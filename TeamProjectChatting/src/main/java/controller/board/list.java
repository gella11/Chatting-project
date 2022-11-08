package controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Dao.boardDao.boardDao;
import model.Dao.memberDao.memberDao;
import model.Dto.memberDto.BoardDto;
import model.Dto.memberDto.singUp_Dto;

@WebServlet("/board/list")
public class list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public list() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int user_num = (Integer)request.getSession().getAttribute("user_num");
		// String user_name = (String)request.getSession().getAttribute("user_name");

		// 부서명 가져오기
		String user_department = memberDao.getInstance().getuser_department( user_num ); 
		
		// 프로필 이미지 가져오기
		String user_profile = memberDao.getInstance().getuser_profile( user_num ); 
		
		// dao 처리한 내용 불러오기
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
			object.put("user_name", list.get(i).getUser_name());
			object.put("user_department", user_department );
			object.put("user_profile", user_profile );
			array.add(object);
			
		}
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(array);
		
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
