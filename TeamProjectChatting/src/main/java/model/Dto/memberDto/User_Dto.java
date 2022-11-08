package model.Dto.memberDto;

public class User_Dto {
	private int user_num;
	private String user_name;
	private String user_pw;
	private String user_email;
	private String user_phone;
	private String user_department;
	private String user_birth;
	private String user_date;
	
	
	public User_Dto(int user_num, String user_name, String user_pw, String user_email, String user_phone,
			String user_department) {
		super();
		this.user_num = user_num;
		this.user_name = user_name;
		this.user_pw = user_pw;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.user_department = user_department;
	}
	public User_Dto() {
		super();
	}
	public User_Dto(int user_num, String user_name, String user_pw, String user_email, String user_phone,
			String user_department, String user_birth, String user_date) {
		super();
		this.user_num = user_num;
		this.user_name = user_name;
		this.user_pw = user_pw;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.user_department = user_department;
		this.user_birth = user_birth;
		this.user_date = user_date;
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
	public String getUser_department() {
		return user_department;
	}
	public void setUser_department(String user_department) {
		this.user_department = user_department;
	}
	public String getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}
	public String getUser_date() {
		return user_date;
	}
	public void setUser_date(String user_date) {
		this.user_date = user_date;
	}
	@Override
	public String toString() {
		return "User_Dto [user_num=" + user_num + ", user_name=" + user_name + ", user_pw=" + user_pw + ", user_email="
				+ user_email + ", user_phone=" + user_phone + ", user_department=" + user_department + ", user_birth="
				+ user_birth + ", user_date=" + user_date + "]";
	}
	
	
	
}
