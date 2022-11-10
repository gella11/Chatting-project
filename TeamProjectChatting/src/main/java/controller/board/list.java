package controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Dao.boardDao.boardDao;
import model.Dao.memberDao.memberDao;
import model.Dto.memberDto.BoardDto;
import model.Dto.memberDto.singUp_Dto;

@WebServlet("/board/list")
public class list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public list() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String pageinfo = request.getParameter("pageinfo");
		
		// 1. 검색 가져오기
		String key = request.getParameter("key");
		String keyword = request.getParameter("keyword");
		//System.out.println("리스트 key ::: " + key);
		//System.out.println("리스트 keyword ::: " + keyword);
		
		// 2. 페이지당 게시물 수 가져오기
		int list_size = Integer.parseInt(request.getParameter("list_size"));
				
		int c_no = Integer.parseInt(request.getParameter("c_no"));
		System.out.println("c_no ::: " + c_no);
		
		// 3. 전체 게시물 수
		int total_size = boardDao.getInstance().gettotal_size( key, keyword, c_no );
		
		// 4. 전체 페이지 수 계산
		int total_page = 0;
		if( total_size % list_size == 0 ) {
			total_page = total_size / list_size;
		}else {
			total_page = total_size / list_size+1;
		}
		
		// 5. 현재 페이지 수
		int page = Integer.parseInt( request.getParameter("page"));
		
		// 6. 페이지별 시작 게시물 행번호
		int start_row = (page-1)*list_size;
		
		// 7. 화면에 표시할 최대 버튼 수
		int btn_size = 5;
		
		// 8. 버튼 시작 번호
		int start_btn = ( ( page-1 ) / btn_size )* btn_size+1;
		
		// 9. 버튼 끝 번호
		int end_btn = start_btn + ( btn_size );
		
		// 10. 만약에 endbtn이 마지막 페이지보다 클 경우 마지막 버튼 번호는 마지막 페이지 번호
		if ( end_btn > total_page ) {
			end_btn = total_page;
		}
		
		// ----------------------------------------------------------------------
		
		int user_num = (Integer)request.getSession().getAttribute("user_num");
		// String user_name = (String)request.getSession().getAttribute("user_name");

		// 부서명 가져오기
		String user_department = memberDao.getInstance().getuser_department( user_num ); 
		
		// 프로필 이미지 가져오기
		String user_profile = memberDao.getInstance().getuser_profile( user_num ); 
		
		// 페이징 처리에 필요한 정보 담은 것
		JSONObject boards = new JSONObject();
		
		// dao 처리한 내용 불러오기
		ArrayList< BoardDto > list = boardDao.getInstance().getlist( c_no, start_row, list_size, key, keyword );
		
		JSONArray array = new JSONArray();
		for( int i = 0; i < list.size(); i++ ) {
			JSONObject object = new JSONObject();
			object.put("b_no", list.get(i).getB_no());
			object.put("b_title", list.get(i).getB_title());
			object.put("b_content", list.get(i).getB_content());
			object.put("b_file", list.get(i).getB_file());
			object.put("b_date", list.get(i).getB_date());
			object.put("b_view", list.get(i).getB_view());
			object.put("c_no", list.get(i).getC_no());
			object.put("user_name", list.get(i).getUser_name());
			object.put("user_department", list.get(i).getUser_department());
			object.put("user_profile", list.get(i).getUser_profile());
			array.add(object);
		}

		boards.put("total_page", total_page);	// 전체 페이지수
		boards.put("data", array);				// 게시물 리스트
		boards.put("start_btn", start_btn);		// 버튼의 시작 번호	
		boards.put("end_btn", end_btn);			// 버튼의 끝 번호
		boards.put("total_size", total_size);	// 전체 게시물 수
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(boards);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
