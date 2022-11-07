package model.Dto.memberDto;

public class CategoryDto {

	private int c_no;
	private String c_name;
	
	public CategoryDto() {super();}

	public CategoryDto(int c_no, String c_name) {
		super();
		this.c_no = c_no;
		this.c_name = c_name;
	}

	@Override
	public String toString() {
		return "CategoryDto [c_no=" + c_no + ", c_name=" + c_name + "]";
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	
	
	
}
