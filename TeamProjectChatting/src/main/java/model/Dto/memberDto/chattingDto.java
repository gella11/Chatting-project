package model.Dto.memberDto;

public class chattingDto {
	private String type;
	private String mid;
	private String name;
	private String content;
	private String date;
	private String img;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public chattingDto(String type, String mid, String name, String content, String date, String img) {
		super();
		this.type = type;
		this.mid = mid;
		this.name = name;
		this.content = content;
		this.date = date;
		this.img = img;
	}
	public chattingDto() {
		super();
	}
	@Override
	public String toString() {
		return "chattingDto [type=" + type + ", mid=" + mid + ", name=" + name + ", content=" + content + ", date="
				+ date + ", img=" + img + "]";
	}
}
