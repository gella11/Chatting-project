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


	<div class="webbox"> 
		<h3> 글쓰기 </h3>
		<form enctype="application/x-www-form-urlencoded">
			제목 : <input type="text" name="btitle"> <br>
			
			<textarea id="summernote" name="bcontent"></textarea>
			<!-- 내용 : <input type="text" name="bcontent"> <br> -->
			
			첨부파일: <input type="file" name="bfile"> <br>
			<button type="button" onclick="bwirte()"> 쓰기 </button>
			<!-- form 쩨로 넘기려면 버튼에 타입 button 써야해요 -->
		</form>
	</div>
<script>

 history.pushState(null, null, '6');  //data, title, url 의 값이 들어가게 됩니다. 비워두면 이벤트 발생의 플래그 정도로 사용 할 수 있습니다.

                                         //기존페이지 이외에 입력한 URL로 페이지가 하나 더 만들어지는 것을 알 수 있습니다.



 window.onpopstate = function(event) {  //뒤로가기 이벤트를 캐치합니다.

  history.back();   // pushState로 인하여 페이지가 하나 더 생성되기 떄문에 한번에 뒤로가기 위해서 뒤로가기를 한번 더 해줍니다.
  pagechange2('board/view2.jsp')
  console.log('뒤로가기 체크'); 

 };

</script>

	<!-- include summernote css/js -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<script src="lang/summernote-ko-KR.js"></script>
	
	<script src="../member_JS/update.js" type="text/javascript"></script>

</body>
</html>