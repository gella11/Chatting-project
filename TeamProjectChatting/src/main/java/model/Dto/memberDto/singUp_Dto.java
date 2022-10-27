package model.Dto.memberDto;

public class singUp_Dto {
	



	private int user_num; 					// 회원번호
	private String user_name;				// 회원이름
	private String user_pw;					// 비밀번호
	private String user_email; 				// 이메일
	private String user_phone; 				// 폰번호
	private String user_profile;			// 프로필사진
	private String user_msg; 				// 상태메시지
	
	
	public singUp_Dto() {

	}

	
	public singUp_Dto(int user_num, String user_name, String user_pw, String user_email, String user_phone,
			String user_profile, String user_msg) {
		super();
		this.user_num = user_num;
		this.user_name = user_name;
		this.user_pw = user_pw;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.user_profile = user_profile;
		this.user_msg = user_msg;
	}

	public singUp_Dto(int user_num, String user_name, String user_profile, String user_msg) {
		super();
		this.user_num = user_num;
		this.user_name = user_name;
		this.user_profile = user_profile;
		this.user_msg = user_msg;
	}

	

	public int getUser_num() {
		return user_num;
	}


	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getUser_pw() {
		return user_pw;
	}


	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getUser_phone() {
		return user_phone;
	}


	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}


	public String getUser_profile() {
		return user_profile;
	}


	public void setUser_profile(String user_profile) {
		this.user_profile = user_profile;
	}


	public String getUser_msg() {
		return user_msg;
	}


	public void setUser_msg(String user_msg) {
		this.user_msg = user_msg;
	}


	@Override
	public String toString() {
		return "singUp_Dto [user_num=" + user_num + ", user_name=" + user_name + ", user_pw=" + user_pw
				+ ", user_email=" + user_email + ", user_phone=" + user_phone + ", user_profile=" + user_profile
				+ ", user_msg=" + user_msg + "]";
	}
	

	
	
	
}
