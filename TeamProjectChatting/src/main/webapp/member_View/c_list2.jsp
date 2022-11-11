<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../CSS/member_CSS/c_list2.css?after">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">

<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">

<!-- 구글 폰트 노토산스 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">


</head>
<body class="bodybg">
   <%@include file="header.jsp"%>
   
   <div class="c_container"> <!--  컨테이너  -->
      <div class="chatting_rbox"> <!-- 채팅방 박스 -->
      
         <div class="friend_box"> <!-- 친구 목록 출력 -->
               <div class="friend_title">
                  채팅 목록 ▾
                  <img onclick="addbtn()" class="groupchat" src="../img/talk.png">  
               </div>
               <div class="friendlist_box">
      
                  <div class="clist">
                     <!-- 친구 리스트 js에서 가져와서 출력되는 곳 -->
                     <div class="friend_list">
                        <div class="friend_con_box">
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
      
      
      <!-- Button trigger modal -->
      <button type="button" style='display: none' class="btn btn-primary modalbtn" data-bs-toggle="modal" data-bs-target="#exampleModal">
         강제클릭버튼 안보이는부분.
      </button>
      <!-- Modal -->
      <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="exampleModalLabel"> Chatting </h1>
              <button type="button" class="btn-close" onclick='socketclose()' data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div class='contentbox'>
              </div>
              <textarea onkeyup="enterkey()" class='msgbox form-control' rows="" cols=""></textarea>
              
              <button class="sendbox form-control" type="button" onclick="send()">보내기</button>
              <button style='background-color: #ff9866; ' class="form-control" type="button" onclick="chattingout()">채팅방 나가기</button>
            </div>
          </div>
        </div>
      </div>
         
      <button style='color=red; display: none' type="button" class="groupchatbtn btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
        단톡방
      </button>    
      <!-- Modal -->
      <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="staticBackdropLabel"> 채팅방을 만드세요 ! </h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div class="c_friendlist">
            </div>
            <div class="form-floating mb-3">
  			  <input type="email" class="chattingname form-control" id="floatingInput" placeholder="name@example.com">
  			  <label for="floatingInput"> 채팅방 이름 입력 </label>
			</div>
            <button class="form-control" type="button" data-bs-dismiss="modal" onclick="groupchatting()">채팅방 만들기</button>
            </div>
          </div>
        </div>
      </div>         
         
      <!-- 하단 탭 아이콘 부분 -->   
      <div class="icon_box">
         <div class="side_box">
            <div class="tab_list1"><a onclick="pagechange('F_list2.jsp')"><i class="fas fa-user user"></i></a></div>
            <div class="tab_list2"><a onclick="pagechange('c_list2.jsp')"><i class="fas fa-comment comment"></i></a></div>
            <div class="tab_list3"><a onclick="pagechange2('calender/calender2.jsp')"><i class="fas fa-ellipsis-h more"></i></a></div>
            <div class="tab_list4"><a class="logout" href="/TeamProjectChatting/member_View/login.jsp">Logout</a></div>
         </div>
      </div>
         
         
         
      </div> <!-- 채팅방 박스 e -->
      
    

		
		
   </div> <!--  컨테이너 e  -->
   
   


	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
	<script src="../member_JS/c_list.js" type="text/javascript"></script>
</body>
</html>