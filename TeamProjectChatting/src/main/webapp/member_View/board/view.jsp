<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../../CSS/board_CSS/view.css">

</head>
<body>

	<%@include file="../header.jsp"%>
		
	
	<div class="view_box">
		<!-- <div>
			<div class="view_profile">
				<div>
					<img class="user_profile" alt="" src="../../img/망곰이.png">
				</div>
				
				<div class="view_title"> 
					<div class="user_name"> 김망곰 </div>
					<div class="b_date"> 2022-11-06 </div>
				</div>
				<div class="view_subtitle"> 
					<div class="user_department"> 인사과 </div>
				</div>
			</div>
				
			<div class="content_box">			
				<div class="b_title"> 제 58회 가을 워크샵 안내 </div>
		
				<div class="b_content"> 올해 가을은, 단풍잎을 보며 즐기는 온전한 나만의 시간이 될 것입니다.<br>
				같은 취향을 가진 새로운 사람들과 만남을 즐겨보세요.<br>
				전통주를 빚으며 관계의 깊이가 함께 익어가는 것을 느껴보세요.<br>
				또 다른 시작을 위해 오늘은 잠시 일시정지 버튼을 눌러주세요. </div>
			</div>	
			
			<div class="file_box">
				<span> 첨부파일 </span>
				<span class="b_file"> 파일명 </span>
			</div>
			
		</div> 
			
	
		<div class="reply_box">
			<textarea style="resize: none;" rows="" cols="" class="r_content"></textarea>
			<button type="button" class="reply_btn"> 등록 </button>
		</div> -->
		
		
		
	</div> <!-- view_box e -->

	

	<!-- Button trigger modal -->
	<button style="display: none" type="button" class="b_update btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
	</button>
	
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      
	      <div class="modal-body"> 	
	        <!-- 모달 본문 -->
	        
	        <form class="update_form">
				
				게시글번호 	<input type="text" readonly="readonly" name="b_no" class="b_no"><br>        
				제목 		<textarea rows="" cols="" name="b_title" class="b_title"></textarea><br>
				내용 		<input type="text" name="b_content" class="b_content"><br>
				첨부파일 	<input type="file" id="b_file" name="b_file" class="b_file"><br>
				
			</form>
	        
	        <!-- ------- 모달 본문 e -------- -->
	        
	        
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"> 취소 </button>
	        <button type="button" class="btn btn-primary" onclick="b_update()"> 저장 </button>
	      </div>
	    </div>
	  </div>
	</div>










<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../../member_JS/board_JS/view.js" type="text/javascript"></script>

</body>
</html>