<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../CSS/member_CSS/dash.css">
</head>
<body>
	
	<%@include file="header.jsp"%>
	<div class="c_container">
		<!-- 본문 -->
		<div id="mainbox">
			<!-- 사이드바에서 메뉴 클릭시 jsp가 표시되는 구역 -->
			<jsp:include page="F_list2.jsp" /> 
						<%-- <%@include file="F_list.jsp"변수 중복선언?? %> --%>
		</div>
		
		<!-- 본문 -->
		<div id="boardbox">
			<!-- 사이드바에서 메뉴 클릭시 jsp가 표시되는 구역 -->
			<jsp:include page="board/list2.jsp" />
		</div>
	</div> <!--  컨테이너 e  --> 
	
	

	
	<script src="../member_JS/dash.js" type="text/javascript"></script>
</body>
</html>