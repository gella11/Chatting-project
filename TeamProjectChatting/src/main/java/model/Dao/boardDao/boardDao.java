package model.Dao.boardDao;

import java.util.ArrayList;

import model.Dto.memberDto.BoardDto;

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
	} // getlist e
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
