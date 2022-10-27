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
	

	
	// 세션에 등록된 나의 회원번호로
	// 친구 목록 가져와서
	// 친구들의 정보(회원번호 이름 프로필 상태메시지) 가져오기 - 상진
	public ArrayList<Integer> getinfolist(int my_num){
        ArrayList<Integer> list = new ArrayList<>();
        String sql = "select friend_num from friend where user_num = "+my_num;
        try {
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while(rs.next()){
              list.add(rs.getInt(1));
           }
           return list;
        } catch (Exception e) {   System.out.println(e);   }
        return list;
     }
     public ArrayList<singUp_Dto> f_list_info(ArrayList<Integer> list){
        ArrayList<singUp_Dto> friendlist = new ArrayList<>();
        for(int a : list) {
           singUp_Dto dto = null;
           String sql = "select user_num , user_name , user_profile , user_msg from user where user_num="+a;
           try {
              ps = con.prepareStatement(sql);
              rs = ps.executeQuery();
              while(rs.next()){
                 dto = new singUp_Dto(
                       rs.getInt(1),
                       rs.getString(2),
                       rs.getString(3),
                       rs.getString(4)
                       );
                 friendlist.add(dto);
              }
              continue;
           } catch (Exception e) {   System.out.println(e);   }
        }   
        return friendlist;      
     }
		
		
		
}
