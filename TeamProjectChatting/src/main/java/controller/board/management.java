package controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Dao.boardDao.adminDao;

/**
 * Servlet implementation class management
 */
@WebServlet("/management")
public class management extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public management() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		String preformace =request.getParameter("preformace");
	
		boolean result = adminDao.getInstance().Preformace(preformace,user_num);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		
		System.out.println("dbsdbsjdb//"+user_num);
		
		ArrayList<Integer> list = adminDao.getInstance().chart(user_num);
		JSONArray array = new JSONArray();
		for( int a: list ) {
			array.add(a);
		}
		System.out.println("ddddd./."+array);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(array);
	
	}

}
