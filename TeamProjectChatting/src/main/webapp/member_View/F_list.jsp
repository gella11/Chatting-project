<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="../CSS/member_CSS/F_list.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">

</head>
<body>
	
	<div class="col-sm-6 offset-3 chattingbox"> <!--  채팅 구역  -->
	
		<div  class="row">
			<table class="chattinglist">

			</table>
		</div>
		
		<div class="row"> 
			<div class="col-sm-4">	<!-- 접속회원목록 -->
			</div>
			<div class="col-sm-8">  							<!--  친구목록 창 -->
				<table class="f_list my-3">
		
				</table> 
			</div>
		</div> <!-- 채팅 구역 end  -->
		
		
	</div> <!--  컨테이너 end  -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="../member_JS/F_list.js" type="text/javascript"></script>
	
</body>
</html>