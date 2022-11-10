package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.Dao.boardDao.boardDao;
import model.Dao.chattingDao.chattingDao;
import model.Dao.memberDao.memberDao;
import model.Dto.memberDto.BoardDto;

@WebServlet("/tboard/view")
public class view extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public view() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 선택한 게시글 번호 세션 정보 호출
		int b_no = (Integer)request.getSession().getAttribute("b_no");
		System.out.println("서블릿 b_no : " + b_no);	
		
		// 로그인 세션 정보 호출
		int user_num = (Integer)request.getSession().getAttribute("user_num");
		
		// 선택한 게시글 번호 가지고있기
		BoardDto dto = boardDao.getInstance().board_view( b_no );
		
		// 부서명 호출
		String user_department = memberDao.getInstance().getuser_department( user_num );
		
		// 유저 이름 호출
		String user_name = chattingDao.getInstacnDao().findname( user_num );
		
		// 프로필 이미지 가져오기
		String user_profile = memberDao.getInstance().getuser_profile( user_num ); 
	
		// dao에서 처리한 개별 게시글 담아 옴
		BoardDto bdto = boardDao.getInstance().board_view( b_no );
		
		// 제이슨 오브젝트로 포장
		JSONObject object = new JSONObject();
		object.put("b_no", dto.getB_no());
		object.put("b_title", dto.getB_title());
		object.put("b_content", dto.getB_content());
		object.put("b_file", dto.getB_file());
		object.put("b_date", dto.getB_date());
		object.put("b_view", dto.getB_view());
		object.put("c_no", dto.getC_no());
		object.put("user_name", dto.getUser_name());
		object.put("user_department", user_department );
		object.put("user_profile", user_profile );
		
		// 삭제 수정 버튼 활성화 하기 회원 번호가 있고 작성자와 로그인된 이름이 동일거나 이름이 admin 이면
		if( user_num != 0 && user_name.equals( dto.getUser_name()) || user_name.equals("admin") ) {
			object.put("btn_action", true);
		}
				
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print( object );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 첨부파일이 있을 경우에만 사용 [ 업로드용 ]
		MultipartRequest multi = new MultipartRequest(
				request,
				request.getSession().getServletContext().getRealPath("/img"), // getRealPath : 배포한 서버의 경로
				1024*1240*10,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		
		int b_no = Integer.parseInt(multi.getParameter("b_no"));
		String b_title = multi.getParameter("b_title");
		String b_content = multi.getParameter("b_content");
		String b_file = multi.getFilesystemName("b_file");		
		
		BoardDto dto = new BoardDto( b_no, b_title, b_content, b_file );
				
		boolean result = new boardDao().b_update( dto );
		response.setCharacterEncoding( "UTF-8" );
		response.getWriter().print( result );
		
	
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		
		System.out.println("bno 서블릿 ::: " + b_no );
		boolean result = new boardDao().b_delete(b_no);
		
		response.getWriter().print(result);
		System.out.println("삭제 결과 서블릿 ::: " + result);
		
		
		
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
