package model.Dao.boardDao;

import java.util.ArrayList;

import model.Dao.chattingDao.chattingDao;
import model.Dto.memberDto.BoardDto;


public class boardDao extends SuperDao_B{
	
	private static boardDao bdao =new boardDao();
	public static boardDao getInstacnDao() {return bdao;}
	
	// 1. 글 등록
		public boolean write(String b_title, String b_content, String myname ,String b_file) {
			String sql="insert into board(btitle,bcontent,myname,bfile) value(?, ?, ?, ?) where user_name = ? ";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, b_title );
				ps.setString(2, b_content );
				ps.setString(3, b_file );
				ps.setString(4, myname );
				ps.setString(5, myname );
				ps.executeUpdate();
				return true;
			} catch (Exception e) {System.out.println(e);}
			return false;
		}
		// 2. 글 출력 [ JSP 용 ]
		public ArrayList<BoardDto> getlist(int startrow , int listsize, String key , String keyword) {
			ArrayList<BoardDto> list = new ArrayList<>();
			String sql ="";
			if(!key.equals("") && !keyword.equals("")) {
				sql = "select b.* , m.mid from member m , board b where m.mno = b.mno and "+key+" like '%"+keyword+"%' order by b.bdate desc limit "+startrow+" , "+listsize+";";
			}else {
				sql = "select b.* , m.mid from member m , board b where m.mno = b.mno order by b.bdate desc limit "+startrow+" , "+listsize+";";
			}
			
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					BoardDto dto = new BoardDto(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getInt(7),
							rs.getString(8)
							);
						list.add(dto);
				}
				return list;
			} catch (Exception e) {System.out.println(e);}
			return list;
		}
		
		// 3. 글 조회 
		public BoardDto getboard(int b_no) {
			String sql ="select * from board  where b_no = ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1 , b_no);
				rs = ps.executeQuery();
				while(rs.next()) {
					BoardDto dto = new BoardDto(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getInt(7),
							rs.getString(8)
							);
					return dto;
				}
			} catch (Exception e) {System.out.println(e);}
			return null;
		}
		
		// 4. 글 삭제
		public boolean delete(int bno) {
			String sql = "delete from board where bno="+bno+"; ";
			try {
				ps = con.prepareStatement(sql);
				int count = ps.executeUpdate();
				if(count == 1)
					return true;
			} catch (Exception e) {System.out.println(e);}
			return false;
		}
		
		// 5. 첨부파일만 삭제 [업데이트]
		public boolean bfiledelete(int bno) {
			String sql = "update board set bfile = null where bno = "+bno;
			try {
				ps = con.prepareStatement(sql);
				ps.executeUpdate();
				return true;
			} catch (Exception e) {System.out.println(e);	}
			return false;
		}
		
		// 6. 게시물 수정
		public boolean bupdate(int b_no, String b_title, String b_content, String b_file) {
			String sql = "update board set btitle = ?, bcontent = ?, bfile = ? where bno = ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, b_title);
				ps.setString(2, b_content);
				ps.setString(3, b_file);
				ps.setInt(4, b_no);
				ps.executeUpdate();
				return true;
			} catch (Exception e) {System.out.println(e);}
			return false;
		}
		
		// 7. 조회수 [업데이트]
			public boolean view(int bno) {
				String sql2="update board set bview = bview +1 where bno = ?;";
				try {
					ps = con.prepareStatement(sql2);
					ps.setInt(1 , bno);
					ps.executeUpdate();
					return true;
				} catch (Exception e) {System.out.println("조회수 업데이트 실패");}
				return false;
			}
}
