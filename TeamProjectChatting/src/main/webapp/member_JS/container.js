

//변환이벤트
function tabchange(page){
	$(".container").load(page)// 특정 태그에 해당 파일 로드 [ jquery ]
console.log(page)
}


f_list()
function f_list(){
	$.ajax({
		url : "/TeamProjectChatting/F_list",
		success : function(re){
			let list = JSON.parse(re)
			let html = document.querySelector('.f_list').innerHTML
			for(let i = 0 ; i<list.length; i++){
				let l = list[i]
				console.log(l.user_num)
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
