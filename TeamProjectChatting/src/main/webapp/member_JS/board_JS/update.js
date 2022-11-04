

bview()
function bview(){
	$.ajax({
		url:"",
		success : function(re){
			let board = JSON.parse(re)
			console.log(board)
			console.log(board.bfile)
			console.log(board.btitle)
			console.log(board.bcontent)
			
			document.querySelector('.btitle').value = board.btitle;
			// 썸머노트는 내용 저장시 html 형식으로 저장하기 때문에
			document.querySelector('.bcontent').innerHTML = board.bcontent;
			if(board.bfile !== 'null'){ // 첨부파일 존재하면
				let filedelete = board.bfile+'<button type="button" onclick="bfiledelete()">삭제</button>'// html 구성
				document.querySelector('.oldbfilebox').innerHTML = filedelete; // 위에 구성된 html 넣기
			}
			
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
		}
	})
}


function bfiledelete(){
	// 1.
	if(confirm('삭제 하시겠습니까?')){
	
	$.ajax({
		url:"",
		success : function(re){
			if(re === 'true'){
				alert("첨부파일 삭제")
				// 현재페이지 새로고침 location.reload()
				
				// 특정태그만 새로고침 : JQUERY ($)
				// console에 $ define 이면 JQUERY 스크립트가 안먹혔다는 것
				document.querySelector('.oldbfilebox').innerHTML ='' // 공백처리
				// 									띄어쓰기 주의
				$("#oldbfilebox").load(location.href+' #oldbfilebox')
				
			}else{
				alert("첨부파일 삭제 실패")
			}
			
			}
		})
	}
}


function bupdate(){
	let form = document.querySelector('form')
	let formdata = new FormData(form)
	console.log(formdata)
	$.ajax({
		url:"",
		data : formdata,
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