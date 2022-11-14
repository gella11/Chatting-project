<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<!--  썸머노트 API -->
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	
</head>
<body>
	<div class="tab_list2 aaa"><a onclick="pagechange2('board/list2.jsp')"><i class="fas comment">←</i></a></div>
	<%@include file="../header.jsp"%>

	<div class="webbox"> 
		<h3> 글쓰기 </h3>
		<form enctype="application/x-www-form-urlencoded">
			제목 : <input type="text" name="b_title"> <br>
			
			<textarea id="summernote" name="b_content"></textarea>
			<!-- 내용 : <input type="text" name="bcontent"> <br> -->
			
			첨부파일: <input type="file" name="b_file" id="b_file"> <br>
			<button type="button" onclick="bwirte()"> 쓰기 </button>
			<!-- form 쩨로 넘기려면 버튼에 타입 button 써야해요 -->
		</form>
		
		<div> <!-- 첨부파일 미리보기 -->
			<img alt="" src="" id="b_filepre">
		</div>
	</div>


	<!-- include summernote css/js -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<script src="lang/summernote-ko-KR.js"></script>
	
	<script src="../../member_JS/board_JS/write2.js" type="text/javascript"></script>

</body>
</html>