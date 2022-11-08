// 11/6 혜영 글 전체 보기
list()
function list(){
	$.ajax({
		url : "/TeamProjectChatting/board/list",
		success : function(re){
			alert(re);
			let list = JSON.parse(re);
			let html = '';
			for( let i = 0; i < list.length; i++ ){
				let l = list[i];
				html += '<div>'
			+ '<div class="list_profile">'
			+ '<div>'	
			+ '<img class="user_profile" alt="" src="../../img/망곰이.png">'		
			+ '</div>'	
			+ '<div class="list_title"> '	
			+ '<div class="user_name"> '+ l.user_name +' </div>'		
			+ '<div class="b_date"> '+ l.b_date +' </div>'		
			+ '</div>'	
			+ '<div class="list_subtitle">'	
			+ '<div class="user_department"> 인사과 </div>'
			+ '</div>'	
			+ '</div>'
			+ '<div class="content_box">'		
			+ '<div class="b_title"> '+ b_title +' </div>'	
			+ '</div>'	
			+ '</div>'
				
				
			}
			document.querySelector('.list_box').innerHTML = html;
		}
	})
} // list e
