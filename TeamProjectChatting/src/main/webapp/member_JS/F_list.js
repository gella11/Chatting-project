
//10/28 도현,상진 친구리스트 가져오기.
f_list()
function f_list(){
	$.ajax({
		url : "/TeamProjectChatting/F_list",
		success : function(re){
			let list = JSON.parse(re)
			let html = '';
			for(let i = 0 ; i<list.length; i++){
				let l = list[i]
				console.log(l.user_num)
				html +=  '<div class="friend_list">'
					+ '<div class="friend_con_box" onclick="chatting('+l.user_num+')"  id='+l.user_num+' >'		
					+ '<div>'			
					+ '<img class="friend_img" alt="" src="../img/망햄터.png">' + l.user_profile	// 이 라인에 프로필 이미지 들어가야돼요		
					+ '</div>'			
					+ '<div class="friend_text_box">'			
					+ '<div class="friend_name">'+l.user_name+'</div>'				
					+ '<div class="friend_msg">'+l.user_msg+'</div>'				 
					+ '</div>'			
					+ '</div>'		
					+ '</div>'
			}
			document.querySelector('.f_list').innerHTML = html;
		}
	})
}
//10/28 도현,상진 채팅방생성 후 채팅창으로 넘어가기.
function chatting(num){
   let chattingnum = num;
   $.ajax({
      url : "/TeamProjectChatting/F_list",
      data : {"chattingnum" : chattingnum, "option" : 1},
      type:"POST",
      success : function(re){
		location.href='/TeamProjectChatting/member_View/c_list.jsp';
       }
   })
}
//11/2 도현 모달에서 친구추가하기.
function friendadd(){
	let email = document.querySelector('.f_email').value;
	$.ajax({
      url : "/TeamProjectChatting/F_list",
      data : {"email" : email, "option" : 4},
      type:"POST",
      success : function(re){
		if(re=='true'){
			location.reload();
		}else{alert('이메일을 확인해주세요')}
      }
   })
}

//11/2 도현 추천친구찾기. 나를 친구추가했지만 , 내가 친구추가안한사람

// 친구목록 & 채팅방 하단 탭 
let icon_box = document.querySelector('.icon_box')
let container = document.querySelector('.container')

//변환이벤트
function tabchange(page){
	$(".container").load(page)// 특정 태그에 해당 파일 로드 [ jquery ]
}
function addbtn(){
	document.querySelector('.friendaddbtn').click();
}
//11/2 도현 모달에서 친구추가하기.
function friendadd(){
   let email = document.querySelector('.f_email').value;
   $.ajax({
      url : "/TeamProjectChatting/F_list",
      data : {"email" : email, "option" : 4},
      type:"POST",
      success : function(re){
      if(re=='true'){
         location.reload();
      }else{alert('이메일을 확인해주세요')}
      }
   })
}
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////

categorylist()
function categorylist(){
	$.ajax({
		url : "/TeamProjectChatting/categorynum",
		success: (re)=>{
			let category_num = JSON.parse(re)
			let html = ''
			category_num.forEach(c =>{
				html +=  '<button onclick="categoryboard('+c.c_no+')"> '+c.c_name+' </button>'	
			})
			
		}
	})
	
    // 카테고리 정보를 반복문 돌려서 카테고리별 버튼 innerHTML
    document.querySelector('.categorylist').innerHTML = html
    // 카테고리 별 글쓰기 버튼 innerHTML
    let writebtn = '<button onclick="writebtn('+category_num.c_no+')"> 글쓰기 </button>'
    document.querySelector('.writebtn').innerHTML = writebtn
}

// 카테고리 번호에 해당하는 글 list 출력
function categoryboard(c_no){
	$.ajax({
		url : "",
		data :{"c_no" : c_no},
		success : (re)=>{
			
		}
	})
	
}
// 카테고리 번호에 해당하는 글 쓰 기
function writebtn(){
	$.ajax({
		url : "",
		data : {"c_no":c_no},
		success: (re) =>{
			if(re==='true'){
				alert('글 등록 완료')
			}else{
				alert('글 등록 실패')
			}
		}
	})
}








