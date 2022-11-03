<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<%@include file="header.jsp"%>

<div class="flist_box">



			<div class="friend_box"> <!-- 친구 목록 출력 -->
				<div class="friend_title">
					친구 목록
				</div>

				<div class="friendlist_box">

					<div class="f_list">
						<!-- 친구 리스트 js에서 가져와서 출력되는 곳 -->
						<div class="friend_list">
							<div class="friend_con_box" onclick="chatting('+l.user_num+')" id='+l.user_num+' >
								<div>
									<img class="friend_img" alt="" src="../img/망햄터.png"> <!-- 친구 프로필 이미지 -->
								</div> 
								<div class="friend_text_box"> 
									<div class="friend_name"> 이햄터 </div> <!-- 친구 이름 -->
									<div class="friend_msg"> 망구라안녕 망글망글랄그글 </div> <!-- 친구 프로필 메세지 -->
								</div>
							</div>
						</div>
						<!-- 친구 리스트 js에서 가져와서 출력되는 곳 e -->
					</div>
					
				</div> <!-- friend_box e -->
			</div>	<!-- flist_box e -->	
	</div>		
</body>
</html>