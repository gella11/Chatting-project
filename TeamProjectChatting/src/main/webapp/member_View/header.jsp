<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
   
    <% 
         int user_num = (Integer)session.getAttribute("user_num");
      %>
   <input type="hidden" class="mid" value="<%=user_num%>"> 
   
   <% if( user_num == 0 ) { System.out.println("로그인넘버111 : "+user_num); %>
   <% } else {
      System.out.println("로그인넘버222 : "+user_num)   ;
   }%>


   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
   <script type="text/javascript" src="/TeamProjectChatting/member_JS/login.js"></script>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>

</body>
</html>