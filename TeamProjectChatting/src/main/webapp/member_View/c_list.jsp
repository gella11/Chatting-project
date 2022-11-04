<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../CSS/member_CSS/c_list.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">

<!-- 구글 폰트 노토산스 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">


</head>
<body class="bodybg">
	<%@include file="header.jsp"%>
	
	
		<div class="row"> 
			<div class="col-sm-4">	<!-- 접속회원목록 -->
			</div>
			<div class="col-sm-8">  							<!--  친구목록 창 -->
				<table class="f_list my-3 clist">
					
				</table> 
			</div>
		</div> <!-- 채팅 구역 end  -->
	<button type="button" style='display: none' class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
	  강제클릭버튼 안보이는부분.
	</button>
	
	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h1 class="modal-title fs-5" id="staticBackdropLabel">Modal title</h1>
	        <button type="button" class="btn-close" onclick='socketclose()' data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <div class='contentbox'>
	           채팅방 구현할자리.
	        </div>
	        <textarea onkeyup="enterkey()" class='msgbox form-control' rows="" cols=""></textarea>
	        <button class="form-control" type="button" onclick="send()">보내기</button>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" onclick='socketclose()' data-bs-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary">Understood</button>
	      </div>
	    </div>
	  </div>
	</div>
      
      

   
   
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
   <script src="../member_JS/c_list.js" type="text/javascript"></script>
</body>
</html>