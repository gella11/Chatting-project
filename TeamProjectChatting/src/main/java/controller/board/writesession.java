package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Dao.boardDao.boardDao;

@WebServlet("/writesession")
public class writesession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public writesession() {super();    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int user_num = (Integer)request.getSession().getAttribute("user_num");
		String my_department = boardDao.getInstance().my_department(user_num);
		
		
		int c_no = Integer.parseInt( request.getParameter("c_no"));
		String c_name = boardDao.getInstance().department(c_no);
		
		System.out.println("유저번호▶"+user_num);
		System.out.println("카테고리번호▶"+c_no);
		System.out.println("내 부서 ▶"+my_department);
		System.out.println("카테고리이름▶"+c_name);
		
		if(my_department.equals(c_name)) {
			// 세션 객체 생성
			HttpSession session = request.getSession();
			
			// 클릭한 카테고리 번호를 세션에 저장
			request.getSession().setAttribute("c_no", c_no);
			response.getWriter().print(true);
			
		}else {
			response.getWriter().print(false);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
