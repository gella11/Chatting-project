// 11/6 혜영 글 전체 보기

let pageinfo ={
	
	list_size : 3,	// 한페이지당 게시물 표시 개수
	page : 1,		// 현재 페이지 번호
	key : '',		// 검색 키 [ 공백이면 값이 없음 ]
	keyword : '',	// 검색 키워드
	c_no : 1		// 카테고리 번호 1 : 전체
	
}

list(1, 1)
function list( page, c_no ){
	console.log("c_no 11:: " + c_no)
	pageinfo.page = page;
	pageinfo.c_no = c_no;
	console.log("c_no 22:: " + c_no)
	$.ajax({
		url : "/TeamProjectChatting/board/list",
		async: false, 
		data : pageinfo ,
		success : function(re){
			
			let blist = JSON.parse(re);
			let list = blist.data
			
			let html = '';
			for( let i = 0; i < list.length; i++ ){
				let l = list[i];
				/*
				if( pageinfo.c_no == 0 ){
					
				}else if( pageinfo.c_no == l.c_no ){
					
				}
				*/
				html += '<div class="boder">'
					+ '<div class="list_profile">'
					+ '<div><img class="user_profile" alt="" src="../../img/'+ l.user_profile +'"></div>'	
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
			// 버튼 출력
			let writebtn = '<button onclick="writebtn('+c_no+')"> 글쓰기 </button>'
         	document.querySelector('.write').innerHTML = writebtn
			// 게시물 출력
			document.querySelector('.list_box').innerHTML = html;
			
			// 페이징 버튼 출력
			let page_html = '';
			
			// 이전 버튼 출력
			if( page <= 1 ){
				page_html += '<button onclick="list('+(page +','+ c_no)+')"> 이전 </button>';
			}else{
				page_html += '<button onclick="list('+(page-1, +','+ c_no)+')"> 이전 </button>';
			}
			
			// [ 페이지번호 버튼 ] 시작 버튼 - 마지막 버튼 출력
			for( let page = blist.start_btn; page <= blist.end_btn; page++ ){
				page_html += '<button type="button" onclick="list('+ page +','+ c_no +')">'+ page +'</button>'
			}
			
			// 다음 버튼 출력
			if( page >= blist.total_page ){
				// 현재 페이지가 마지막 페이지면 다음 페이지 불가 제한
				page_html += '<button onclick="list('+(page +','+ c_no)+')"> 다음 </button>';
			}
			else{
				page_html += '<button onclick="list('+(page+1+','+ c_no)+')"> 다음 </button>';
			}
			document.querySelector('.pagebox').innerHTML = page_html;
			
			// 5. 검색된 전체 게시물 수 출력	
			document.querySelector('.total_size').innerHTML = blist.total_size;	
			
		}
	})
} // list e


// 제목 클릭 시 페이지 이동 함수
function select_view( b_no ){
	$.ajax({
		url		: "/TeamProjectChatting/board/viewselect",
		async: false, 
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
		async: false, 
		success: (re)=>{
			let category_num = JSON.parse(re)
			console.log(re)
			let html = ''
			category_num.forEach(c =>{
				html +=  '<button onclick="list('+ pageinfo.page+','+ c.c_no+')"> '+c.c_name+' </button>'	
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
      url : "/TeamProjectChatting/boardlist",
      async: false, 
      data :{"c_no" : c_no},
      success : (re)=>{
         let list = JSON.parse(re)
         
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
		async: false, 
		data : {"c_no":c_no},
		success: (re) =>{
			location.href = "/TeamProjectChatting/member_View/board/write.jsp";
		}
	})
}


// 4. 검색 처리 메소드
function bsearch(){
	
	pageinfo.key = key = document.querySelector(".key").value;
	pageinfo.keyword = keyword = document.querySelector(".keyword").value;
	console.log("pageinfo.key :: " + pageinfo.key)
	console.log("pageinfo.keyword :: " + pageinfo.keyword)
	
	// 검색 완료 시 첫페이지로 출력되도록해야 함
	list( 1, pageinfo.c_no )	// 메소드 호출 시 파라미터 사용하여 첫 페이지로 보여지게끔
		
} // bsearch es


// 6. 게시물 표시 개수 : 한 페이지에 보여지는 게시글 수 체인지 이벤트 사용하여 변경하기
function blist_size(){
	
	pageinfo.list_size = document.querySelector('.list_size').value;
	list( 1, pageinfo.c_no )
	
} // blistsize e


