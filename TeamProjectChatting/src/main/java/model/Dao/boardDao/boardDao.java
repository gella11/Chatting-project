package model.Dao.boardDao;

import java.util.ArrayList;
import java.util.Locale.Category;

import org.json.simple.JSONArray;

import model.Dto.memberDto.BoardDto;
import model.Dto.memberDto.CategoryDto;
import model.Dto.memberDto.chattingDto;

public class boardDao extends SuperDao_B{

	private static boardDao bdao = new boardDao();
	public static boardDao getInstance() {
		return bdao;
	}
		
	// 1. 전체 게시글 출력 - 11/6 혜영
	public ArrayList< BoardDto > getlist( int c_no , int start_row, int list_size, String key, String keyword ) {
		ArrayList< BoardDto > list = new ArrayList<BoardDto>();
		String sql = "";
		System.out.println("key ::: " + key);
		System.out.println("key ::: " + keyword);
		// 부서 전체보기
		if( c_no == 1 && !key.equals("") && !keyword.equals("") ) { // 카테고리 번호가 1이고 검색이 있을 때[키워드검색]
			sql = "select b.*, u.user_department, u.user_profile\r\n"
					+ "from board b, user u\r\n"
					+ "where b.user_name = u.user_name and b." + key + " like '%"+ keyword +"%'\r\n"
					+ "order by b_date desc\r\n"
					+ "limit " + start_row + ", " + list_size;
			System.out.println("카테고리 번호가 1이고 검색이 있을 때(전체검색)");
		}else if(c_no != 1 && !key.equals("") && !keyword.equals("")){ // 카테고리가 1이 아니고 검색이 있을 때[개별]
			sql = "select b.*, u.user_department, u.user_profile\r\n"
					+ "from board b, user u\r\n"
					+ "where b.user_name = u.user_name && b.c_no ="+ c_no +"\r\n and b." + key + " like '%"+ keyword +"%'\r\n"
					+ "order by b_date desc \r\n"
					+ "limit " + start_row + ", " + list_size;
			System.out.println("카테고리가 1이 아니고 검색이 없을때");
		}else if(c_no != 1 && key.equals("") && keyword.equals("")){ // 카테고리가 1이 아니고 검색이 없을때[개별]
			sql = "select b.*, u.user_department, u.user_profile\r\n"
					+ "from board b, user u\r\n"
					+ "where b.user_name = u.user_name && b.c_no ="+ c_no +"\r\n"
					+ "order by b_date desc \r\n"
					+ "limit " + start_row + ", " + list_size;
			System.out.println("카테고리가 1이 아니고 검색이 없을때");
		}else if(c_no == 1 && key.equals("") && keyword.equals("")){ // 카테고리가 1이 이고 검색이 없을때[전체]
			sql = "select b.*, u.user_department, u.user_profile\r\n"
					+ "from board b, user u\r\n"
					+ "where b.user_name = u.user_name\r\n"
					+ "order by b_date desc \r\n"
					+ "limit " + start_row + ", " + list_size;
			System.out.println("start_row::" + start_row);
			System.out.println("list_size::" + list_size);	
			System.out.println("카테고리가 1이 이고 검색이 없을때");
		}
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
						rs.getString(8),
	                    rs.getString(9),
	                    rs.getString(10));
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
		System.out.println();
		try {
			ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
            	CategoryDto dto = new CategoryDto(
           			 rs.getInt(1),
           			 rs.getString(2));
            	 	list.add(dto);
            		}
            System.out.println(list);
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
	
	// 2. 개별 게시글 출력 - 11/7 혜영
	public BoardDto board_view( int b_no ) {
		String sql = "select b.*, u.user_department, u.user_profile\r\n"
				+ "from board b, user u\r\n"
				+ "where b.user_name = u.user_name and b.b_no = "+ b_no;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				BoardDto dto = new BoardDto(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getString(8),
	                    rs.getString(9),
	                    rs.getString(10));
				return dto;
			}
		} catch (Exception e) {
			System.out.println("개별글 출력 오류 : " + e);
		}
		return null;
	} // board_view e
	
	
	// 3. 게시글 수정 - 11/8 혜영
	public boolean b_update( BoardDto dto ) {
		String sql = "update board set b_title = ?, b_content = ?, b_file = ? where b_no = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getB_title());
			ps.setString(2, dto.getB_content());
			ps.setString(3, dto.getB_file());
			ps.setInt(4, dto.getB_no());
			ps.executeUpdate();
			System.out.println( dto.toString() );
			return true;
		} catch (Exception e) {
			System.out.println("게시글 수정 오류 : " + e);
		}
		return false;
	} // b_update e
	
	
	// 4. 게시글 삭제 - 11/8 혜영
	public boolean b_delete( int b_no ) {
		String sql = " delete from board where b_no = " + b_no;
		
		try {
			ps = con.prepareStatement(sql);
			int count = ps.executeUpdate();
			if( count == 1 ) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("게시글 삭제 오류 : " + e);
		}
		return false;
	} // b_delete e
	
	
	// 11/8 [상진]
    // 게시판 카테고리 별 글 가져오기
     public ArrayList<BoardDto> boardlist (int c_no){
        ArrayList<BoardDto> array = new JSONArray();
        String sql = "select * from board where c_no ="+c_no+" ";
        try {
           ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
          while(rs.next()){
             BoardDto dto = new BoardDto(
                   rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10)
                    );
             array.add(dto);
          }
         return array;
      } catch (Exception e) {System.out.println("카테고리 별 글 가져오기 오류" + e);   }
        return null;
     }
	
	
	// 전체 게시물 수  - 11/9 혜영
    public int gettotal_size( String key, String keyword ) {
    	
    	String sql = "";
    	if( !key.equals("") && !keyword.equals("") ) { // 검색이 있을 경우
    		sql = "select count(*)\r\n"
				+ "from user u, board b\r\n"
				+ "where b.user_name = u.user_name and "+ key +" like '%"+ keyword +"%'";
    	}else { // 검색이 없을 경우
			sql = "select count(*)\r\n"
				+ "from user u, board b\r\n"
				+ "where b.user_name = u.user_name";
		}
    	
    	try {
    		ps = con.prepareStatement(sql);
    		rs = ps.executeQuery();
    		if( rs.next() ) {
    			return rs.getInt(1);
    		}
		} catch (Exception e) {
			System.out.println("전체 게시물 수 처리 오류 : " + e);
		}
		return 0;
	} // gettotal_size e
	
	
	
	
	
	
	
	
	
	
	
	
	
}
