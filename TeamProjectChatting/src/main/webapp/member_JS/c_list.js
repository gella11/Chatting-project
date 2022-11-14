//웹소켓
let clientsocket = null;
//채팅방번호 변수
let roomnumber= null;
//
let profile = null;
//회원번호 변수
let mid = document.querySelector('.mid').value;
//회원이름
let myname = null;
//채팅방html
let html = '';
//채팅방 메시지
let msgcontent = '';

//11/8 도현 나의 프로필
myprofile()
function myprofile(){
	$.ajax({
      url : "/TeamProjectChatting/F_list",
      data : {"option" : 7},
      type:"POST",
      async:false,
      success : function(re){
     	let json = JSON.parse(re)
		profile=json.profile;
		myname=json.name;
   	  }
	})
}

//11/1 도현 : 나의 채팅방목록 꺼내오기
c_list() 
function c_list(){
	
   $.ajax({
      url : "/TeamProjectChatting/F_list",
      data:{"option":3 ,"user_num":mid},
      async:false,
      type:"POST",
      success : function(re){
         let json = JSON.parse(re)
         console.log(json)
         json.forEach(c=>{
			let c_num = c.c_num
			
            html+=    '<div class="friend_list roomnumber" onclick="gochat('+c.c_num+')">'
               + '<div class="friend_con_box">'            
               + '<div>'               
               + '<img class="friend_img" alt="" src="/TeamProjectChatting/img/user.png">'                  
               + '</div>'                
               + '<div class="friend_text_box">'  
               + '<div class="friend_name"> '+ c.c_name +' </div>'
              
               $.ajax({
				  url : "/TeamProjectChatting/F_list",
			      data:{"option":8  , "c_num":c_num},
			      async:false,
			      type:"POST",
			      success : function(re){
					if(re=='null'){
						 html+=	'<div class="friend_msg">대화를 시작하세요 !</div>'                  
		                     + '</div>'               
		                     + '</div>'            
		                     + '</div>'
					}
					else{
						html+=	'<div class="friend_msg">'+re+'</div>'                  
		                    + '</div>'               
		                    + '</div>'            
		                    + '</div>'
					}
				  }
			   }) 	               
           })
         document.querySelector('.clist').innerHTML = html;
      }
   })
}
// 11/1 도현 : 채팅하기.
function gochat(c_num){
   $.ajax({
      url : "/TeamProjectChatting/F_list",
      data:{"option":2 , "c_num":c_num},
      async:false,
      type:"POST",
      success : function(re){
         let json = JSON.parse(re)
         roomnumber=json.roomnumber;
         socket()
         document.querySelector('.btn-primary').click()
      }
   })
}
// 11/1 도현 채팅소켓여는함수
function socket(){
   let str = mid+","+roomnumber;
   if (mid != 'null') {
      // 웹소켓에 서버소켓으로 연결[매핑]
      clientsocket = new WebSocket('ws://192.168.17.123:8080/TeamProjectChatting/chatting/'+str);
      // 아래에서 구현 메소드를 객체에 대입
      clientsocket.onopen = function(e) { onopen(e) }
      clientsocket.onclose = function(e) { onclose(e) }
      clientsocket.onmessage = function(e) { onmessage(e) }
      clientsocket.onerror = function(e) { onerror(e) }
   }
   function onopen(e) {}
   function onclose(e) {}  
}

// 11/1 도현 esc로 소켓끄기 새로고침
$(document).keyup(function(e) {
     if (e.key === "Escape") { 
        location.reload();
    }
});
// 11/1 도현 소켓끄기 새로고침
function socketclose(){
   location.reload();
}

 
//  11/1 도현 메시지보내기
function send() {
   msgcontent = document.querySelector('.msgbox').value;
   let today = new Date();   
   let msg = { // 전송할 데이터 객체
      type: roomnumber,// 일반메시지 
      content: msgcontent, // 작성내용
      mid: mid,  // 보낸 사람 회원번호.
      img: profile,
      name:myname,
      date : today.getFullYear()+'-'+(today.getMonth()+1)+'-'+ today.getDate()+' '+
      	     today.getHours()+':'+today.getMinutes()+':'+today.getSeconds()
   }
   $.ajax({
      url : "/TeamProjectChatting/F_list",
      data:{"option":5 , "msg":JSON.stringify(msg)},
      async:false,
      type:"POST",
      success : function(re){
         if(re=='false'){return;}
      }
   })
   clientsocket.send(JSON.stringify(msg));
   document.querySelector('.msgbox').value = '';
}

