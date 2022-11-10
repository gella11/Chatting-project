all_user();
let user_email = '';
function all_user() {//[2022-11-07 회원 전체호출 메소드 ]
	$.ajax({
		url: "/TeamProjectChatting/admin",
		type: "get",
		success: function(re) {
			let list = JSON.parse(re)
			let html = `<tr>` +
				`<td>사원번호</td>` +
				`<td>이름</td>` +
				`<td>비밀번호</td>` +
				`<td>이메일</td>` +
				`<td>연락처</td>` +
				`<td>부서</td>` +
				`<td>생년월일</td>` +
				`<td>입사날</td>` +
				`<td>수정</td>` +
				`</tr>`
			list.forEach(l => {
				user_email = l.user_email
				html += `<tr>` +
					`<td>${l.user_num}</td>` +
					`<td>${l.user_name}</td>` +
					`<td>${l.user_pw}</td>` +
					`<td>${l.user_email}</td>` +
					`<td>${l.user_phone}</td>` +
					`<td>${l.user_department}</td>` +
					`<td>${l.user_birth}</td>` +
					`<td>${l.user_date}</td>` +
					`<td>` +
					`<button class="wrap button" onclick="edit(${l.user_num})">수정하기</button>` +
					`</td>` +
					`</tr>`
			})
			document.querySelector('.alluser').innerHTML = html
		}//success
	})//ajax E
}//funtion E


//////////////////////////////////이메일 저장 //////////////////////////////////////////////////
localStorage.removeItem('user_email');//저장소 초기화
//저장소 생성 [ checkplist 라는 이름으로 checkplist 를 저장]	
localStorage.setItem('user_email', JSON.stringify(user_email));
////////////////////////////////////////////////////////////////////////////////////////////


//[2022-11-08 회원 수정 기본정보 출력 메소드 ]
function edit(user_num) {
	//1.해당 모달을 열어주는 버튼에 강제클릭 이벤트 실행 
	document.querySelector(".btn").click()//해당버튼을 누를때 
	$.ajax({
		url: "/TeamProjectChatting/admin",
		data: { "user_num": user_num },
		type: "post",
		success: function(re) {
			let json = JSON.parse(re)
			document.querySelector('.user_num').value = json.user_num
			document.querySelector('.user_name').value = json.user_name
			document.querySelector('.user_pw').value = json.user_pw
			document.querySelector('.user_email').value = json.user_email
			document.querySelector('.user_phone').value = json.user_phone
			document.querySelector('.user_department').value = json.user_department
			document.querySelector('.user_birth').value = json.user_birth
			document.querySelector('.user_date').value = json.user_date
		}

	})

}
//
//[2022-11-08 회원 수정 처리  메소드 ]
// 추가해야할 사항 변경내용이 없으면 리턴하게 .
function edit_user() {
	let form = {
		user_num: document.querySelector('.user_num').value,
		user_name: document.querySelector('.user_name').value,
		user_pw: document.querySelector('.user_pw').value,
		user_email: document.querySelector('.user_email').value,
		user_phone: document.querySelector('.user_phone').value,
		user_department: document.querySelector('.user_department').value
	}
	$.ajax({
		url: "/TeamProjectChatting/admin",
		data: form,
		type: "PUT",
		success: function(re) {
			if (re == 'true') {
				alert('회원정보가 수정되었습니다.')
			} else {
				alert('오류가 발생했습니다.서버관리자에게 문의해주세요.')
			}

		}
	})
}
showcategory()
function showcategory() {
	let showcate = document.querySelector('.showcate')
	let add_delete = document.querySelector('.add_delete')
	$.ajax({
		url: "/TeamProjectChatting/categorynum",
		success: function(re) {
			let json = JSON.parse(re)
			let html = ''; let html2 = '';
			json.forEach(e => {
				html +=
					`<tr>` +
					`<td>${e.c_name}</td>`+
					`<th><button id="deletebtn"class="form-control"  type="button" onclick="deletecategory(${e.c_no})">삭제하기</button></th>` +
					`</tr>` +
					`</table>`
				
			})
			showcate.innerHTML = html;
		}
	})
}

//[2022-11-09 카테고리 추가 메소드 ]
function addcategory() {
	let category = document.querySelector('#category').value
	$.ajax({
		url: "/TeamProjectChatting/admin_Category",
		data: { "category": category },
		success: function(re) {
			if (re == 'true') {
				alert('카테고리 추가완료 ')
				location.reload()
			} else {
				alert('카테고리 추가에 실패했습니다.서버관리자에게 문의해주세요.')
			}

		}
	})
}
//[2022-11-09 카테고리 삭제 메소드 ]
function deletecategory(c_no) {
	$.ajax({
		url: "/TeamProjectChatting/admin_Category",
		type:"post",
		data: { "c_no": c_no },
		success: function(re) {
			if (re == 'true') {
				alert('카테고리 삭제완료')
				location.reload()
			} else {
				alert('카테고리 삭제에 실패했습니다.서버관리자에게 문의해주세요.')
			}

		}
	})
}

// Get DOM Elements
const modal = document.querySelector('#my-modal');
const modalBtn = document.querySelector('#modal-btn');
const closeBtn = document.querySelector('.close');

// Events
modalBtn.addEventListener('click', openModal);
closeBtn.addEventListener('click', closeModal);
window.addEventListener('click', outsideClick);

// Open
function openModal() {
  modal.style.display = 'block';
}

// Close
function closeModal() {
  modal.style.display = 'none';
}

// Close If Outside Click
function outsideClick(e) {
  if (e.target == modal) {
    modal.style.display = 'none';
  }
}


/*

	//document.querySelector('.pno').value = json.pno
			//document.querySelector('.pname').value = json.pname
			//document.querySelector('.pcomment').value = json.pcomment
			//document.querySelector('.pprice').value = json.pprice
			//document.querySelector('.pdiscount').value = json.pdiscount
			//document.querySelector('.valuebox').value = json.pactive
let buttons = document.querySelectorAll('button') // 현재페이지의 모든 버튼 

function updateaction() {
	let mname = document.querySelector('#mname')
	
	if (buttons[1].innerHTML == '확인') {

		$.ajax({
			url: "/jspweb/member/update",
			data: { "mname": mname.value },
			success: function(result) {
				if (result === 'true') {
					 alert("수정성공")
					  }else {
				 alert('수정실패 ') 
				 }

			}
		})
		buttons[1].innerHTML = '수정'
		mname.readOnly = false;
		//location.href = 'URL '//페이지이동
		location.reload();//현재 페이지 새로고침
	} else {
		alert('수정후 확인 버튼 클릭시 수정이 완료됩니다.')
		mname.readOnly = false;
		buttons[1].innerHTML = '확인'
	}

}

*/