package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Dao.boardDao.boardDao;
import model.Dto.memberDto.BoardDto;

/**
 * Servlet implementation class viewselect
 */
@WebServlet("/board/viewselect")
public class viewselect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewselect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int b_no = Integer.parseInt( request.getParameter("b_no"));
		
		// 세션 객체 생성
		HttpSession session = request.getSession();
		
		// 클릭한 게시물 번호를 세션에 저장
		request.getSession().setAttribute("b_no", b_no);
		// System.out.println("서블릿 b_no : " + b_no);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
