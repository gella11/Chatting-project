<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="../CSS/member_CSS/F_list.css">

<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">

<!-- 구글 폰트 노토산스 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">


</head>
<body>

	<%@include file="header.jsp"%>

   <div class="container"> <!--  컨테이너  -->

      <div class="flist_box">

         <div class="profile_box"> 
            <div class="sub_box"> 
               <div class="profile_title">
                  내 프로필
               </div>
               <div> <!--  친구추가 아이콘 -->
                  <img onclick="addbtn()" class="friend_plus" src="../img/user_plus.png">
               </div>
            </div>   
            <div class="profile_con_box">
               <div>
                  <img class="user_profile" alt="" src="../img/망곰이.png"> <!-- 내 프로필 이미지 -->
               </div>
               <div class="profile_text_box">
                  <div class="user_name"> <!-- 내 이름 -->
                     김망곰
                  </div>
                  <div class="user_msg"> <!-- 내 프로필 메세지 -->
                     울지 않는 방법...
                  </div>
               </div>   
            </div>
         </div> <!-- profile_box e -->


         <div class="friend_box"> <!-- 친구 목록 출력 -->
            <div class="friend_title">
               친구 목록
            </div>

            <div class="friendlist_box">

               <div class="f_list">
                  <!-- 친구 리스트 js에서 가져와서 출력되는 곳 -->
                  <div class="friend_list">
                     <div class="friend_con_box" onclick="chatting('+l.user_num+')" id='+l.user_num+' >
                        <div>
                           <img class="friend_img" alt="" src="../img/망햄터.png"> <!-- 친구 프로필 이미지 -->
                        </div> 
                        <div class="friend_text_box"> 
                           <div class="friend_name"> 이햄터 </div> <!-- 친구 이름 -->
                           <div class="friend_msg"> 망구라안녕 망글망글랄그글 </div> <!-- 친구 프로필 메세지 -->
                        </div>
                     </div>
                  </div>
                  <!-- 친구 리스트 js에서 가져와서 출력되는 곳 e -->

               </div>
            </div> <!-- friend_box e -->
         </div>   <!-- flist_box e -->   
      
      <button style="display: none;" type="button" class="friendaddbtn btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
        친구추가버튼(숨길예정)
      </button>
      
      <!-- Modal -->
      <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="staticBackdropLabel"> 친구를 추가하세요 ! </h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <span>친구 e-mail 입력 : </span> <input type="text" class='f_email'>
              <button class="form-control" type="button" data-bs-dismiss="modal" onclick="friendadd()">친구 추가</button>
            </div>
          </div>
        </div>
      </div>
      

      <!-- 하단 탭 아이콘 부분 -->   
      <div class="icon_box">
         <div class="side_box">
            <div class="tab_list1"><a onclick="tabchange('F_list.jsp')"><i class="fas fa-user user"></i></a></div>
            <div class="tab_list2"><a onclick="tabchange('c_list.jsp')"><i class="fas fa-comment comment"></i></a></div>
            <div class="tab_list3"><a onclick="tabchange('F_list.jsp')"><i class="fas fa-ellipsis-h more"></i></a></div>
            <div class="tab_list4"><a class="logout" href="/TeamProjectChatting/member_View/login.jsp">Logout</a></div>
         </div>
      </div>

      </div> <!-- flist_box e -->
   </div> <!--  컨테이너 e  -->   



   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script src="../member_JS/F_list.js" type="text/javascript"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>