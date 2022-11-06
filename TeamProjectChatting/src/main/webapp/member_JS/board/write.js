

// 썸머노트 실행
$(document).ready(function() {
  $('#summernote').summernote(
		{
			placeholder : '내용입력',
  			maxHeight : null,
  			minHeight : 300,
  			lang : 'ko-KR'
		}
	);
});

// 글 쓰기
function bwirte(){
	
	let form = document.querySelector('form')
	console.log(form)
	
	let formdata = new FormData(form)
	console.log(formdata)
	
	$.ajax({ //ajax 통신 전송타입 : 문자열
		url : "/TeamProjectChatting/write",
		data : {"formdata" : formdata , "type" : 1},	//ajax 기본값으로 form 전송 불가능 그래서 추가할 것이 있음
		contentType : false, // 전송할 데이터의 타입 form 형식("application/x-www-form-urlencoded") 기본값을 false로 바꿔줌 multipart/form-data 이용할겨
		processData : false, // 전송시 사용되는 타입 기본값 String 을 false로 바꿔줌 전송url에 데이터명시 중. 첨부파일 주소 안보여줄려고. 
		type : 'POST',		 // 메소드 방식 [get(첨부파일은 get안됨) , post(첨부파일은 무조건 post)]
		success : function(re){
			if(re === 'true'){
				alert(re)
				location.href="list.jsp"
			}else{
				alert('글 등록 실패')
			}
		}
	})
	
}

// 선택 글 삭제
function bdelete(bno){
	$.ajax({
		url:"/TeamProjectChatting/write",
		data : {"bno" : bno , "type" : 2},
		success : function(re){
			if(re === 'true'){
				alert('글 삭제했습니다.')
				location.href="../board/list.jsp"
			}
			else{
				alert('작성하신 글이 아닙니다.')
			}
		}
	})
}

// 선택 글 수정 
function bupdate(){
	let form = document.querySelector('form')
	let formdata = new FormData(form)
	console.log(formdata)
	$.ajax({
		url:"/TeamProjectChatting/write",
		data : {"formdata" : formdata , "type" : 3},
		// 첨부파일시
		type : "POST",
		contentType : false,
		processData : false,
		success : function(re){
			if(re==='true'){
				alert('수정성공')
				location.href='/jspweb/board/view.jsp'
			}else{
				alert('수정실패')
			}
		}
	})
}

// 선택 글 상세보기
function viewload(bno){
	$.ajax({
		url:"/TeamProjectChatting/write",
		data:{"bno" : bno},
		success : function(re){
			alert("선택한 글 입니다.")
			//location.href="http://localhost:8081/jspweb/board/view.jsp"
		}
	})
}