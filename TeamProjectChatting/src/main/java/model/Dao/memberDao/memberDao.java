package model.Dao.memberDao;

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
	

	
	
}
