<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../CSS/board_CSS/list2.css?after">
</head>
<body>

	<%@include file="../header.jsp"%>
	<input class="user_num" type="hidden" value="<%=user_num%>">
	<div class="list_container">
	<div class="list_con">
		
			
		<div class="category"> </div>
		<div class="write"> </div>
		<div class="admin_btn"> </div>
		
		<div class="total_size_box">
			전체 게시물 <span class="total_size"> </span> 건		
		</div>
		
		<div class="admin_box">
		</div>
		
		<div class="list_box">
		</div>
				
	
			
		<div class="page_con">
			<div class="page_con2">
				<span>
					<select class="list_size" onchange="blist_size()">
						<option vlaue="5"> 4 </option>
						<option vlaue="10"> 8 </option>
						<option vlaue="15"> 12 </option>
						<option vlaue="20"> 16 </option>
					</select>
				</span>
					
					
				<!-- 페이징 처리 출력 부분 -->
				<span class="pagebox""> 
				</span>
			</div>	
			
			<!-- 검색 처리 출력 부분 -->
			<div class="page_con3"> 
				<select class="key">
					<option value="b_title"> 제목 </option>
					<option value="b_content"> 내용 </option>
					<option value="user_name"> 작성자 </option>
				</select>
				<input class="keyword" type="text" placeholder="검색어">
				<button type="button" onclick="bsearch()" class="bsearch_box"> 검색 </button>
			</div>
			
			
		</div>
		
	</div>
	</div>

<script src="../member_JS/board_JS/list2.js" type="text/javascript"></script>

</body>
</html>