package model.Dto.memberDto;

public class BoardDto {
	private int b_no;		
	private String b_title;		
    private String b_content;		
    private String b_file;							
    private String  b_date; 				
    private String b_view;					
    private int c_no;								
    private String  user_name;
	
    public BoardDto() {super();}

	public BoardDto(int b_no, String b_title, String b_content, String b_file, String b_date, String b_view, int c_no,
			String user_name) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_file = b_file;
		this.b_date = b_date;
		this.b_view = b_view;
		this.c_no = c_no;
		this.user_name = user_name;
	}

	@Override
	public String toString() {
		return "BoardDto [b_no=" + b_no + ", b_title=" + b_title + ", b_content=" + b_content + ", b_file=" + b_file
				+ ", b_date=" + b_date + ", b_view=" + b_view + ", c_no=" + c_no + ", user_name=" + user_name + "]";
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public String getB_file() {
		return b_file;
	}

	public void setB_file(String b_file) {
		this.b_file = b_file;
	}

	public String getB_date() {
		return b_date;
	}

	public void setB_date(String b_date) {
		this.b_date = b_date;
	}

	public String getB_view() {
		return b_view;
	}

	public void setB_view(String b_view) {
		this.b_view = b_view;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
    
    
    
}
