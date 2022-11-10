package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao.boardDao.boardDao;

/**
 * Servlet implementation class adminbtn
 */
@WebServlet("/board/adminbtn")
public class adminbtn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminbtn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 게시판에서 관리자이면 버튼 출력
		
		// 로그인 세션 정보 호출
		int user_num = (Integer)request.getSession().getAttribute("user_num");
		
		System.out.println("user_num :: " + user_num);
		boolean result = boardDao.getInstance().admin_btn( user_num );
		response.getWriter().print(result);
		System.out.println("result :: " +result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
