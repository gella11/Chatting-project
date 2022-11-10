package controller.member;

import java.io.File;
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
import org.json.simple.parser.JSONParser;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.Dao.chattingDao.chattingDao;
import model.Dao.memberDao.memberDao;
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
			object.put("user_email",	list.get(i).getUser_email() );
			array.add(object);
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(array);
		
		
		
	}

	
	
	
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int option = Integer.parseInt(request.getParameter("option"));
		int user_num = (Integer)request.getSession().getAttribute("user_num");
		request.setCharacterEncoding("UTF-8");
		//11/9 도현 단톡방만들기 
		if(option==0) {
			String chattingname = (String)request.getParameter("name");
			String list = (String)request.getParameter("list");
			JSONParser parser = new JSONParser();
			try {
				JSONArray array = (JSONArray) parser.parse(list);
				
				System.out.println("array:"+array.toString());
				int endroom = chattingDao.getInstacnDao().endroom();		
				for(int i=0; i<array.size(); i++) {
					System.out.println("친구회원번호"+array.get(i));
					boolean result = chattingDao.getInstacnDao().chattingroom(endroom, Integer.parseInt(String.valueOf(array.get(i))));
					if(result==false) {return;}
				}
				//유저 채팅방에 넣기.
				boolean result1 = chattingDao.getInstacnDao().chattingroom(endroom, user_num);
				//채팅방 이름 넣기.
				boolean result2 = chattingDao.getInstacnDao().chattingroomname(endroom,chattingname);
				if(result1&&result2) {
					response.getWriter().print(true);
				}		
			} 
			catch (Exception e) {
				System.out.println("단톡방 형변환 오류"+e);
			}		
		}
		else if(option == 1) {

			// 도현 상진
			// [10/28]
			// 끝방번호
			int endroom = chattingDao.getInstacnDao().endroom();
		    int f_num = Integer.parseInt(request.getParameter("chattingnum"));
		
		    String myname = chattingDao.getInstacnDao().findname(user_num);
		    String f_name = chattingDao.getInstacnDao().findname(f_num);
		
		    String c_name = myname+','+f_name;
		    String r_name = f_name+','+myname;
		    boolean nameresult = chattingDao.getInstacnDao().findroom(c_name , r_name);
		    
		    if(nameresult == true) {
		    	
		    	boolean result1 = chattingDao.getInstacnDao().chattingroom(endroom, user_num);
			    boolean result2 = chattingDao.getInstacnDao().chattingroom(endroom, f_num);
			    // 도현 상진
				// [10/28]
			    // 채팅방 이름 넣기
			    boolean result3 = chattingDao.getInstacnDao().chattingroomname(endroom,c_name);
			    response.setCharacterEncoding("UTF-8");
				response.getWriter().print(endroom+1);
		    }    
		}
		else if( option == 2) {
			int c_num = Integer.parseInt(request.getParameter("c_num"));
			response.setCharacterEncoding("UTF-8");
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
			String email = (String)request.getParameter("email");
			boolean result = chattingDao.getInstacnDao().friendadd(user_num,email);
			response.getWriter().print(result);
		}
		// 11/3 도현 메시지 저장하기.
		else if(option == 5) {
			String msg = request.getParameter("msg");
			JSONParser parser = new JSONParser();
			try {
				JSONObject object = (JSONObject) parser.parse(msg);
				String type = String.valueOf(object.get("type"));
				int mid = Integer.parseInt(String.valueOf(object.get("mid")));
				String content = String.valueOf(object.get("content"));
				String name = String.valueOf(object.get("name"));
				String img = String.valueOf(object.get("img"));
				String date = String.valueOf(object.get("date"));
				boolean result = chattingDao.getInstacnDao().setchat(mid,type,name,content,img,date);
				response.getWriter().print(result);			
			} catch (Exception e) {
				System.out.println(e+"메시지저장 형변환 오류");
			}		
		}
		// 11/10 도현 채팅방 나가기.
		else if(option == 6){
			int c_num = Integer.parseInt(request.getParameter("c_num"));
			//채팅방 목록에서 삭제.
			boolean result = chattingDao.getInstacnDao().deletechat(user_num, c_num);
			if(result) {
				//채팅방에 들어와있는 사람 체크
				int count= chattingDao.getInstacnDao().searchchat(c_num);
				if(count==0) {
					//아무도없으면 채팅방내역삭제
					chattingDao.getInstacnDao().deletemessage(c_num);
					response.getWriter().print("allout");
				}
				else {
					//누군가는 있다.
					response.getWriter().print(true);
				}
			}		
		}
		// 11/7 도현 내프로필 불러오기
		else if(option == 7) {
			ArrayList<String> list = memberDao.getInstance().getprofile(user_num);
			JSONObject object = new JSONObject();
			object.put("profile", list.get(0));
			object.put("name", list.get(1));
			object.put("msg", list.get(2));
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(object);
		}
		// 11/8 도현 채팅방의 마지막 대화만 가져오기
		else if(option==8) {
			int c_num = Integer.parseInt(request.getParameter("c_num"));
			System.out.println("마지막대화"+c_num);
			String lastStr = chattingDao.getInstacnDao().lastStr(c_num);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(lastStr);		
		}
		
	
		
	}
	@Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 11/7 도현 내프로필 바꾸기
		String uploadpath = request.getSession().getServletContext().getRealPath("/img");
		int user_num = (Integer)request.getSession().getAttribute("user_num");
		System.out.println(uploadpath);
		
		MultipartRequest multi = new MultipartRequest(
				request,uploadpath,1024*1024*100,"UTF-8",new DefaultFileRenamePolicy());
		
		String usermsg = multi.getParameter("usermsg");
		
		String userprofile = multi.getFilesystemName("userprofile");
		
		System.out.println(usermsg); 
		System.out.println(userprofile);
		

		if( userprofile == null ) {  
			String oldimg = new memberDao().getprofile(user_num).get(0);
			System.out.println(oldimg);
			deletefile( request.getSession() , oldimg );
		}
		
		boolean result = memberDao.getInstance().setprofile(user_num,usermsg,userprofile);

		response.getWriter().print(result);
		
    }
	public void deletefile( HttpSession session ,  String pimg ) {
		String deletepath = session.getServletContext().getRealPath("/TeamProjectChatting/pimg/"+ pimg );
		File file = new File( deletepath );
		if( file.exists() ) file.delete();	// 해당 경로에 존재하는 파일을 삭제
	}
}
