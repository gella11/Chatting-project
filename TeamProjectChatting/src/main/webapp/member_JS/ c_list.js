/**
 * 
 */
 
 let clientsocket = null;

c_list() 
function c_list(){
	$.ajax({
		url : "/TeamProjectChatting/F_list",
		data:{"option":2},
		type:"POST",
		success : function(re){
			let json = JSON.parse(re)
			alert(json.roomnumber)
			roomnumber = json.roomnumber;
			html='<tr><th>채팅방 이름</th></tr>';
			html+=`<tr class="roomnumber" value="${json.roomnumber}"><th>${json.cname}</th></tr>`;
			document.querySelector('.clist').innerHTML=html;
			//socket()
		}
	})
}

let mid = document.querySelector('.mid').value;
let roomnumber =document.querySelector('.roomnumber').value;
console.log(mid)
console.log(roomnumber)

function socket(){
	if (mid != 'null') {
		// 웹소켓에 서버소켓으로 연결[매핑]
		clientsocket = new WebSocket('ws://localhost:8080/jspweb/chatting/' + mid);
		// 아래에서 구현 메소드를 객체에 대입
		clientsocket.onopen = function(e) { onopen(e) }
		clientsocket.onclose = function(e) { onclose(e) }
		clientsocket.onmessage = function(e) { onmessage(e) }
		clientsocket.onerror = function(e) { onerror(e) }
	} else { alert('로그인이 필요합니다.'); location.href = '../member/login.jsp'; }
	function onopen(e) { alert(e) }
	function onclose(e) { alert(e) }
}



function send() {
	let msg = { // 전송할 데이터 객체
		type: type,// 일반메시지 
		content: document.querySelector('.msgbox').value, // 작성내용
		mid: mid,  // 보낸 사람 
		date: new Date().toLocaleTimeString(), // 날짜 
		img: '프로필.jpg' // 사진
	}
	clientsocket.send(JSON.stringify(msg));
	document.querySelector('.msgbox').value = '';
}

function emosend(i) { // 이모티콘 전송
	let msg = {
		type: "emo",	// 이모티콘 
		content: i,	// 이미지 번호
		mid: mid,
		date: new Date().toLocaleTimeString(),//날짜
		img: '프로필.jpg'
	}
	clientsocket.send(JSON.stringify(msg))
}



function enterkey() { if (window.event.keyCode == 13) { send() } }
function onmessage(e) {
	let msg = JSON.parse(e.data) // 받은 데이터 객체

	if (msg.type == type) { //전송타입이 메시지 이면 
		if (msg.mid == mid) { // 본인 글이면  // 보낸사람 아이디와 접속된 아이디가 동일하면
			let html = document.querySelector('.contentbox').innerHTML;

			html += '<div class="secontent my-3"> ' +
				'<span class="date"> ' + msg.date + ' </span>' +
				'<span class="content"> ' + msg.content + ' </span>' +
				'</div>';
			document.querySelector('.contentbox').innerHTML = html

		} else { // 본인 글이 아니면 
			let html = document.querySelector('.contentbox').innerHTML;
			html += '<div class="row g-0 my-3">' +
				'	<div class="col-sm-1 mx-2">' +
				'		<img width="100%;" class="rounded-circle" alt="" src="/jspweb/img/' + msg.img + '">' +
				'	</div>' +
				'	<div class="col-sm-9"> ' +
				'		<div class="recontent"> ' +
				'			<div class="name">' + msg.mid + '</div>' +
				'			<span class="content">' + msg.content + '</span>' +
				'			<span class="date">' + msg.date + '</span>' +
				'		</div>' +
				'	</div>' +
				'</div>';
			document.querySelector('.contentbox').innerHTML = html
		}
		//////////////////////////////////////////////////////////////
	} else if (msg.type == 'emo') {//전송타입이 이모티콘 이면 
		//////////////////////////////2.이모티콘 전송 코드 /////////////////
		if (msg.mid == mid) {//내가보낸거
			let html = document.querySelector('.contentbox').innerHTML;

			html += '<div class="secontent my-3"> ' +
				'<span class="date"> ' + msg.date + ' </span>' +
				'<img src="/jspweb/img/imoji/emo' + msg.content + '.gif" width="90px"> ' +
				'</div>';
			document.querySelector('.contentbox').innerHTML = html
		}
		else {
			let html = document.querySelector('.contentbox').innerHTML;
			html += '<div class="row g-0 my-3">' +
				'	<div class="col-sm-1 mx-2">' +
				'		<img width="100%;" class="rounded-circle" alt="" src="/jspweb/img/' + msg.img + '">' +
				'	</div>' +
				'	<div class="col-sm-9"> ' +
				'		<div class="recontent"> ' +
				'			<div class="name">' + msg.mid + '</div>' +
				'		<img src="/jspweb/img/imoji/emo' + msg.content + '.gif" width="90px"> ' +
				'			<span class="date">' + msg.date + '</span>' +
				'		</div>' +
				'	</div>' +
				'</div>';
			document.querySelector('.contentbox').innerHTML = html
		}
		//////////////////////////////////////////////////////////////
	} else if (msg.type == 'alarm') {
		let html = document.querySelector('.contentbox').innerHTML
		html += '<div class=alarm>' +
			'				<span>' + msg.content + ' </span>' +
			'			</div>';
		document.querySelector('.contentbox').innerHTML = html
	}

	////////////////////////스크롤 하단으로 내리기 /////////////////////////////// 
	/////////////// 스크롤 하단으로 내리기 ////////////////////////
	let top = document.querySelector('.contentbox').scrollTop;
	let Height = document.querySelector('.contentbox').scrollHeight;
	console.log('스크롤막대 상단위치 : ' + top)
	console.log('스크롤 전체 길이 : ' + Height)

	document.querySelector('.contentbox').scrollTop
		= document.querySelector('.contentbox').scrollHeight;
}

function onerror(e) { alert(e) }
