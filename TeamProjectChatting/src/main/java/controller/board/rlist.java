package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import model.Dao.boardDao.boardDao;

@WebServlet("/rlist")
public class rlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public rlist() {super();    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청
				String type = request.getParameter("type");
				int b_no = (Integer)request.getSession().getAttribute("b_no");

				
				// DB
				JSONArray array = new JSONArray();
				if(type.equals("reply")) {
					array = boardDao.getInstance().getrlist(b_no);
				}
				else if(type.equals("rereply")) {
					int r_index = Integer.parseInt(request.getParameter("r_no"));
					array = boardDao.getInstance().getrrlist(b_no , r_index);
				}
			
				
				// 3. 결과
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(array);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
