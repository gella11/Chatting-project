  
let in_box = document.querySelector('.in_box');
let back = document.querySelector('.back');

function login() {//로그인 메소드[ 2022-10-26 김원종]
   let mid = document.querySelector('.mid').value    //아이디값 가져오기
   let mpassword = document.querySelector('.mpassword').value   //패스워드 값 가져오기  
   let result = document.querySelector('.result')

   $.ajax({
      url: "/TeamProjectChatting/member/login", // 서블렛주소 설정
      data: { "mid": mid, "mpassword": mpassword },//서블렛에 넘겨줄 아이디 비밀번호 
      success: function(re) {
         if (re == 1) { alert('로그인에성공했습니다.'); 
         //location.href = "/TeamProjectChatting/member_View/F_list.jsp";
         location.href = "/TeamProjectChatting/member_View/dash.jsp";
         }//1번 if end
         else if (re == 2) { result.innerHTML = 'password가 다릅니다.' }
         else if (re == 3) { result.innerHTML = '데이터베이스 오류입니다.서비스센터에 문의해주세요.' }
         else if (re == 0) { result.innerHTML = "<span>아이디가 없습니다</span>" }
      }//success end
   })//ajax end
}// login 메소드 end


function Signup(){
location.href="/TeamProjectChatting/member_View/singUp.jsp"
}




const toastTrigger = document.getElementById('liveToastBtn')
const toastLiveExample = document.getElementById('liveToast')
if (toastTrigger) {
  toastTrigger.addEventListener('click', () => {
    const toast = new bootstrap.Toast(toastLiveExample)

    toast.show()
  })
}