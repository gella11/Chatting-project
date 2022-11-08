package model.Dto.memberDto;

public class profileDto {
	private String profile;
	private String msg;
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public profileDto(String profile, String msg) {
		super();
		this.profile = profile;
		this.msg = msg;
	}
	public profileDto() {
		super();
	}
	@Override
	public String toString() {
		return "profileDto [profile=" + profile + ", msg=" + msg + "]";
	}
}
