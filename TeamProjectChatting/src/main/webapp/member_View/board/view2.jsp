<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../CSS/board_CSS/view2.css?after">

</head>
<body>

	<%@include file="../header.jsp"%>
		
	
	<div class="view_box">

	</div> <!-- view_box e -->
	<div class="replylist"> </div> <!-- 댓글 -->
	

<!-- 수정 모달창 - 혜영 11/10 수정 -->
<div class="container2">
  <div class="interior">
    <a class="btn" href="#open-modal" style="display: none"></a>
  </div>
</div>
<div id="open-modal" class="modal-window">
  <div>
   
    <a href="#" title="Close" class="modal-close"> X </a>
    <div class="modal_title"> 글 상세보기 </div>
    <br>
    <div class="form_box">
    	<form class="update_form">
			<table class="table">
			<tr>
				<td> 게시글번호 </td> <td> <input type="text" readonly="readonly" name="b_no" class="b_no"> </td>
			</tr>
			<tr>
				<td> 제목 </td> <td> <input type="text" name="b_title" class="update_title"> </td>
			</tr>
			<tr>
				<td> 내용 </td> <td> <textarea rows="" cols="" name="b_content" class="update_content"></textarea> </td>
			</tr>
			<tr>
				<td> 첨부파일 </td> <td> <input type="file" id="b_file" name="b_file" class="update_file"> </td>
			</tr>       
			</table>		
		</form>
    </div>
   <div class="modal_btn_box">
	<!--	<button type="button"> 취소 </button>   -->
		<button type="button"onclick="b_update()"> 저장 </button>
		
   </div>
   
  </div>
</div>










<script>

   //data, title, url 의 값이 들어가게 됩니다. 비워두면 이벤트 발생의 플래그 정도로 사용 할 수 있습니다.

                                         //기존페이지 이외에 입력한 URL로 페이지가 하나 더 만들어지는 것을 알 수 있습니다.



 window.onpopstate = function(event) {  //뒤로가기 이벤트를 캐치합니다.
	 history.pushState(null, null, '3');
  history.back();   // pushState로 인하여 페이지가 하나 더 생성되기 떄문에 한번에 뒤로가기 위해서 뒤로가기를 한번 더 해줍니다.
  pagechange2('board/list2.jsp')
  console.log('뒤로가기 체크'); 

 };

</script>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../member_JS/board_JS/view2.js" type="text/javascript"></script>

</body>
</html>