
// 도현 상진
// [10/28]
f_list()
function f_list(){
	$.ajax({
		url : "/TeamProjectChatting/F_list",
		success : function(re){
			let list = JSON.parse(re)
			console.log(re)
			let html = document.querySelector('.f_list').innerHTML
			for(let i = 0 ; i<list.length; i++){
				let l = list[i]
				alert(l)
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
      data : {"chattingnum" : chattingnum},
      type:"POST",
      success : function(re){
          let endroom = re
          document.querySelector('.모델에 넣을 곳').innerHTML = endroom;
          
          document.querySelector(".updatemodalhtn").click()
          
          
         }
   })
}
