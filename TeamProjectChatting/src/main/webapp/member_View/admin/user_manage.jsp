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
	response.sendRedirect("/TeamProjectChatting/member_View/login.jsp");
	}
	%>
	<button onclick="pagechange2('+admin/user_manage+')"> 관리자 페이지 </button>
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
					<h3 class="fw-bold title">인사관리</h3>
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
							<input type="text" id="category" class=" form-control"
								placeholder="추가할 부서명을 입력해주세요.">
							<button type="button" id="category" class="form-control"
								onclick="addcategory()">추가하기</button>
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
	<div class="interior"></div>
	<div id="open-modal" class="modal-window">
		<div>
			<a href="#" title="Close" class="modal-close">Close</a>
			<h1>사원관리</h1>
			<div>
				<form class="updateform">
					사원번호<input class="user_num form-control" type="text"
						name="user_num" readonly="readonly"> 사원이름<input
						class="user_name form-control" type="text" name="user_name"
						readonly="readonly"> 비밀번호<input
						class="user_pw form-control" type="text" name="user_pw">
					이메일<input class="user_email form-control" type="text"
						name="user_email"> 연락처<input
						class="user_phone form-control" type="text" name="user_phone">
					부서<input class="user_department form-control" type="text"
						name="user_department">
						직책<input class="user_position form-control" type="text"
						name="user_position">
						 생년월일<input
						class="user_birth form-control" type="text" name="user_birth"
						readonly="readonly"> 입사날<input
						class="user_date form-control" type="text" name="user_date"
						readonly="readonly">
					<button id="edit" class="wrap button" type="button"
						onclick="edit_user()">수정하기</button>

				</form>
			</div>
			<br>


		</div>
	</div>
	<!--================================================================================================  -->
	<div style="display: none;" class="Click-here">Click Here</div>
	<div class="custom-model-main">
		<div class="custom-model-inner">
			<div class="close-btn">×</div>
			<div class="custom-model-wrap">
				<div class="pop-up-content-wrap" style="height: 476px;">
					<table class="detail_table table">

					</table>
					<div style="width: 500px; height: 1200px;">
						<!--차트가 그려질 부분-->
						<canvas id="myChart"></canvas>
					</div>

				</div>
			</div>
		</div>
		<div class="bg-overlay"></div>
	</div>

	<!--===================================================================================================  -->






	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
	<script src="/TeamProjectChatting/member_JS/board_JS/admin_JS/admin.js"
		type="text/javascript"></script>
</body>
</html>