
function sign_up(){ // 회원가입 - 허혜영 221026
	
	let info = {
		user_name : document.querySelector('#user_name').value,
		user_phone : document.querySelector('#user_phone').value,
		user_email : document.querySelector('#user_email').value,
		user_pw : document.querySelector('#user_pw').value,
		user_pw_confirm : document.querySelector('#user_pw_confirm').value
	}

	$.ajax({
		url		: "/TeamProjectChatting/member/singUp",
		data	: info,
		type 	: "POST",
		success : function( result ){
			if( result == 'true' ){
				alert('회원가입이 완료되었습니다.')
				// location.href="../member_View/login.jsp";
			}else{
				alert('회원가입이 실패하였습니다. 다시 시도해주세요.')	
			}
		} 
	})
}

/* ---- 유효성 검사 ---- */

// 1. 이름 검사
function name_check(){
	let user_name = document.querySelector('#user_name').value;
	let user_name_c = /^[a-zA-Z가-힣]{2,20}$/; // 영대소문자, 한글 최소 2글자, 최대 20글자
	if( user_name_c.test(user_name) ){
		document.querySelector('.check_icon1').innerHTML = '';
	}else{
		document.querySelector('.check_icon1').innerHTML = '2글자 이상 영문 대소문자만 입력 가능합니다.';
	}
}

// 2. 핸드폰번호 검사
function phone_check(){
	let user_phone = document.querySelector('#user_phone').value;
	let user_phone_c = /^([0-9]{2,3})-([0-9]{3,4})-([0-9]{3,4})$/;
	if( user_phone_c.test(user_phone) ){
		document.querySelector('.check_icon2').innerHTML = '';
	}else{
		document.querySelector('.check_icon2').innerHTML = '올바른 핸드폰 번호를 입력해 주세요.';
	}
}

// 3. 이메일 검사
function email_check(){
	let user_email = document.querySelector('#user_email').value;
	let user_email_c = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-]+$/;
	if( user_email_c.test(user_email) ){
		document.querySelector('.check_icon3').innerHTML = '';
	}else{
		document.querySelector('.check_icon3').innerHTML = '올바른 이메일 주소로 입력해 주세요.';
	}
}

// 4. 비밀번호 검사
function pw_check1(){
	let user_pw = document.querySelector('#user_pw').value;
	let user_pw_c = /^[a-zA-Z0-9]{8,20}$/; // 영대소문자 최소 8글자, 최대 20글자
	if( user_pw_c.test(user_pw) ){
		document.querySelector('.check_icon4').innerHTML = '';
	}else{
		document.querySelector('.check_icon4').innerHTML = '영문 대소문자와 숫자를 포함한 8~20글자로 입력해 주세요.';
	}
}

// 5. 비밀번호 확인 검사
function pw_check2(){
	let user_pw = document.querySelector('#user_pw').value;
	let user_pw_confirm = document.querySelector('#user_pw_confirm').value;
	let user_pw_c = /^[a-zA-Z0-9]{8,20}$/; // 영대소문자 최소 8글자, 최대 20글자
	if( !user_pw_c.test(user_pw) ){	// 정규표현식이랑 다르면
		document.querySelector('.check_icon5').innerHTML = '영문 대소문자와 숫자를 포함한 8~20글자로 입력해 주세요.';
	}else if( user_pw != user_pw_confirm ){ // 비밀번호와 비밀번호 확인이 다르면
		document.querySelector('.check_icon5').innerHTML = '비밀번호가 일치하지 않습니다';
	}else{	// 정규표현식도 맞고 두개의 비밀번호가 일치하면
		document.querySelector('.check_icon5').innerHTML = '';
	}
}







