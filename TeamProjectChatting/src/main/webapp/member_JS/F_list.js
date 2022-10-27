
// 상진 
// jsp 등록된 섹션 값 가져오기
//let user_email = document.querySelector('.mid').value

// 상진
// 친구 리스트 출력
f_list()
function f_list(){
	$.ajax({
		url : "/TeamProjectChatting/F_list",
		//data : {"user_email" : user_email},
		success : function(re){
			let list = JSON.parse(re)
			let html = document.querySelector('.f_list').innerHTML
			for(let i = 0 ; i<list.length; i++){
				let l = list[i]
				html += '<tr onclick="모달()" value='+l.user_num+' id='+l.user_num+' >'
						+	'<td>'+l.user_profile+' </td> '
						+	'<td>'+l.user_name+'</td>'
						+	'<td>'+l.user_msg+'</td> '
						+'</tr> <br>';
			}
			document.querySelector('.f_list').innerHTML = html;
		}
	})
}


