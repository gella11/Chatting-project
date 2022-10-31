
// 도현 상진
// [10/28]
f_list()
function f_list(){
	$.ajax({
		url : "/TeamProjectChatting/F_list",
		success : function(re){
			let list = JSON.parse(re)
			let html = document.querySelector('.f_list').innerHTML
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
