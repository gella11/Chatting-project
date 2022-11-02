<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> TPC 회원가입 </title>
	
	<!-- 뷰포트 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- CSS 초기화 -->
	<link rel="stylesheet" href="https://unpkg.com/destyle.css@1.0.5/destyle.css">
	
	<!-- 구글 폰트 노토산스 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
	
	<!-- 부트스트랩 CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<!-- CSS -->
	<link href="../CSS/member_CSS/singUp.css" rel="stylesheet">
	
	
</head>
<body>
	
	<div class="wrap">
	
		<div class="container">
			
			<div class="sign_up_box">
			
			<div class="su_title">
				<div> </div> <!-- 바 자리 -->
				<div>신규 회원가입</div> <!-- 메인타이틀 -->
				<div>카카오톡에서 로그인할 정보를 입력해주세요</div> <!-- 서브타이틀 -->
			</div>
				
			<div class="info_box">
				
				<form action="" method="post">
						
					<!-- 이름 -->
				    <div class="group">      
				      <input type="text" id="user_name" name="user_name" onkeyup="name_check()" required>
				      <span class="highlight"></span>
				      <span class="bar"></span>
				      <div class="check_icon1">  </div>
				      <label>Name</label>
				    </div>
				    
				    <!-- 전화번호 -->  
				    <div class="group">      
				      <input type="text" id="user_phone" name="user_phone" onkeyup="phone_check()" required>
				      <span class="highlight"></span>
				      <span class="bar"></span>
				      <div class="check_icon2">  </div>
				      <label>Phone</label>
				    </div>
				    
				    <!-- 이메일 -->  
				    <div class="group">      
				      <input type="text" id="user_email" name="user_email" onkeyup="email_check()" required>
				      <span class="highlight"></span>
				      <span class="bar"></span>
				      <div class="check_icon3">  </div>
				      <label>Email</label>
				    </div>
				    
				    <!-- 비밀번호 -->
				    <div class="group">      
				      <input type="password" id="user_pw" name="user_pw" onkeyup="pw_check1()" required>
				      <span class="highlight"></span>
				      <span class="bar"></span>
				      <div class="check_icon4">  </div>
				      <label>Password</label>
				    </div>
				    
				    <!-- 비밀번호확인 -->
				    <div class="group">      
				      <input type="password" id="user_pw_confirm" name="user_pw_confirm" onkeyup="pw_check2()" required>
				      <span class="highlight"></span>
				      <span class="bar"></span>
				      <div class="check_icon5">  </div>
				      <label>Password Check</label>
				    </div>
				</form>
				<!-- 버튼 -->	
				<div class="btn_box">
					<button class="sign_up_btn" onclick="sign_up()" type="button"> 회원가입 </button>
				</div>
			
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