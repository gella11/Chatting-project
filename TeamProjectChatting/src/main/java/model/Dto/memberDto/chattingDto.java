package model.Dto.memberDto;

public class chattingDto {
	private String type;
	private String mid;
	private String content;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public chattingDto(String type, String mid, String content) {
		super();
		this.type = type;
		this.mid = mid;
		this.content = content;
	}
	public chattingDto() {
		super();
	}
	@Override
	public String toString() {
		return "chattingDto [type=" + type + ", mid=" + mid + ", content=" + content + "]";
	}
}
