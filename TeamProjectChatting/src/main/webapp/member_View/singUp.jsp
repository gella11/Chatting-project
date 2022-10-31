<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> TPC 회원가입 </title>
	
	<!-- 뷰포트 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 폰트어썸 -->	
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
	<!-- CSS 초기화 -->
	<link rel="stylesheet" href="https://unpkg.com/destyle.css@1.0.5/destyle.css">
	<!-- 부트스트랩 CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<!-- CSS -->
	<link href="../CSS/member_CSS/singUp.css" rel="stylesheet">
</head>
<body>
	
	<div class="wrap">
	
		<div class="container">
			
			<div class="sign_up_box">
			
			<div class="su_title"> 신규 회원가입 </div>
				<div class="info_box">
					
				<form action="" method="post">
						
					<!-- 이름 -->
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="user_name" name="user_name" onkeyup="name_check()" placeholder="Name">
						<label for="user_name"> Name </label>
						<div class="check_icon1">  </div>
					</div>
						
					<!-- 전화번호 -->
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="user_phone" name="user_phone" onkeyup="phone_check()" placeholder="Phone">
						<label for="user_phone"> Phone </label>
						<div class="check_icon2">  </div>
					</div>
					
					<!-- 이메일 -->
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="user_email" name="user_email" onkeyup="email_check()" placeholder="Email">
						<label for="user_email"> Email </label>
						<div class="check_icon3">  </div>
					</div>
					
					<!-- 비밀번호 -->
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="user_pw" name="user_pw" onkeyup="pw_check1()" placeholder="Password">
						<label for="user_pw"> Password </label>
						<div class="check_icon4">  </div>
					</div>

					<!-- 비밀번호확인 -->
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="user_pw_confirm" name="user_pw_confirm" onkeyup="pw_check2()"placeholder="Password Check">
						<label for="user_pw_confirm"> Password Check </label>
						<div class="check_icon5">  </div>
					</div>
						
					<div class="btn_box">
						<button class="sign_up_btn" onclick="sign_up()" type="button"> 회원가입 </button>
					</div>
			
				</form>
					
				</div>
			</div>
		
		</div> <!-- container e -->
	</div> <!-- wrap e -->
	
	
	<!-- JQUERY 라이브러리 -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 부트스트랩 JS -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<!-- JS -->
	<script src="../member_JS/singUp.js" type="text/javascript"></script>
	
</body>
</html>