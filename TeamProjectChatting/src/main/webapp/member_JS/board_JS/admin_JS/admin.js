all_user();
let user_email = '';
let select_num=0;
let chartdata = [];
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
				`<td>직책</td>` +
				`<td>입사날</td>` +
				`<td>수정</td>` +
				`</tr>`
			list.forEach(l => {
				user_email = l.user_email
				html += `<tr>` +
					`<td>${l.user_num}</td>` +
					`<td onclick="detail_employee(${l.user_num})">${l.user_name}</td>` +
					`<td>${l.user_pw}</td>` +
					`<td>${l.user_email}</td>` +
					`<td>${l.user_phone}</td>` +
					`<td  onclick="insert_department(${l.user_num})">${l.user_department}</td>` +
					`<td>${l.user_position}</td>` +
					`<td>${l.user_date.split(" ")[0]}</td>` +
					
					`<td>` +
					`<button class="wrap button" onclick="edit(${l.user_num})"><a id="btn" href="#open-modal">수정하기</a></button>` +
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
	document.querySelector("#btn").click()//해당버튼을 누를때 
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
			document.querySelector('.user_position').value = json.user_position
			document.querySelector('.user_birth').value = json.user_birth
			document.querySelector('.user_date').value = json.user_date.split(" ")[0]
		}

	})

}
//
//[2022-11-08 회원 수정 처리  메소드 ]
// 추가해야할 사항 변경내용이 없으면 리턴하게 .
function edit_user() {
	select_num = document.querySelector('.user_num').value
	let form = {
		user_num: document.querySelector('.user_num').value,
		user_name: document.querySelector('.user_name').value,
		user_pw: document.querySelector('.user_pw').value,
		user_email: document.querySelector('.user_email').value,
		user_phone: document.querySelector('.user_phone').value,
		user_department: document.querySelector('.user_department').value,
		user_position: document.querySelector('.user_position').value
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
					`<td>${e.c_name}</td>` +
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
				alert('카테고리 추가가 완료되었습니다. ')
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
		type: "post",
		data: { "c_no": c_no },
		success: function(re) {
			if (re == 'true') {
				alert('카테고리 삭제를 완료했습니다.')
				location.reload()
			} else {
				alert('카테고리 삭제에 실패했습니다.서버관리자에게 문의해주세요.')
			}

		}
	})
}

function detail_employee(user_num) {
	select_num=user_num
	document.querySelector('.Click-here').click(user_num)
	$.ajax({
		url: "/TeamProjectChatting/admin_Category",
		type: "put",
		async:false,
		data: { "user_num": user_num },
		success: function(re) {
			let detail = JSON.parse(re)
			let html = '<tr>' +
				'<td>사원번호</td>' +
				'<td>사원이름</td>' +
				'<td>소속부서</td>' +
				'<td>직책</td>' +
				'<td>생년월일</td>' +
				'<td>입사날짜</td>' +
				'<td>연차</td>' +
				'<td>사용일</td>' +
				'</tr>';
			detail.forEach(d => {
				html += '<tr>' +
					`<td>${d.user_num}</td>` +
					`<td>${d.user_name}</td>` +
					`<td>${d.user_department}</td>` +
					`<td>${d.user_position}</td>` +
					`<td>${d.user_birth}</td>` +
					`<td>${d.user_date.split(" ")[0]}</td>` +
					`<td>${d.user_vacation}</td>` +
					`<td>${d.user_usevacation}</td>` +
					`<tr><td colspan="1">` +
					`실적</td><td colspan="5"><input class="performance form-control" type="text"></td>` +
					`<td  colspan="2"><button style="width: 100px;" class="Performance wrap button" onclick="Performance(${d.user_num})">실적입력</button></td>` +
					`</td></tr>` +
					`</tr>`
			})
			document.querySelector('.detail_table').innerHTML = html
		chart()
		}
	})

}

