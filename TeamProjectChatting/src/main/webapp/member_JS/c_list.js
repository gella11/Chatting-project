/**
 * 
 */

var clientsocket = null;
var roomnumber= null;
var mid = document.querySelector('.mid').value;


c_list() 
function c_list(){
	$.ajax({
		url : "/TeamProjectChatting/F_list",
		data:{"option":2},
		type:"POST",
		success : function(re){
			let json = JSON.parse(re)
			html='<tr><th>채팅방 이름</th></tr>';
			html+=`<tr class="roomnumber" value="${json.roomnumber}"><th>${json.cname}</th></tr>`;
			roomnumber=json.roomnumber;
			document.querySelector('.clist').innerHTML=html;
			document.querySelector('.btn-primary').click()
			socket()
		}
	})
}

function socket(){
	alert(roomnumber)
	if (mid != 'null') {
		// 웹소켓에 서버소켓으로 연결[매핑]
		clientsocket = new WebSocket('ws://localhost:8081/TeamProjectChatting/chatting/'+mid);
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
		type: roomnumber,// 일반메시지 
		content: document.querySelector('.msgbox').value, // 작성내용
		mid: mid,  // 보낸 사람 
	}
	clientsocket.send(JSON.stringify(msg));
	document.querySelector('.msgbox').value = '';
}

function enterkey() { if (window.event.keyCode == 13) { send() } }

function onmessage(e) {
	let msg = JSON.parse(e.data) // 받은 데이터 객체

	if (msg.type == roomnumber) { //전송타입이 메시지 이면 
		if (msg.mid == mid) { // 본인 글이면  // 보낸사람 아이디와 접속된 아이디가 동일하면
			let html = document.querySelector('.contentbox').innerHTML;

			html += '<div class="secontent my-3"> ' +
				//'<span class="date"> ' + msg.date + ' </span>' +
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
	} 
	else if (msg.type == 'alarm') {
		let html = document.querySelector('.contentbox').innerHTML
		html += '<div class=alarm>' +
			'				<span>' + msg.content + ' </span>' +
			'			</div>';
		document.querySelector('.contentbox').innerHTML = html
	}

	////////////////////////스크롤 고정 /////////////////////////////// 
	let top = document.querySelector('.contentbox').scrollTop;
	let Height = document.querySelector('.contentbox').scrollHeight;
	  document.querySelector('.contentbox').scrollTop
	= document.querySelector('.contentbox').scrollHeight;
}

function onerror(e) { alert(e) }


