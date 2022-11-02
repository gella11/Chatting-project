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
	<%@include file="header.jsp"%>
	
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
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
  친구추가버튼(숨길예정)
</button>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="staticBackdropLabel"> 친구를 추가하세요 ! </h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <span>친구 e-mail 입력 : </span> <input type="email" class='f_email'>
        <button class="form-control" type="button" onclick="friendadd()">친구 추가</button>
      </div>
    </div>
  </div>
</div>

	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<script src="../member_JS/F_list.js" type="text/javascript"></script>
</body>
</html>