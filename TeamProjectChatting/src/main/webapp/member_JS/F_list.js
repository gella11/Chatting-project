//10/28 도현,상진 친구리스트 가져오기.
f_list()
function f_list(){
	$.ajax({
		url : "/TeamProjectChatting/F_list",
		success : function(re){
			let list = JSON.parse(re)
			let html = '';
			for(let i = 0 ; i<list.length; i++){
				let l = list[i]
				html +='<div class="friend_list">'
					+ '<div class="friend_con_box" onclick="chatting('+l.user_num+')"  id='+l.user_num+' >'		
					+ '<div>'			
					if(l.user_profile!==null){
				  html += '<img class="friend_img" alt="" src="/TeamProjectChatting/img/'+l.user_profile+'">' 		
						+ '</div>'			
						+ '<div class="friend_text_box">'			
						+ '<div class="friend_name">'+l.user_name+'</div>'				
						+ '<div class="friend_msg">'+l.user_msg+'</div>'				 
						+ '</div>'			
						+ '</div>'		
						+ '</div>'
					}else{
				  html += '<img class="friend_img" alt="" src="/TeamProjectChatting/img/user.png">' 		
						+ '</div>'			
						+ '<div class="friend_text_box">'			
						+ '<div class="friend_name">'+l.user_name+'</div>'				
						+ '<div class="friend_msg">'+l.user_msg+'</div>'				 
						+ '</div>'			
						+ '</div>'		
						+ '</div>'
					}
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


function addbtn(){
	document.querySelector('.friendaddbtn').click();
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
		 document.querySelector('.f_email').value='';
		 //location.reload();
      }
      else{alert('이메일을 확인해주세요')}
      }
   })
}

// 11/4 친구추가버튼
function subfriendadd(i){
	//추천친구의 value를 친구추가 input에 넣어주기
	document.querySelector('.f_email').value = document.querySelector('.recommendemail'+i).value;
	friendadd();
}
/*
//11/4 도현 추천친구목록 불러오기
recommendfriendlist()
function recommendfriendlist(){
	$.ajax({
      url : "http://localhost:8080/TeamProjectChatting/F_list",
      data : {"option" : 6},
      type:"POST",
      success : function(re){
     	let json = JSON.parse(re)
     	console.log(json);
      }
     if(re=='true'){
         recommendfriendlist();
      }
      else{alert('실패?')}
      }
   })
}*/

//11/8 도현 내프로필 추가하기.
myprofile()
function myprofile(){
	$.ajax({
      url : "/TeamProjectChatting/F_list",
      data : {"option" : 7},
      type:"POST",
      success : function(re){
     	let json = JSON.parse(re)
		document.querySelector('.user_name').innerHTML=json.name;
		document.querySelector('.user_msg').innerHTML=json.msg;
		if(json.profile!==null){
			document.querySelector('.user_profile').src= "/TeamProjectChatting/img/"+json.profile;
   	  	}
   	  	else{
			document.querySelector('.user_profile').src= "/TeamProjectChatting/img/user.png";
		}
   	  }
	})
}
function profilebox(){
	document.querySelector('.profileaddbtn').click();
}

//11/8 도현 내프로필 변경
function updateprofile(){
	let form = document.querySelector('form')
	let formdata = new FormData(form)
	$.ajax({
      url : "/TeamProjectChatting/F_list",
      data : formdata ,
      contentType:false, 
	  processData:false,
      type:"put",
      success : function(re){
     	if(re=='true'){location.reload()}
   	  }
	})
}












