package model.Dao.boardDao;

import java.util.ArrayList;

import javax.swing.text.html.parser.DTD;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Dto.memberDto.User_Dto;

public class adminDao extends SuperDao_B{
	
	private static adminDao adminDao = new adminDao();
	public static adminDao getInstance() {
		return adminDao;
	}
	
	//[2022-11-07 회원 전체호출 메소드 ]
	public ArrayList<User_Dto> all_user(){
		ArrayList<User_Dto>list = new ArrayList<>();
		String sql = "select*from user where user_num!=1;";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				User_Dto dto = new User_Dto(
						rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(8), 
						rs.getString(9), rs.getString(10),
						rs.getString(11));
				list.add(dto);
			}
			
			return list;
		} catch (Exception e) {System.out.println("관리자 회원호출 오류 adminDao"+e);}
		return null;
	}
	
	//[2022-11-08 회원 수정 메소드 ]
	public User_Dto user_edit(int user_num) {
		String sql ="select*from user where user_num=" + user_num;
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			if (rs.next()) {//
				User_Dto dto = new User_Dto(
						rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(8), 
						rs.getString(9), rs.getString(10),
						rs.getString(11));
				return dto;
			}
		} catch (Exception e) {System.out.println("관리자 회원수정 개인 호출 오류 adminDao"+e);}
		return null;
	}
	
	//[2022-11-08 회원 정보 수정 메소드 ]
	public boolean edit_user(User_Dto dto , int user_num) {
		String sql ="update user set user_name=?,"
				+ "user_pw=?,user_email=?,"
				+ "user_phone=?,user_department=? where user_num="+user_num;
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, dto.getUser_name());
			ps.setString(2, dto.getUser_pw());
			ps.setString(3, dto.getUser_email());
			ps.setString(4, dto.getUser_phone());
			ps.setString(5, dto.getUser_department());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("관리자 회원수정 개인 수정 오류 adminDao"+e);}
		
		return false;
	}
	//[2022-11-09 카테고리 추가 메소드 ]
	public boolean addcategory(String category) {
		String sql="insert into category(c_name) value(?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, category);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("관리자 카테고리추가 오류 adminDao"+e);}
		return false;
	}
	//[2022-11-09 카테고리 삭제 메소드 ]
	public boolean deletecategory(int c_no) {
		String sql="DELETE FROM category WHERE c_no=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, c_no);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("관리자 카테고리삭제 오류 adminDao"+e);}
		return false;
	}
	//[2022-11-10 사원 개인정보 출력 메소드]
	public JSONArray detail_employee(int user_num) {
		JSONArray array= new JSONArray();
		String sql="select*from user where user_num="+user_num;
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				JSONObject object = new JSONObject();
				object.put("user_num", rs.getInt(1));
				object.put("user_name", rs.getString(2));
				object.put("user_department", rs.getString(8));
				object.put("user_position", rs.getString(9));
				object.put("user_birth", rs.getString(10));
				object.put("user_date", rs.getString(11));
				object.put("user_vacation", rs.getString(12));
				object.put("user_usevacation", rs.getString(13));
				array.add(object);
				System.out.println("다오오오오오당"+array);
				return array;
			}
		} catch (Exception e) {System.out.println("개인정보호출 오류"+e);}
		return null;
	}
	//[2022-11-11 인사관리 실적 입력 메소드]
	public boolean Preformace(String preformace, int user_num) {
		String sql= "insert into Performance values(? , now(), ?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_num);
			ps.setString(2, preformace);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("인사관리 실적입력 오류"+e);}
		return false;
	}
	//[2022-11-11 인사관리 실적 출력 메소드]
	
	public ArrayList<Integer> chart(int user_num){
		ArrayList<Integer> list = new ArrayList<>();
		for(int i =1; i<=12 ;i++) {	
		String sql=null;
			if(i<10) {
				sql="select count(*) from Performance where user_num=? and Performance like '%2022-0"+i+"%';";
			}else {
				sql="select count(*) from Performance where user_num=? and Performance like '%2022-"+i+"%';";
			}
			try {
				System.out.println("차트넘버///"+user_num);
				ps=con.prepareStatement(sql);
				ps.setInt(1, user_num);
				rs=ps.executeQuery();
				if(rs.next()) {
					int count = rs.getInt(1);
					list.add(count);
					continue;
				}
			} catch (Exception e) {System.out.println("실적출력 오류"+e);}
			break;
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
