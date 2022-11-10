package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

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
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
	
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int user_num=Integer.parseInt(request.getParameter("user_num"));
		JSONArray list = adminDao.getInstance().detail_employee(user_num);
				
		System.out.println("어라이////"+list);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(list);
	
	}

}
