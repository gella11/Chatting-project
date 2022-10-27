package model.Dao.memberDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class memberDao  extends SuperDao_M{
	
	
	private static memberDao mdao =new memberDao();
	public static memberDao getInstacnDao() {return mdao;}
	
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public memberDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/cacao", "root", "1234");
		} catch (Exception e) { System.out.println("DB오류:"+e);}
	}
	
	
	
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
	
	
}
