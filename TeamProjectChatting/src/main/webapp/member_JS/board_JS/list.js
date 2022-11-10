// 11/6 혜영 글 전체 보기

let pageinfo ={
	
	list_size : 5,	// 한페이지당 게시물 표시 개수
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
				page_html += '<button onclick="list('+(page-1 +','+ c_no)+')"> 이전 </button>';
			}
			
			// [ 페이지번호 버튼 ] 시작 버튼 - 마지막 버튼 출력
			for( let page = blist.start_btn; page <= blist.end_btn; page++ ){
				page_html += '<button type="button" onclick="list('+ page +','+ c_no +')">'+ page +'</button>'
			} console.log("page :  " + page)
				console.log("blist.start_btn :  " + blist.start_btn) 
				console.log("blist.end_btn :  " + blist.end_btn) 
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
			if(re==='true'){
				location.href = "/TeamProjectChatting/member_View/board/write.jsp";	
			}else{
				alert('당신은 이 부서 사람이 아니야 보기만 해')
				location.href = "/TeamProjectChatting/member_View/board/list.jsp";	
			}
			
		}
	})
}


// 검색 처리 메소드 혜영 - 11/9
function bsearch(){
	
	pageinfo.key = key = document.querySelector(".key").value;
	pageinfo.keyword = keyword = document.querySelector(".keyword").value;
	console.log("pageinfo.key :: " + pageinfo.key)
	console.log("pageinfo.keyword :: " + pageinfo.keyword)
	
	// 검색 완료 시 첫페이지로 출력되도록해야 함
	list( 1, pageinfo.c_no )	// 메소드 호출 시 파라미터 사용하여 첫 페이지로 보여지게끔
		
} // bsearch es


// 게시물 표시 개수 혜영 - 11/9
function blist_size(){
	
	pageinfo.list_size = document.querySelector('.list_size').value;
	list( 1, pageinfo.c_no )
	
} // blistsize e

//[2022-11-10]
function admin_board(){
	let user_num=document.querySelector('.user_num').value
	$.ajax({
		url:"/TeamProjectChatting/boardlist",
		data:{"user_num":user_num},
		type:"post",
		success:function(re){
			let html='';
			let admin =JSON.parse(re)
			admin.forEach(l=>{
				html += '<div class="boder">'
					+ '<div class="list_profile">'
					+ '<div><img class="user_profile" alt="" src="../../img/'+ l.user_profile +'"></div>'	
					+ '<div class="list_title"> '	
					+ '<div class="user_name"> '+ l.user_name +' </div>'		
					+ '<div class="b_date"> '+ l.b_date +' </div>'		
					+ '</div>'	
					+ '<div class="list_subtitle">'	
					+ '</div>'	
					+ '</div>'
					+ '<div class="content_box">'		
					+ '<div class="b_title" onclick="select_view(' + l.b_no + ')"> '+ l.b_title +' </div>'	
					+ '</div>'	
					+ '</div>'
			})
			document.querySelector('.admin_box').innerHTML = html;
		}
		
	})
}







// 어드민 버튼 생성
admin_btn();
function admin_btn(){
	
	$.ajax({
		url		: "/TeamProjectChatting/board/adminbtn",
		success	: re => {
			if( re == 'true' ){
				let btn = '<button onclick="adminpage()"> 관리자 페이지 </button>';
		
				document.querySelector('.admin_btn').innerHTML = btn;
			}else{
				
			}
		}
	})
	
} // admin_btn

// 어드민 페이지로 이동
function adminpage(){
	location.href = "/TeamProjectChatting/member_View/admin/user_manage.jsp";
	
} // adminpage e
