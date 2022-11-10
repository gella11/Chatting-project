package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao.boardDao.adminDao;

/**
 * Servlet implementation class admin_Category
 */
@WebServlet("/admin_Category")
public class admin_Category extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public admin_Category() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		String category = request.getParameter("category");
		
		boolean result=adminDao.getInstance().addcategory(category);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int c_no=Integer.parseInt(request.getParameter("c_no"));
		
		boolean result=adminDao.getInstance().deletecategory(c_no);
		response.setCharacterEncoding("UTF-8");
		System.out.println("결과받기"+result);
		
		response.getWriter().print(result);
	
	}

}
