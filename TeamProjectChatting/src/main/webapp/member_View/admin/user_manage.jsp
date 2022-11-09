<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/TeamProjectChatting/CSS/board_CSS/admin_CSS/admin.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">

<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">

<!-- 구글 폰트 노토산스 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">


</head>
<body class="bodybg">
	<%@include file="../header.jsp"%>
	<%
	if (user_num == 1) {
		System.out.println("관리자 넘버 : " + user_num);
	%>
	<%
	} else {
	response.sendRedirect("../board/login.jsp");
	System.out.println("로그인넘버222 : " + user_num);
	}
	%>
	<div class="c_container">
		<!--  컨테이너  -->
		<div class="chatting_rbox">
			<!-- 채팅방 박스 -->

			<!-- Modal -->

		</div>
		<!-- 채팅방 박스 e -->

		<div class="board_box">
			<!-- 게시판 -->
			<div>
				<div class="board_title">
					<h3 class="fw-bold title">회원관리</h3>
				</div>
				<button id="modal-btn" class="button2">부서 추가</button>

				<div id="my-modal" class="modal">
					<div class="modal-content2">
						<div class="modal-header">
							<h2>부서관리</h2>
							<span class="close">&times;</span>
						</div>
						<div>
							<table class="add_delete showcate table">
							</table>
							<input type="text" id="category" class=" form-control" placeholder="추가할 부서명을 입력해주세요." >
							<button type="button" id="category" class="form-control" > 추가하기</button>
						</div>
						<div class="modal-footer"></div>
					</div>
				</div>
				<div>
					<table class="table alluser">
					</table>
				</div>
			</div>
		</div>
		<!-- 게시판 e -->

	</div>
	<!--  컨테이너 e  -->
	<!-- Button trigger modal -->
	<button type="button" style="display: none" class="btn btn-primary"
		data-bs-toggle="modal" data-bs-target="#exampleModal1"
		onclick="edit()"></button>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal1" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">사원정보 수정</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form class="updateform">
						사원번호<input class="user_num form-control" type="text"
							name="user_num" readonly="readonly"> 사원이름<input
							class="user_name form-control" type="text" name="user_name">
						비밀번호<input class="user_pw form-control" type="text" name="user_pw">
						이메일<input class="user_email form-control" type="text"
							name="user_email"> 연락처<input
							class="user_phone form-control" type="text" name="user_phone">
						부서<input class="user_department form-control" type="text"
							name="user_department"> 생년월일<input
							class="user_birth form-control" type="text" name="user_birth"
							readonly="readonly"> 입사날<input
							class="user_date form-control" type="text" name="user_date"
							readonly="readonly">
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary" onclick="edit_user()">변경사항
						저장</button>
				</div>
			</div>
		</div>
	</div>





	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
	<script src="/TeamProjectChatting/member_JS/board_JS/admin_JS/admin.js"
		type="text/javascript"></script>
</body>
</html>