//[2022-11-11] 인사관리 실적입력 메소드 김원종 
function Performance(user_num) {
	let preformace = document.querySelector('.performance').value
	$.ajax({
		url: "/TeamProjectChatting/management",
		data: { "user_num": user_num, "preformace": preformace },
		success: function(re) {
			if (re == 'true') {
				alert('입력하신 정보가 입력되었습니다.')
				chart();
			} else {
				alert('실패 서버관리자에게 문의해주세요.')
			}
	
		}
	})
}
//[2022-11-11] 인사관리 실적 출력  메소드 김원종 
function chart() {
	$.ajax({
		url: "/TeamProjectChatting/management",
		data: { "user_num": select_num },
		type: "post",
		async:false,
		success: function(re) {
			let chart =JSON.parse(re)
			chartdata = [];
			for(let i=0; i<12; i++){
				chartdata.push(chart[i])
			}
			console.log(chartdata)
		}
	})
	chartstart()
}
function chartstart(){
	var context = document
	    .getElementById('myChart')
	    .getContext('2d');
	var myChart = new Chart(context, {
	    type: 'bar', // 차트의 형태
	    data: { // 차트에 들어갈 데이터
	        labels: [
	            //x 축
	            '1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'
	        ],
	        datasets: [
	            { //데이터
	                label:'2022 실적', //차트 제목
	                fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
	                data:
	                    chartdata//x축 label에 대응되는 데이터 값
	                ,
	                backgroundColor: [
	                    //색상
	               'rgba(255, 99, 132, 0.2)',
	                   'rgba(54, 162, 235, 0.2)',
	                   'rgba(255, 206, 86, 0.2)',
	                   'rgba(75, 192, 192, 0.2)',
	                   'rgba(153, 102, 255, 0.2)',
	                   'rgba(255, 159, 64, 0.2)',
	                   'rgba(255, 99, 132, 0.2)',
	                   'rgba(54, 162, 235, 0.2)',
	                   'rgba(255, 206, 86, 0.2)',
	                   'rgba(75, 192, 192, 0.2)',
	                   'rgba(153, 102, 255, 0.2)',
	                   'rgba(255, 159, 64, 0.2)'
	                ],
	                borderColor: [
	                    //경계선 색상
	                    'rgba(255, 99, 132, 1)',
	                    'rgba(54, 162, 235, 1)',
	                    'rgba(255, 206, 86, 1)',
	                    'rgba(75, 192, 192, 1)',
	                    'rgba(153, 102, 255, 1)',
	                    'rgba(255, 159, 64, 1)',
	                    'rgba(255, 99, 132, 1)',
	                    'rgba(54, 162, 235, 1)',
	                    'rgba(255, 206, 86, 1)',
	                    'rgba(75, 192, 192, 1)',
	                    'rgba(153, 102, 255, 1)',
	                    'rgba(255, 159, 64, 1)'
	                ],
	                borderWidth: 3 //경계선 굵기
	            } 
	        ]
	    },
	    options: {
	        scales: {
	            yAxes: [
	                {
	                    ticks: {
	                        beginAtZero: true
	                    }
	                }
	            ]
	        }
	    }
	});
}



////////////////////////////////////////////모달//////////////////////////////////////////

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


////////////////////////////////////////////////////////////////////////////////////////////////

$(".Click-here").on('click', function() {
	$(".custom-model-main").addClass('model-open');
});
$(".close-btn, .bg-overlay").click(function() {
	$(".custom-model-main").removeClass('model-open');
});


function pagechange2( page ){
	$("#boardbox").load( page ) // 특정 태그에 해당 파일 로드 [ jquery ]
}

function goback(){
	location.href='../dash.jsp'
}








// TAB
// $(document).on('click', '.tab-wrap ul li a', function (e) {
//     var curTabContentId = $(this).attr('href');
//     $(this).parents('.tab-wrap').find('ul li').removeClass('active');
//     $(this).parents('li').addClass('active');
//     $(this).parents('.tab-wrap').find('.tab-content-id').removeClass('active');
//     $(curTabContentId).addClass("active");
//     e.preventDefault();
// });
