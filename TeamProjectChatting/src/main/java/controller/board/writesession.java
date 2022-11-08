package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/writesession")
public class writesession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public writesession() {super();    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int c_no = Integer.parseInt( request.getParameter("c_no"));
		
		// 세션 객체 생성
		HttpSession session = request.getSession();
		
		// 클릭한 카테고리 번호를 세션에 저장
		request.getSession().setAttribute("c_no", c_no);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
