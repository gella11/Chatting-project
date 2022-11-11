<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<title>달력이요</title>
   
   <!-- 예약 아이콘 -->
   <script src="https://kit.fontawesome.com/b8a7fea4d4.js"></script>
   <!-- 아이콘 -->
   <link rel="shortcut icon" type="image/x-icon" href="../image/logo.jpg">
   <!-- 구글 웹폰트 -->
   <link href="https://fonts.googleapis.com/css2?family=Noto+Serif+HK&display=swap" rel="stylesheet">
   <!-- 부트 스트랩 -->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
   
   <link rel="stylesheet" href="https://unpkg.com/destyle.css@1.0.5/destyle.css">
   <link href="../CSS/calender_CSS/calender2.css?after" rel="stylesheet">
   
<title>Insert title here</title>
</head>
<body>
	<div>
		<div class="cal">
	      <div class="head">
	         <div class="left_btn"> < </div>
	            <ul>
	               <h3></h3>
	               <h2></h2>
	            </ul>
	         <div class="right_btn"> > </div>
	      </div>
	      <div class="week">
	         <div class="sunday">Sunday</div>
	         <div>Monday</div>
	         <div>Tuesday</div>
	         <div>Wednesday</div>
	         <div>Thursday</div>
	         <div>Friday</div>
	         <div onclick="abcd()">Saturday</div>
	      </div>
	      <div class="days">
	      <div class="daysdays">
	      </div>
	   </div>
	   
	   
	   <!-- Button trigger modal -->
		<button style="display: none;" type="button"  class="btn2 btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal2">
		  Launch demo modal
		</button>
		
		<!-- Modal -->
		<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		       		 <form class="calender1">
						내용 : <input class="t_content form-control" type="text" name="t_content"  value="ddd"> 
					</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" onclick="calregist()">Save changes</button>
		      </div>
		    </div>
		  </div>
		</div>
	   
	   </div>
   </div>
   
   
   
   
   
   
   
   
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
   <!-- 부트스트랩 -->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<script src="../member_JS/calender_JS/calender2.js" type="text/javascript"></script>	
</body>
</html>