package controller.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;

import model.Dao.boardDao.adminDao;
import model.Dto.memberDto.User_Dto;

/**
 * Servlet implementation class admin
 */
@WebServlet("/admin")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public admin() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */	
	// [2022-11-07 회원 전체호출 메소드 ]
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		ArrayList<User_Dto> list = adminDao.getInstance().all_user();

		JSONArray array = new JSONArray();
		for (int i = 0; i < list.size(); i++) { // 2.JSONObjexct 생성
			JSONObject object = new JSONObject();
			object.put("user_num", list.get(i).getUser_num());
			object.put("user_name", list.get(i).getUser_name());
			object.put("user_pw", list.get(i).getUser_pw());
			object.put("user_email", list.get(i).getUser_email());
			object.put("user_phone", list.get(i).getUser_phone());
			object.put("user_department", list.get(i).getUser_department());
			object.put("user_birth", list.get(i).getUser_birth());
			object.put("user_date", list.get(i).getUser_date());
			array.add(object);
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(array);
	}
// [2022-11-08 회원 수정 기본정보 출력 메소드 ]
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		
		User_Dto dto = adminDao.getInstance().user_edit(user_num);
		JSONObject object = new JSONObject();
		object.put("user_num", dto.getUser_num());
		object.put("user_name", dto.getUser_name());
		object.put("user_pw", dto.getUser_pw());
		object.put("user_email", dto.getUser_email());
		object.put("user_phone", dto.getUser_phone());
		object.put("user_department", dto.getUser_department());
		object.put("user_birth", dto.getUser_birth());
		object.put("user_date", dto.getUser_date());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(object);
	}
	// [2022-11-08 회원 수정  처리 출력 메소드 ]
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		String user_name=(String)request.getParameter("user_name");
		String user_pw=(String)request.getParameter("user_pw");
		String user_email=(String)request.getParameter("user_email");
		String user_phone=(String)request.getParameter("user_phone");
		String user_department=(String)request.getParameter("user_department");
		User_Dto dto = new User_Dto();
		dto.setUser_num(user_num);dto.setUser_pw(user_pw);dto.setUser_phone(user_phone);
		dto.setUser_name(user_name);dto.setUser_email(user_email);dto.setUser_department(user_department);
		boolean result = adminDao.getInstance().edit_user(dto,user_num);//변수 끝
		
		System.out.println("수정 ///"+result);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
	
	}

}
