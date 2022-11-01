<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 폰트어썸 [아이콘 ] -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
</head>
<body>
	
	 <% 
   		int user_num = (Integer)session.getAttribute("user_num");
  	 %>
   <input type="hidden" value="<%=user_num%>"> 
   
   <% if( user_num == 0 ) { System.out.println("로그인넘버111 : "+user_num); %>
   <% } else {
      System.out.println("로그인넘버222 : "+user_num)   ;
   }%>


   <script type="text/javascript" src="/TeamProjectChatting/member_JS/login.js"></script>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	
</body>
</html>