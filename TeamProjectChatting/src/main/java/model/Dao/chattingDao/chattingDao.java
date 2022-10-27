package model.Dao.chattingDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Dto.memberDto.F_list_Dto;
import model.Dto.memberDto.singUp_Dto;



public class chattingDao extends SuperDao_C{

	private static chattingDao cdao =new chattingDao();
	public static chattingDao getInstacnDao() {return cdao;}
	
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public chattingDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/cacao", "root", "1234");
		} catch (Exception e) { System.out.println("DB오류:"+e);}
	}
	
	
	// 상진  
		// 친구목록
		// 2-1) 내 친구 회원번호 출력 
		public ArrayList<F_list_Dto> getinfolist(int my_num){
			ArrayList<F_list_Dto> list = new ArrayList<>();
			String sql = "select friend_num from friend where 회원번호 = "+my_num+"; ";
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					F_list_Dto dto = new F_list_Dto(
							rs.getInt(2)
							);
					list.add(dto);
				}
				return list;
			} catch (Exception e) {	System.out.println(e);	}
			return list;
		}
		
		
}
