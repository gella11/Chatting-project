//
//////////// 본문 페이지 전환 이벤트 ///////////
function pagechange( page ){
	$("#mainbox").load( page ) // 특정 태그에 해당 파일 로드 [ jquery ]
}

function pagechange2( page ){
	$("#boardbox").load( page ) // 특정 태그에 해당 파일 로드 [ jquery ]
}

function move(){
    //....요 위에서 ajax로 뭔가 했다고 가정 합니다.

    let hashUrl = '';
    if(document.URL.indexOf('#') > -1){
        let url = document.URL.substring(0, document.URL.indexOf('#'))
        hashUrl = url + '#'+ '에이작스로 한행동추가';
    } else {
        hashUrl = document.URL += '#' + '에이작스로 한행동추가';
    }
    window.location.replace(hashUrl);
}
let page_history = document.URL.substring(document.URL.indexOf('#')+1);
let historyData = page_history.split('_');  //이런식으로 사용 할 수 있겠네요!