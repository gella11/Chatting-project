
b_view()
// 1. 게시글 출력 - 11/8 혜영
function b_view( b_no ){ // 선택한 글 출력

	$.ajax({
		url		: "/TeamProjectChatting/tboard/view",
		data	: { "b_no" : b_no },
		type	: "get",
		success	: re => {
			let b = JSON.parse(re);
			
			let html = '<div>'
					+ '<div class="view_profile">' 
					+ '<div>' + b.user_profile + '</div>'	
					+ '<div class="view_title">'	
					+ '<div class="user_name"> '+ b.user_name +' </div>'		
					+ '<div class="b_date"> '+ b.b_date +' </div>'		
					+ '</div>'	
					+ '<div class="view_subtitle">'	 
					+ '<div class="user_department"> '+ b.user_department +' </div>'		
					+ '</div>'	
					+ '</div>'
					+ '<div class="content_box">'	
					+ '<div class="b_title"> '+ b.b_title +' </div>'	
					+ '<div class="b_content">'+ b.b_content +'</div>'
					+ '</div>'
					+ '<div class="file_box">'
					+ '<span> 첨부파일 </span>'	
					+ '<span class="b_file"> '+ b.b_file +' </span>'	
					+ '</div>'
					+ '</div>'
					+ '<div class="btn_box">'
					+ '</div>'
					+ '<div class="reply_box">'
					+ '<textarea style="resize: none;" rows="" cols="" class="r_content"></textarea>'
					+ '<button type="button" class="reply_btn"> 등록 </button>'
					+ '</div>';
					document.querySelector('.view_box').innerHTML = html;
			
			// 서블릿에서 조건 검 -> 내 글이거나 관리자면 버튼이 생성되도록
			if( b.btn_action == true ){
				let btn = '<button onclick="b_modal_open()"> 수정 </button>'
						+ '<button onclick="b_delete('+b.b_no+')"> 삭제 </button>'
				document.querySelector('.btn_box').innerHTML = btn;
			}
		}
	})
} // board_view e



// 2. 게시글 수정할 수 있는 모달 창 오픈 - 11/8 혜영
function b_modal_open( b_no ){ 
	
	document.querySelector('.btn').click()
		
	$.ajax({
		url		: "/TeamProjectChatting/tboard/view",
		data	: { "b_no" : b_no },
		type	: "get",
		success	: re => {
			let b = JSON.parse(re);
			document.querySelector('.b_no').value = b.b_no;
		}
	})
} // b_modal_open e


function b_update(){
	
	let form = document.querySelector('.update_form');
	let formata = new FormData( form );
	
	$.ajax({
		url		: "/TeamProjectChatting/tboard/view",
		data	: formata,
		type	: "put",
		processData : false,
		contentType : false,
		success	: re => {
			alert('연결확인')
			
			
		}
	})
} // b_update e


// 3. 게시글 삭제
function b_delete(){
	alert('delete')
	$.ajax({
		url		: "/TeamProjectChatting/tboard/view",
		data	: { "b_no" : b_no },
		type	: "delete",
		success	: re => {
			let b = JSON.parse(re);
			
			
			
		}
	})
	
} // b_delete e




