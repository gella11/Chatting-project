package model.Dto.memberDto;

public class F_list_Dto {
	
	private int user_num;
	private int friend_num;
	
	public F_list_Dto() {}

	public F_list_Dto(int user_num, int friend_num) {
		super();
		this.user_num = user_num;
		this.friend_num = friend_num;
	}

	public F_list_Dto(int int1) {
		// TODO Auto-generated constructor stub
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public int getFriend_num() {
		return friend_num;
	}

	public void setFriend_num(int friend_num) {
		this.friend_num = friend_num;
	}

	@Override
	public String toString() {
		return "F_list_Dto [user_num=" + user_num + ", friend_num=" + friend_num + "]";
	}
	
	
	
}
