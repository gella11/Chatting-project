package model.Dto.memberDto;

public class recommendDto {
	private String email;
	private String mname;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public recommendDto(String email, String mname) {
		super();
		this.email = email;
		this.mname = mname;
	}
	public recommendDto() {
		super();
	}
	@Override
	public String toString() {
		return "recommendDto [email=" + email + ", mname=" + mname + "]";
	}
}
