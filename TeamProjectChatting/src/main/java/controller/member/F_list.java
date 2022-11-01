package controller.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Dao.chattingDao.chattingDao;
import model.Dao.memberDao.memberDao;
import model.Dto.memberDto.F_list_Dto;
import model.Dto.memberDto.singUp_Dto;


@WebServlet("/F_list")
public class F_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public F_list() {super();  }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	    		
	    		
		// 요청 
		int my_num = (Integer)request.getSession().getAttribute("user_num");
		// DAO
		ArrayList<Integer> friendlist  = chattingDao.getInstacnDao().getinfolist(my_num);
	    ArrayList<singUp_Dto> list = chattingDao.getInstacnDao().f_list_info(friendlist);
	    
	    
	    
		// JSON
		JSONArray array = new JSONArray();
		for( int i = 0  ; i<list.size() ; i++ ) {
			JSONObject object = new JSONObject();
			object.put("user_num", 		list.get(i).getUser_num() );
			object.put("user_name", 	list.get(i).getUser_name() );
			object.put("user_profile",  list.get(i).getUser_profile() );
			object.put("user_msg",		list.get(i).getUser_msg() );
			array.add(object);
		}
		System.out.println(array);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(array);
		
		
		
	}

	
	
	
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 도현 상진
		// [10/28]
		// 끝방번호
		int endroom = chattingDao.getInstacnDao().endroom();
		System.out.println("끝방번호 : " + endroom);
		// 도현 상진
		// [10/28]
		// 내 번호
		int user_num = (Integer)request.getSession().getAttribute("user_num");
		System.out.println("내 회원번호 : "+user_num);
		// 도현 상진
		// [10/28]
		// 친구 번호
	    int f_num = Integer.parseInt(request.getParameter("chattingnum"));
	    System.out.println("친구 회원번호 :"+f_num);
	    // 도현 상진
		// [10/28]
	    // 이름 가져오기
	    String myname = chattingDao.getInstacnDao().findname(user_num);
	    String f_name = chattingDao.getInstacnDao().findname(f_num);
	    System.out.println("내 이름 : "+myname);
	    System.out.println("친구 이름 : "+f_name);
	    // 도현 상진
		// [10/28]
	    // 이름 합치기
	    String c_name = myname+','+f_name;
	    System.out.println("이름 합치기 : " +c_name);
	    // 도현 상진
		// [10/28]
	    // 채팅방 추가 , 회원번호 넣기
	    boolean result1 = chattingDao.getInstacnDao().chattingroom(endroom, user_num);
	    boolean result2 = chattingDao.getInstacnDao().chattingroom(endroom, f_num);
	    System.out.println("내 이름 채팅방 insert : "+ result1);
	    System.out.println("친구 이름 채팅방 insert :"+result2);
	    // 도현 상진
		// [10/28]
	    // 채팅방 이름 넣기
	    boolean result3 = chattingDao.getInstacnDao().chattingroomname(endroom,c_name);
	    System.out.println("채팅창 이름 넣기"+result3);
	    
	    response.setCharacterEncoding("UTF-8");
		response.getWriter().print(endroom+1);
		
		
		
		
		
		
		
	}

}
