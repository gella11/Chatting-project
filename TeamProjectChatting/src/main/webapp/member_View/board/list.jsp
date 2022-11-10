<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../../CSS/board_CSS/list.css">
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
	
	

<script src="../../member_JS/board_JS/list.js" type="text/javascript"></script>

</body>
</html>