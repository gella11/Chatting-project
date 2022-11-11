
b_view()
// 1. 게시글 출력 - 11/8 혜영
function b_view( b_no ){ // 선택한 글 출력

	$.ajax({
		url		: "/TeamProjectChatting/tboard/view",
		data	: { "b_no" : b_no },
		type	: "get",
		success	: re => {
			let b = JSON.parse(re);
			let profile = b.user_profile
			if(profile === null){
				profile = "user.png"
				let html = '<div>'
					+ '<div class="view_profile">' 
					+ '<div><img class="user_profile" alt="" src="../img/'+ profile +'"></div>'	
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
					+ '<button type="button" class="reply_btn" onclick="rwrite('+b_no+')"> 등록 </button>'
					+ '</div>';
					document.querySelector('.view_box').innerHTML = html;
			}else{
				let html = '<div>'
					+ '<div class="view_profile">' 
					+ '<div><img class="user_profile" alt="" src="../img/'+ b.user_profile +'"></div>'	
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
					+ '<button type="button" class="reply_btn" onclick="rwrite('+b_no+')"> 등록 </button>'
					+ '</div>';
					document.querySelector('.view_box').innerHTML = html;
			}
					
			
					
					// 뒤로가기 버튼 생성
					let return_btn = '<span class="return_btn">'
							+ '<a href="/TeamProjectChatting/member_View/board/list.jsp"> 뒤로가기 </a>'
							//+ '<a onclick="pagechange('+/TeamProjectChatting/member_View/board/view2.jsp+')"> 뒤로가기 </a>'
							+ '</span>';
					document.querySelector('.btn_box').innerHTML = return_btn;
					rlist()
			// 서블릿에서 조건 검 -> 내 글이거나 관리자면 버튼이 생성되도록
			if( b.btn_action == true ){
				let btn = '<button onclick="b_modal_open()" class="update_btn2"> 수정 </button>'
						+ '<button onclick="b_delete('+b.b_no+')" class="delete_btn2"> 삭제 </button>'
				document.querySelector('.btn_box').innerHTML += btn;
				
				
			}
		}
	})
} // board_view e

// 11/9 상진
// 댓글 value 저장
function rwrite(b_no){
	let r_content = document.querySelector(".r_content").value
	$.ajax({
		url:"/TeamProjectChatting/rwrite",
		data : {"r_content" : r_content , "type" : "reply" , "b_no" : b_no},
		type : "POST",						// get방식은 기본값
		success : function(re){
			if( re == 1){
				alert('댓글 작성')
				//location.reload()
				pagechange2('../member_View/board/view2.jsp')	
			}else if( re == 0) {
				alert('로그인이 필요합니다.')
				location.href='../../member_View/login.jsp'
			}else{
				alert('댓글 등록 실패!!!')
			}
		}
	})
}


// 댓글 리스트 출력
function rlist(){
	$.ajax({
		url:"/TeamProjectChatting/rlist",
		data : {"type" : "reply"},
		success : function(re){
			let replylist = JSON.parse(re)
			
			let html = ''
			
			for(let i = 0 ; i<replylist.length; i++){
				let reply = replylist[i]
				
				//대댓글 호출 = rno
				$.ajax({
					url:"/TeamProjectChatting/rlist",
					data : {"type" : "rereply" , "r_no" : reply.r_no},
					async : false,
					success : function(re){
						let rereplylist = JSON.parse(re)
							html += '<div class="reply_box2">' 
										+ '<div class="view_profile">' 
										+ '<div><img class="user_profile" alt="" src="../img/'+ reply.user_profile +'"></div>'	
										+ '<div class="view_title">'	
										+ '<div class="user_name"> '+ reply.user_name +' </div>'		
										+ '<div class="b_date"> '+ reply.r_date +' </div>'		
										+ '</div>'	
										+ '<div class="view_subtitle">'	 
										+ '<div class="user_department"> '+ reply.user_department +' </div>'		
										+ '</div>'	
										+ '</div>'
										+ '<div class="content_box">'	
										+ '<div class="b_content reply_con">'+ reply.r_content +'</div>'
										+ '</div>'
										+ '</div>'
										+ '<div class="btn_box">'
										+ '</div>'
										+ '<button type="button" onclick="rereplyview('+reply.r_no+')" class="rereply_btn"> 답글 </button>'  
										+ '<div class="reply'+reply.r_no+'"> </div>'
										+ '</div>';
							for(let j = 0 ; j<rereplylist.length; j++){
							let rereply = rereplylist[j]
							html += '<div style="margin : 150px;">' 				 
										+ '<div class="view_profile">' 
										+ '<div><img class="user_profile" alt="" src="../../img/'+ rereply.user_profile +'"></div>'	
										+ '<div class="view_title">'	
										+ '<div class="user_name"> '+ rereply.user_name +' </div>'		
										+ '<div class="b_date"> '+ rereply.r_date +' </div>'		
										+ '</div>'	
										+ '<div class="view_subtitle">'	 
										+ '<div class="user_department"> '+ rereply.user_department +' </div>'		
										+ '</div>'	
										+ '</div>'
										+ '<div class="content_box">'	
										+ '<div class="b_content">'+ rereply.r_content +'</div>'
										+ '</div>'
										+ '</div>'
										+ '<div class="btn_box">'
										+ '</div>'
										+ '<div class="reply'+rereply.r_no+'"> </div>'
										+ '</div>';		 	 
									+ '</div>'
							}
							html += '</div>'
						}
					})
				}
			console.log(html)
			document.querySelector('.replylist').innerHTML = html
		}
	})
}

// 댓글 입력시 인풋박스 등장
function rereplyview(r_no){
	let replydiv = document.querySelector('.reply'+r_no)	
	replydiv.innerHTML = '<input type="text" class="rerecontent'+r_no+'" > <button onclick="rereplywrite('+r_no+')">답글등록</button>'
}
// 대댓글 value 저장
function rereplywrite(r_no){
	let r_content = document.querySelector('.rerecontent'+r_no).value
	$.ajax({
		url:"/TeamProjectChatting/rwrite",
		data : {"r_content" : r_content , "r_no" : r_no , "type" : "rereply"},	// type은 같은 서블릿 기능
		type : "POST",
		success : function(re){
			if( re == 1){
				alert('대댓글 작성')
				pagechange2('../member_View/board/view2.jsp')
			}else if( re == 0) {
				alert('로그인이 필요합니다.')
				location.href='../../member_View/login.jsp'
			}else{
				alert('대댓글 등록 실패!!!')
			}
		}
	})
}

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


// 3. 게시글 수정 - 11/8 혜영
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
			alert('게시글이 수정되었습니다.');
			location.href="/TeamProjectChatting/member_View/board/view.jsp"
			
			
		}
	})
} // b_update e


// 4. 게시글 삭제 - 11/9 혜영
function b_delete( b_no ){
	
	$.ajax({
		url		: "/TeamProjectChatting/tboard/view",
		data	: { "b_no" : b_no },
		type	: "delete",
		success	: re => {
			if( re == 'true' ){
				alert('게시글 삭제 성공')
				location.href = "list.jsp"
			}else{
				alert('게시글 삭제 실패')
			}
		}
	})
	
} // b_delete e



