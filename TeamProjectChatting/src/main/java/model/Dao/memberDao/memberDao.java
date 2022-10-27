package model.Dao.memberDao;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class memberDao  extends SuperDao_M{
	

	
	private static memberDao mdao =new memberDao();
	public static memberDao getInstacnDao() {return mdao;}

	
	
	// 상진
	// 1. 이메일 세션으로 나의 회원번호 출력
	public int user_num(String my_num) {
		String sql ="select (user_num) from user where user_email = ?";
		int user_num = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1 , my_num);
			rs = ps.executeQuery();
			
			if(rs.next()) { user_num = rs.getInt(1); }
			
				return user_num;
			}catch (Exception e) {System.out.println(e);}
		return -1;
		} 

	
	// 회원가입 - 혜영
	public boolean sign_up( String user_name, String user_pw, String user_email, String user_phone ) {
		String sql ="insert into user(user_name, user_pw, user_email, user_phone) values( ?, ?, ?, ? )";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.setString(2, user_pw);
			ps.setString(3, user_email);
			ps.setString(4, user_phone);
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			System.out.println("회원가입 메소드 오류 : "+e);
		}
		return false;
	} // sign_up e
	

	//[10-26 ] 김원종 로그인 매소드 구현
	public int login(String mid, String mpassword) { // 매개변수로 id 와 password 받기
		String sql = "select*from user where user_email=?"; // user테이블에 email 일치하는값 찾기

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid); // 아이디값 셋팅
			rs = ps.executeQuery();
			if (rs.next()) {//아이디가 만약 존재하면 
				sql = "select*from user where user_email=? and user_pw=?"; //아이디와 비밀번호가 같은거
				ps=con.prepareStatement(sql);	
				ps.setString(1, rs.getString(4)); // 아이디필드에 처음 셋팅한 값을 대입
				ps.setString(2, mpassword);// 2번째 물음표에 password대입
				rs = ps.executeQuery();
				if (rs.next()) {//만약 둘다 일치하면 
					return 1; // 일치하는 아이디가 있다
				} // 2번 if end
				return 2; // PW가 틀렸다
			} // 1번 if end

		} // try end
		catch (Exception e) {System.out.println("로그인메소드 오류" + e);return 3;} // DB오류다
		return 0;//아이디가 없다.
	}// 메소드 end

}