//  11/1 도현 엔터키로 메시지보내기
function enterkey() { if (window.event.keyCode == 13) { send() } }

//  11/1 도현 메시지받기
function onmessage(e) {
   let msg = JSON.parse(e.data) // 받은 데이터 객체
   if (msg.type === roomnumber) { //전송타입이 현재방번호 
      if (msg.mid == mid) { // 본인 글이면  // 보낸사람 아이디와 접속된 아이디가 동일하면
         let aahtml = document.querySelector('.contentbox').innerHTML;

         aahtml += '<div class="secontent my-3">'+
	'							<span class="date">'+msg.date+'</span>'+
	'							<span class="content">'+msg.content+'</span>'+
	'			   </div>';
         document.querySelector('.contentbox').innerHTML = aahtml

      } else { // 본인 글이 아니면 
         let bbhtml = document.querySelector('.contentbox').innerHTML;

         bbhtml +=  '<div class="row g-0 my-3">'+
					'<div class="col-sm-1 mx-2">';
					if(msg.img!=='null' && msg.img!==null){
						bbhtml+='<img width="100%;" class="rounded-circle" src="/TeamProjectChatting/img/'+msg.img+'">'
					}
					else{
						bbhtml+='<img width="100%;" class="rounded-circle" src="/TeamProjectChatting/img/user.png">'
					}

		 bbhtml +=	'</div>'+
						'<div class="col-sm-9">'+
							'<div class="recontent">'+
								'<div class="name">'+msg.name+'</div>'+
								'<span class="content">'+msg.content+'</span>'+
								'<span class="date">'+msg.date+'</span>'+
							'</div>'+
						'</div>'+
					'</div>';;
         document.querySelector('.contentbox').innerHTML = bbhtml
      }
    //스크롤내리기
	document.querySelector('.contentbox').scrollTop = document.querySelector('.contentbox').scrollHeight; 
   }    
}
function onerror(e) { }
document.querySelector('.contentbox').scrollTop = document.querySelector('.contentbox').scrollHeight; 




//11/9 도현 채팅방만들기 클릭함수
function addbtn(){
	document.querySelector('.groupchatbtn').click();
}

//11/9 도현 간단 친구목록 
f_list()
function f_list(){
	$.ajax({
		url : "/TeamProjectChatting/F_list",
		success : function(re){
			let list = JSON.parse(re)
			let html = '';
			for(let i = 0 ; i<list.length; i++){
				let l = list[i]
				html +='<div class="friendbox"><input type="checkbox" class="friendno" value="'+l.user_num+'"><span class="nameemail"> 이름 : '+l.user_name+"  /  이메일 : "+l.user_email+' </span></div>'
			}
			document.querySelector('.c_friendlist').innerHTML = html;
		}
	})
}

//11/9 도현 단톡방 만들기
function groupchatting(){
	let chattingname = document.querySelector('.chattingname').value;
	let flist =  document.querySelectorAll('.friendno');
  	let list = [];
    for (var i=0; i<flist.length; i++) {
       if (flist[i].checked == true) {
           list.push(flist[i].value)
       }
    }
    
	$.ajax({
      url : "/TeamProjectChatting/F_list",
      data:{"option":0 , "list":JSON.stringify(list) , "name":chattingname},
      async:false,
      type:"POST",
      success : function(re){
         if(re=='true'){location.reload()
         }
         else{
			alert('잘못된 입력입니다.')	
		 }
      }
   })
}


//11/10 도현 채팅방 나가기
function chattingout(){
	$.ajax({
      url : "/TeamProjectChatting/F_list",
      data:{"option":6 , "c_num":roomnumber},
      async:false,
      type:"POST",
      success : function(re){
         if(re=='true'){
		 let outmessage = "<span style='color : red;'>*** 알림 : "+myname+"님이 나갔습니다.***</span>";
		 document.querySelector('.msgbox').value=outmessage;
		 send();
		 location.reload();
         }
         else if(re=='allout'){
		 location.reload();
		 }
         else{
			alert('잘못된 입력입니다.')	
		 }
      }
   })
}













