package model.Dao.boardDao;

import java.util.ArrayList;
import java.util.Locale.Category;

import model.Dto.memberDto.BoardDto;
import model.Dto.memberDto.CategoryDto;
import model.Dto.memberDto.chattingDto;

public class boardDao extends SuperDao_B{

	private static boardDao bdao = new boardDao();
	public static boardDao getInstance() {
		return bdao;
	}
		
	// 11/6 혜영 전체 게시글 출력
	public ArrayList< BoardDto > getlist() {
		
		ArrayList< BoardDto > list = new ArrayList();
		String sql = "select  u.user_profile, u.user_name, u.user_department, b.b_no, b.b_title, b.b_date\r\n"
				+ "from user u, board b\r\n"
				+ "order by b_date desc"; 

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while ( rs.next() ) {
				BoardDto dto = new BoardDto(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getString(9));
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			System.out.println("전체 게시글 출력 오류 : " + e);
		}
		return list;
	}

	// 11/7 [상진]
	// 카테고리 정보 가져오기
	public ArrayList<CategoryDto> categorylist() {
		ArrayList<CategoryDto> list = new ArrayList<>();
		String sql = "select * from category";
		try {
			ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
            	CategoryDto dto = new CategoryDto(
           			 rs.getInt(1),
           			 rs.getString(2));
            	 	list.add(dto);
            		}
           	 return list;
		} catch (Exception e) {System.out.println("카테고리 리스트 가져오기 실패"+e);}
		return null;
	} 
	
	// 11/7 [상진]
	// 글 쓰기
    public boolean write(String b_title , String b_content , String b_file , int c_no , String user_name) {
    	String sql = "insert into board(b_title,b_content,b_file,c_no,user_name) values(?,?,?,?,?);";
    	try {
    		ps = con.prepareStatement(sql);
    		ps.setString(1, b_title );
  			ps.setString(2, b_content );
  			ps.setString(3, b_file );
  			ps.setInt(4, c_no );
  			ps.setString(5, user_name );
  			ps.executeUpdate();
  			return true;
		} 
    	catch (Exception e) {System.out.println("글 등록 실패"+e);}
    	return false;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
