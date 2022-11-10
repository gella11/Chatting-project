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

import model.Dao.boardDao.boardDao;
import model.Dao.chattingDao.chattingDao;
import model.Dto.memberDto.calDto;

@WebServlet("/callist")
public class callist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public callist() { super();    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int user_num = (Integer)request.getSession().getAttribute("user_num");
		String user_name = chattingDao.getInstacnDao().findname(user_num);
		System.out.println("내 이름::"+user_name);
		
		String currentMonth = request.getParameter("currentMonth");
		System.out.println("currentMonth:::"+currentMonth);
		
		ArrayList< calDto  > list = boardDao.getInstance().callist(user_name , currentMonth);
		JSONArray array = new JSONArray();
		for( int i = 0; i < list.size(); i++ ) {
			JSONObject object = new JSONObject();
			object.put("t_no", list.get(i).getT_no());
			object.put("user_name", list.get(i).getUser_name());
			object.put("t_content", list.get(i).getT_content());
			object.put("t_date", list.get(i).getT_date());
			array.add(object);
		}
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(array);
		System.out.println("어레이::"+array);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
