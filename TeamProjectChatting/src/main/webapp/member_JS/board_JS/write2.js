
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


function bwirte(){
	
	let form = document.querySelector('form')
	console.log(form)
	
	let formdata = new FormData(form)
	console.log(formdata)
	
	$.ajax({ //ajax 통신 전송타입 : 문자열
		url : "/TeamProjectChatting/write",
		data : formdata,	//ajax 기본값으로 form 전송 불가능 그래서 추가할 것이 있음
		contentType : false, // 전송할 데이터의 타입 form 형식("application/x-www-form-urlencoded") 기본값을 false로 바꿔줌 multipart/form-data 이용할겨
		processData : false, // 전송시 사용되는 타입 기본값 String 을 false로 바꿔줌 전송url에 데이터명시 중. 첨부파일 주소 안보여줄려고. 
		type : 'POST',		 // 메소드 방식 [get(첨부파일은 get안됨) , post(첨부파일은 무조건 post)]
		success : function(re){
			if(re === 'true'){
				alert(re)
				//location.href="/TeamProjectChatting/member_View/board/list.jsp"
				pagechange2('board/write2.jsp')
			}else{
				alert('글 등록 실패')
			}
		}
	})
	
}

// 2. 첨부파일 등록(변경)했을 때 미리보기
let b_file = document.querySelector('#b_file')
	b_file.addEventListener('change',function(e){	// e 꼭 넣어야함 e:event 객체(이벤트 정보[target] 여기선 change이벤트 , 누가 뭐를[]data])
	// 1) js 파일 클래스 []
	let file = new FileReader() // 객체생성
	// 2) 해당 펌부된 파일 경로 알기
	file.readAsDataURL(e.target.files[0])
	// 3) 이미지 태그에 첨부된 이미지 대입
	file.onload = function(e){ // e는 onload 이벤트 정보
		document.querySelector('#b_filepre').src = e.target.result
	}
})