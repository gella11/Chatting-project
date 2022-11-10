package model.Dto.memberDto;

public class calDto {

	private int t_no;
	private String user_name;
	private String t_content;
	private String t_date;
	
	public calDto() {super();}

	public calDto(int t_no, String user_name, String t_content, String t_date) {
		super();
		this.t_no = t_no;
		this.user_name = user_name;
		this.t_content = t_content;
		this.t_date = t_date;
	}

	@Override
	public String toString() {
		return "calDto [t_no=" + t_no + ", user_name=" + user_name + ", t_content=" + t_content + ", t_date=" + t_date
				+ "]";
	}

	public int getT_no() {
		return t_no;
	}

	public void setT_no(int t_no) {
		this.t_no = t_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getT_content() {
		return t_content;
	}

	public void setT_content(String t_content) {
		this.t_content = t_content;
	}

	public String getT_date() {
		return t_date;
	}

	public void setT_date(String t_date) {
		this.t_date = t_date;
	}
	
	
	
}
