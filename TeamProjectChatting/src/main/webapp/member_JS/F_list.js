

//10/28 도현,상진 친구리스트 가져오기.
f_list()
function f_list(){
	$.ajax({
		url : "/TeamProjectChatting/F_list",
		success : function(re){
			let list = JSON.parse(re)
			let html = '<tr><td>프로필</td><td>이름</td><td>상태메시지</td></tr>'
			for(let i = 0 ; i<list.length; i++){
				let l = list[i]
				html += '<tr onclick="chatting('+l.user_num+')"  id='+l.user_num+' >'
						+	'<td>'+l.user_profile+' </td> '
						+	'<td>'+l.user_name+'</td>'
						+	'<td>'+l.user_msg+'</td> '
						+'</tr> <br>';
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

//11/2 도현 추천친구찾기. 나를 친구추가했지만 , 내가 친구추가안한사람


