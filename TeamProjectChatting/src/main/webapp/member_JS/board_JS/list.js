
// 11/6 혜영 글 전체 보기
list()
function list(){
	$.ajax({
		url : "/TeamProjectChatting/board/list",
		success : function(re){
			
			let list = JSON.parse(re);
			let html = '';
			for( let i = 0; i < list.length; i++ ){
				let l = list[i];
				html += '<div class="boder">'
					+ '<div class="list_profile">'
					+ '<div>' + l.user_profile + '</div>'	
					+ '<div class="list_title"> '	
					+ '<div class="user_name"> '+ l.user_name +' </div>'		
					+ '<div class="b_date"> '+ l.b_date +' </div>'		
					+ '</div>'	
					+ '<div class="list_subtitle">'	
					+ '<div class="user_department"> '+ l.user_department +' </div>'
					+ '</div>'	
					+ '</div>'
					+ '<div class="content_box">'		
					+ '<div class="b_title" onclick="select_view(' + l.b_no + ')"> '+ l.b_title +' </div>'	
					+ '</div>'	
					+ '</div>'
			}
			document.querySelector('.list_box').innerHTML += html;
		}
	})
} // list e


// 제목 클릭 시 페이지 이동 함수
function select_view( b_no ){
	$.ajax({
		url		: "/TeamProjectChatting/board/viewselect",
		data	: { "b_no" : b_no },
		success	: re => {
			location.href = "/TeamProjectChatting/member_View/board/view.jsp";
		}
	})
} // select_view e






