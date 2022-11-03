
//10/28 도현,상진 친구리스트 가져오기.
f_list()
function f_list(){
	$.ajax({
		url : "/TeamProjectChatting/F_list",
		success : function(re){
			let list = JSON.parse(re)
			let html = document.querySelector('.f_list').innerHTML
			for(let i = 0 ; i<list.length; i++){
				let l = list[i]
				html +=  '<div class="friend_list">'
					+ '<div class="friend_con_box" onclick="chatting('+l.user_num+')"  id='+l.user_num+' >'		
					+ '<div>'			
					+ '<img class="friend_img" alt="" src="../img/망햄터.png">' + l.user_profile	// 이 라인에 프로필 이미지 들어가야돼요		
					+ '</div>'			
					+ '<div class="friend_text_box">'			
					+ '<div class="friend_name">'+l.user_name+'</div>'				
					+ '<div class="friend_msg">'+l.user_msg+'</div>'				 
					+ '</div>'			
					+ '</div>'		
					+ '</div>'
			}
			document.querySelector('.f_list').innerHTML = html;
		}
	})
}
//10/28 도현,상진 채팅방생성 후 채팅창으로 넘어가기.
function chatting(num){
   let chattingnum = num;
   $.ajax({
      url : "/TeamProjectChatting/F_list",
      data : {"chattingnum" : chattingnum, "option" : 1},
      type:"POST",
      success : function(re){
		location.href='/TeamProjectChatting/member_View/c_list.jsp';
       }
   })
}

// 친구목록 & 채팅방 하단 탭 
let icon_box = document.querySelector('.icon_box')
let container = document.querySelector('.container')

//변환이벤트
function tabchange(page){
	$(".container").load(page)// 특정 태그에 해당 파일 로드 [ jquery ]
}

//11/2 도현 모달에서 친구추가하기.
function friendadd(){
   let email = document.querySelector('.f_email').value;
   $.ajax({
      url : "/TeamProjectChatting/F_list",
      data : {"email" : email, "option" : 4},
      type:"POST",
      success : function(re){
      if(re=='true'){
         location.reload();
      }else{alert('이메일을 확인해주세요')}
      }
   })
}








// 2-1. 수정 모달 실행 메소드 
function updatemodal( pno ){
	// 1. 해당 모달을 열러주는 버튼에 강제클릭 이벤트 실행 
	document.querySelector(".updatemodalhtn").click() // 해당 버튼을 강제클릭하는 이벤트 실행
	console.log('수정버튼 클릭')
		$.ajax({
			url : "/TeamProjectChatting/infochang",
			data : { "type" : 2 , "pno" : pno } , 	// 타입이 2 이면 개별 제품 호출 
			type : "get" ,
			success : function( re ){ 
				let json = JSON.parse(re)
				
				document.querySelector('.....').value = json.user_profile
				document.querySelector('.user_msg').value = json.user_msg
				document.querySelector('#pimgpre').src = e.target.result
			}
		})
	}
	
// 2. 첨부파일 등록(변경)했을 때 미리보기
let pimg = document.querySelector('#pimg')
	pimg.addEventListener('change',function(e){	// e 꼭 넣어야함 e:event 객체(이벤트 정보[target] 여기선 change이벤트 , 누가 뭐를[]data])
	// 1) js 파일 클래스 []
	let file = new FileReader() // 객체생성
	console.log(file+"는 file")
	console.log(e+"는 e")
	console.log(e.target+"는 e.taget")
	console.log(e.target.files[0]+"는 e.taget.files[0]")
	// 2) 해당 펌부된 파일 경로 알기
	file.readAsDataURL(e.target.files[0])
	// 3) 이미지 태그에 첨부된 이미지 대입
	file.onload = function(e){ // e는 onload 이벤트 정보
		document.querySelector('#pimgpre').src = e.target.result
	}
})

// 2-2. 수정 처리 메소드
function infomodify(){
	// 1.수정할 정보
	let form = document.querySelector('.updateform')
	let formdata = new FormData( form )
	// fomrdata 속성 추가 
		// formdata.set('속성명' : 데이터 )
		// formdata.set('pno' : pno )
	$.ajax({ 
		url : "/jspweb/registaaaaa" , 
		type : "put" ,  // 해당 서블릿주소 의 doPut메소드과 통신
		data : formdata , 
		processData : false , 
		contentType : false , 
		success : function( re ){ 
			if(re === 'true'){
				alert('수정 완료')
				// 1. 모달닫기
				document.querySelector(".modelclosebtn").click()
				// 2. 새로고침
				pagechage('F_list.jsp')
			}
			else{
				alert('수정 실패')
			}
		}
	})
}