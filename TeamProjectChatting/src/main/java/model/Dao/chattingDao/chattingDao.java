package model.Dao.chattingDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import model.Dto.memberDto.F_list_Dto;
import model.Dto.memberDto.singUp_Dto;



public class chattingDao extends SuperDao_C{

	private static chattingDao cdao =new chattingDao();
	public static chattingDao getInstacnDao() {return cdao;}
	

	// 도현 상진
	// [10/28]
	// 1. 내 회원번호로 친구목록 가져온 뒤, 
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
        } catch (Exception e) {   System.out.println("내 회원번호로 친구목록 가져오기 오류 || chatting dao : 1번 "+e);   }
        return list;
     }
	// 도현 상진
	// [10/28]
	// 1-2 친구들의 정보(회원번호 이름 프로필 상태메시지) 가져오기
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
           } catch (Exception e) {   System.out.println("친구 정보가져오기 오류 || chatting dao : 1-2번"+e);   }
        }   
        return friendlist;      
     }
	
   // 도현 상진
   // [10/28]
   // 3. 끝방 찾기
 	public int endroom() {
 		String sql = "select * from chattingroom order by c_num desc limit 1";
 		try {
 			ps = con.prepareStatement(sql);
 			rs = ps.executeQuery();
 			if(rs.next()){
 				return  rs.getInt(1);
 				} 
 		} catch (Exception e) {System.out.println("끝방찾기 오류 || chatting dao : 3번"+e);}
 		return 0;
 	}
 	
    // 도현 상진
 	// [10/28]
 	// 4. 회원번호로 이름 가져오기
 	public String findname(int num) {
 		String sql = "select user_name from user where user_num = "+num;
 		try {
 			ps = con.prepareStatement(sql);
 			rs = ps.executeQuery();
 			if(rs.next()){
 				return  rs.getString(1);
 				} 
 		} catch (Exception e) {System.out.println("회원번호로 이름 가져오기 오류 || chatting dao : 4번"+e);}
 		return null;
 	}
    // 도현 상진
 	// [10/28]
 	// 5. 채팅방 생성 
 	public boolean chattingroom (int endroom , int num) {
 		String sql="insert into chattingroom(c_num , user_num) value(?, ?);";
 		try {
 			ps = con.prepareStatement(sql);
 			ps.setInt(1, endroom+1 );
 			ps.setInt(2, num );
 			ps.executeUpdate();
 			return true;
 		} catch (Exception e) {System.out.println("채팅방 insert 오류 || chatting dao : 5번"+e);}
 		return false;
 				
 	}
    // 도현 상진
 	// [10/28]
 	// 6. 채팅방 이름에 회원 이름 넣기
  	public boolean chattingroomname (int endroom , String c_name) {
  		String sql="insert into chattingname(c_num , c_name) value(?, ?)";
  		try {
  			ps = con.prepareStatement(sql);
  			ps.setInt(1, endroom+1 );
  			ps.setString(2, c_name );
  			ps.executeUpdate();
  			return true;
  		} catch (Exception e) {System.out.println("채팅방 이름 넣기 오류 || chatting dao : 6번"+e);}
  		return false;
  	}
  	
  	
 	//11/1 도현) 나의 채팅방찾기
  	public ArrayList<Integer> chattinglist (int user_num){
  		ArrayList<Integer> list = new ArrayList<>();
  		String sql = "select c_num from chattingroom where user_num="+user_num;
  		try {
  			ps = con.prepareStatement(sql);
 			rs = ps.executeQuery();
 			while(rs.next()){
 				list.add(rs.getInt(1));
 			}
 			return list;
		} 
  		catch (Exception e) {
			System.out.println(e);
		}
  		return null;
  	}
  	
  	public ArrayList<String> chattingname (int c_num){
  		ArrayList<String> list = new ArrayList<>();
  		String sql = "select * from chattingname where c_num ="+c_num;
  		try {
  			ps = con.prepareStatement(sql);
 			rs = ps.executeQuery();
 			while(rs.next()){
 				list.add(String.valueOf(rs.getInt(1)));
 				list.add(rs.getString(2));
 			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
  		return null;
  	}
		
  	
  	
  	
  	public boolean findroom(String c_name, String r_name) {
		String sql = "select count(c_num) from chattingname where c_name = ? or c_name = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, r_name);
			ps.setString(2, c_name);
 			rs = ps.executeQuery();
 			if(rs.next()){
 				if(rs.getInt(1)==0) {
 					return true;
 				}
 			}
 			return false;
		} catch (Exception e) {System.out.println(e);}
  		return false;
	}
  	
  //11/2 도현 친구추가.
    public boolean friendadd(int user_num , String email) {
       String sql = "select (user_num) from user where user_email="+email;
       try {
          ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         if(rs.next()) {
            sql = "insert friend value(? , ?);";
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_num);
            ps.setInt(2, rs.getInt(1));
            ps.executeUpdate();
            return true;
         }
         return false;
     } 
       catch (Exception e) {
        System.out.println(e);
     }
       return false;
    }
  	
  	
  	
  	
  	
  	
  	
  	
  	
}
