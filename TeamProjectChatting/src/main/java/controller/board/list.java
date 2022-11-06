package controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Dao.boardDao.boardDao;
import model.Dto.memberDto.BoardDto;

@WebServlet("/board/list")
public class list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public list() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ArrayList< BoardDto > list = boardDao.getInstance().getlist();
		
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
			object.put("user_name", list.get(i).getUser_name()); // dto가 name이라서 우선 name으로
			array.add(object);
		}
		System.out.println(array);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(array);
		
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
