package model.Dao.memberDao;

public class memberDao  extends SuperDao_M{
	
	private static memberDao mdao = new memberDao();
	public static memberDao getInstence() {
		return mdao;
	}
	
	
	public boolean sign_up( String user_name, String user_pw, String user_email, String user_phone ) {
		String sql ="insert into user(user_name, user_pw, user_email, user_phone) values( ?, ?, ?, ? )";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.setString(2, user_pw);
			ps.setString(3, user_email);
			ps.setString(4, user_phone);
			int count = ps.executeUpdate();
			if( count == 1 ) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("회원가입 메소드 오류 : "+e);
		}
		return false;
	} // sign_up e
	
	
	
	
}
