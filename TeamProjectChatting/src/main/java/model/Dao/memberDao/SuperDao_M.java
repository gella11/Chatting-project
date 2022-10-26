package model.Dao.memberDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SuperDao_M {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	public SuperDao_M() {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");  
				con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/cacao", 
				"root", 
				"1234");
		System.out.println("DB 연동 성공");
	}catch (Exception e) {}
}

}
