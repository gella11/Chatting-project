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

//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
// 11/8 상진
// 카테고리 버튼 생성
categorylist()
function categorylist(){
	$.ajax({
		url : "/TeamProjectChatting/categorynum",
		success: (re)=>{
			let category_num = JSON.parse(re)
			console.log(re)
			let html = ''
			category_num.forEach(c =>{
				html +=  '<button onclick="categoryboard('+c.c_no+')"> '+c.c_name+' </button>'	
			})
			// 카테고리 정보를 반복문 돌려서 카테고리별 버튼 innerHTML
		    document.querySelector('.category').innerHTML = html
		    
		}
	})
}
function categorybtn(){
	
}
// 11/8 상진
// 카테고리 번호에 해당하는 글 list 출력
function categoryboard(c_no){
	$.ajax({
		url : "",
		data :{"c_no" : c_no},
		success : (re)=>{
			
			// 카테고리 별 글쓰기 버튼 innerHTML
		    let writebtn = '<button onclick="writebtn('+c_no+')"> 글쓰기 </button>'
		    document.querySelector('.write').innerHTML = writebtn
		}
	})
	
}
// 11/8 상진
// 카테고리 번호에 해당하는 글 쓰 기
function writebtn(c_no){
	$.ajax({
		url : "/TeamProjectChatting/writesession",
		data : {"c_no":c_no},
		success: (re) =>{
			location.href = "/TeamProjectChatting/member_View/board/write.jsp";
		}
	})
}




