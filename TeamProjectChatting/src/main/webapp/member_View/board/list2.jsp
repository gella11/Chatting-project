<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../CSS/board_CSS/list2.css">
</head>
<body>

	<%@include file="../header.jsp"%>
	<input class="user_num" type="hidden" value="<%=user_num%>">
	<div class="list_con">
		
			
		<div class="category"> </div>
		<div class="write"> </div>
		<div class="admin_btn"> </div>
		
		<div>
			게시물 수 <span class="total_size"> </span>		
		</div>
		
		<div class="admin_box">
		</div>
		
		<div class="list_box">
		</div>
				
		<div>
			
			
			<span>
				<select class="list_size" onchange="blist_size()">
					<option vlaue="5"> 5 </option>
					<option vlaue="10"> 10 </option>
					<option vlaue="15"> 15 </option>
					<option vlaue="20"> 20 </option>
				</select>
			</span>
				
				
			<!-- 페이징 처리 출력 부분 -->
			<span class="pagebox"> 
			</span>
			
			<!-- 검색 처리 출력 부분 -->
			<div> 
				<select class="key">
					<option value="b_title"> 제목 </option>
					<option value="b_content"> 내용 </option>
					<option value="user_name"> 작성자 </option>
				</select>
				<input class="keyword" type="text" placeholder="검색어">
				<button type="button" onclick="bsearch()"> 검색 </button>
			</div>
		</div>
		
	</div>
	
	<script>

   //data, title, url 의 값이 들어가게 됩니다. 비워두면 이벤트 발생의 플래그 정도로 사용 할 수 있습니다.

                                         //기존페이지 이외에 입력한 URL로 페이지가 하나 더 만들어지는 것을 알 수 있습니다.



 window.onpopstate = function(event) {  //뒤로가기 이벤트를 캐치합니다.
	 history.pushState(null, null, '2');
  history.back();   // pushState로 인하여 페이지가 하나 더 생성되기 떄문에 한번에 뒤로가기 위해서 뒤로가기를 한번 더 해줍니다.
  pagechange('c_list2.jsp')
  console.log('뒤로가기 체크'); 

 };

</script>

<script src="../member_JS/board_JS/list2.js" type="text/javascript"></script>

</body>
</html>