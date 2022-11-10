package model.Dao.boardDao;

import java.util.ArrayList;
import java.util.Locale.Category;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Dto.memberDto.BoardDto;
import model.Dto.memberDto.CategoryDto;
import model.Dto.memberDto.calDto;
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
	
	// 11/9 상진 
    // 댓글 등록
	public boolean rwrite(String r_content, String user_name, int b_no ) {
		String sql="insert into reply(r_content, user_name, b_no) value(?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, r_content );
			ps.setString(2, user_name );
			ps.setInt(3, b_no );
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println(e);}
		return false;
				
	}
	// 11/9 상진
	// 대-댓글 등록
	public boolean rrwrite(String r_content, String user_name , int b_no, int r_index ) {
		String sql="insert into reply(r_content , user_name  , b_no , r_index) value(?, ?, ?, ?) ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, r_content );
			ps.setString(2, user_name );
			ps.setInt(3, b_no );
			ps.setInt(4, r_index );
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println(e);}
		return false;
				
	}
	// 11/9 상진
	// 10. 댓글 리스트
	public JSONArray getrlist(int b_no) {
		JSONArray array = new JSONArray();
		String sql = "select r.r_content , r.r_date, u.user_name , r.r_no , u.user_profile, u.user_department , r.r_index from reply r, user u where r.user_name = u.user_name and r.b_no = "+b_no+" and r.r_index = 0 order by r.r_date desc;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject object = new JSONObject();
				object.put("r_content", rs.getString(1) );
				object.put("r_date", rs.getString(2) );
				object.put("user_name", rs.getString(3) );
				object.put("r_no", rs.getInt(4) );
				object.put("user_profile", rs.getString(5) );
				object.put("user_department", rs.getString(6) );
				object.put("r_index", rs.getInt(7) );
				array.add(object);
			}
		} catch (Exception e) {System.out.println(e);}
		return array;
	} 
	// 11/9 상진
	// 10-2). 대댓글 리스트
	public JSONArray getrrlist(int b_no, int r_index) {
		JSONArray array = new JSONArray();
		String sql = "select r.r_content , r.r_date, u.user_name , r.r_no , u.user_profile, u.user_department from reply r, user u where r.user_name = u.user_name and r.b_no = "+b_no+" and r.r_index = "+r_index+" order by r.r_date desc;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				JSONObject object = new JSONObject();
				object.put("r_content", rs.getString(1) );
				object.put("r_date", rs.getString(2) );
				object.put("user_name", rs.getString(3) );
				object.put("r_no", rs.getInt(4) );
				object.put("user_profile", rs.getString(5) );
				object.put("user_department", rs.getString(6) );
				array.add(object);
			}
		} catch (Exception e) {System.out.println(e);}
		return array;
	}
	
	// 11/10 상진
	// 11-1 카테고리 선택하여 글 작성 시, 내 회원번호로 부서를 가져와서 동일 여부 확인
	public String my_department(int user_num) {
		String sql = "select user_department from user where user_num = "+user_num;
		String result = "부서명은 무엇일까요";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if( rs.next() ) {
				
				return	rs.getString(1);
			}
			return result;
		} catch (Exception e) {System.out.println(e);}
		return result;
	}
	// 11-2
	public String department(int c_no) {
		String sql = "select c_name from category where c_no = "+c_no;
		String result = "c_no의 카테고리 이름은 무엇일까요";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if( rs.next() ) {
				return	rs.getString(1);
			}
			return result;
		} catch (Exception e) {System.out.println("c_no의 해당하는 카테고리 이름 가져오기"+e);	}
		return result;
	}
	
	// 11/10 상진
	// 달력 일정 등록
	public boolean caladd(String user_name, String t_date , String t_content) {
		String sql="insert into cal(user_name , t_date  , t_content) value(?, ?, ?) ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, user_name );
			ps.setString(2, t_date );
			ps.setString(3, t_content );
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("일정 등록 실패"+e);}
		return false;
	}
	
	// 11/10 상진
	// 달력 일정 정보 쓸어오기
	public ArrayList<calDto> callist(String user_name , String currentMonth) {
		ArrayList<calDto> list = new ArrayList<>();
		String sql = "select * from cal where user_name = ? and t_date like '____?%' ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.setString(2, currentMonth);
            rs = ps.executeQuery();
            while(rs.next()) {
            	calDto dto = new calDto(
           			rs.getInt(1),
           			rs.getString(2),
           			rs.getString(3),
           			rs.getString(4)
           			 );
            	 	list.add(dto);
            		}
           	 return list;
		} catch (Exception e) {System.out.println("일정 가져오기 실패"+e);}
		return null;
	} 
	
	
	
	
}
