package controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

		int option = Integer.parseInt(request.getParameter("option"));
		if(option == 1) {

			// 도현 상진
			// [10/28]
			// 끝방번호
			int endroom = chattingDao.getInstacnDao().endroom();
			System.out.println(endroom+"끝방");

			int user_num = (Integer)request.getSession().getAttribute("user_num");
	
		    int f_num = Integer.parseInt(request.getParameter("chattingnum"));
		
		    String myname = chattingDao.getInstacnDao().findname(user_num);
		    String f_name = chattingDao.getInstacnDao().findname(f_num);
		
		    String c_name = myname+','+f_name;
		    String r_name = f_name+','+myname;
		    boolean nameresult = chattingDao.getInstacnDao().findroom(c_name , r_name);
		    System.out.println(nameresult+"중복검사");
		    
		    if(nameresult == true) {
		    	
		    	boolean result1 = chattingDao.getInstacnDao().chattingroom(endroom, user_num);
			    boolean result2 = chattingDao.getInstacnDao().chattingroom(endroom, f_num);
			    System.out.println("내 이름 채팅방 insert : "+ result1);
			    System.out.println("친구 이름 채팅방 insert :"+result2);
			    // 도현 상진
				// [10/28]
			    // 채팅방 이름 넣기
			    boolean result3 = chattingDao.getInstacnDao().chattingroomname(endroom,c_name);
			    System.out.println("채팅창 이름 넣기"+result3);
			    
			    HttpSession session = request.getSession();// 세션값 요청객체
				session.setAttribute("c_name", c_name);
				session.setAttribute("roomnumber", endroom+1);
				
			    response.setCharacterEncoding("UTF-8");
				response.getWriter().print(endroom+1);
		    }else {
		    	response.getWriter().print(!nameresult);
		    }
		    
		    
		    
		}else if( option == 2) {
			response.setCharacterEncoding("UTF-8");
			int c_num = Integer.parseInt(request.getParameter("c_num"));
			if(c_num>0) {
				ArrayList<String> listset =chattingDao.getInstacnDao().chattingname(c_num);
				JSONObject object = new JSONObject();
				object.put("roomnumber", listset.get(0));
				object.put("cname", listset.get(1));
				response.getWriter().print(object);
			}
			else {
				int roomnumber = (Integer)request.getSession().getAttribute("roomnumber");
				String cname = (String)request.getSession().getAttribute("c_name");
				JSONObject object = new JSONObject();
				object.put("roomnumber", roomnumber);
				object.put("cname", cname);
				response.getWriter().print(object);
			}
			
			
		}
		// 11/1 도현) 나의 채팅리스트 기본세팅
		else if( option == 3) {
			response.setCharacterEncoding("UTF-8");
			int user_num = Integer.parseInt(request.getParameter("user_num"));
			ArrayList<Integer> list = chattingDao.getInstacnDao().chattinglist(user_num);
			JSONArray array = new JSONArray();
			
			for(int A : list) {
				JSONObject object = new JSONObject();
				ArrayList<String> listset =chattingDao.getInstacnDao().chattingname(A);
				object.put("c_num", listset.get(0));
				object.put("c_name", listset.get(1));
				array.add(object);
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(array);
		}
		// 11/2 도현 친구추가하기 
		else if(option == 4) {
			int user_num = (Integer)request.getSession().getAttribute("user_num");
			String email = (String)request.getParameter("email");
			boolean result = chattingDao.getInstacnDao().friendadd(user_num,email);
			response.getWriter().print(result);
		}
		
	}

}
