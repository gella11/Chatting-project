//
//////////// 본문 페이지 전환 이벤트 ///////////
function pagechange( page ){
	$("#mainbox").load( page ) // 특정 태그에 해당 파일 로드 [ jquery ]
}

function pagechange2( page ){
	$("#boardbox").load( page ) // 특정 태그에 해당 파일 로드 [ jquery ]
}

