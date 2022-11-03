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
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.Dao.chattingDao.chattingDao;
import model.Dto.memberDto.singUp_Dto;


@WebServlet("/infochang")
public class info_chang extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public info_chang() {super();  }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String msg = request.getParameter("user_msg");
		
		// 기존 정보 뿌려주기
		int my_num = (Integer)request.getSession().getAttribute("user_num");
		singUp_Dto dto = chattingDao.getInstacnDao().my_info(my_num);
		
		// JSON
		JSONObject object = new JSONObject();
		object.put("user_num", 		dto.getUser_num() );
		object.put("user_name", 	dto.getUser_name() );
		object.put("user_profile",  dto.getUser_profile() );
		object.put("user_msg",		dto.getUser_msg() );
	
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(object);
				
		////////////////////////////////////////////////////////////
		// 업데이트

		/* 첨부파일이 있을경우 [ 업로드 용 ] */
		MultipartRequest multi = new MultipartRequest(
				request, 
				request.getSession().getServletContext().getRealPath("/img") , 
				1024*1024*10,
				"UTF-8", 
				new DefaultFileRenamePolicy() );
		
		String user_num = multi.getParameter("user_num");			
		String user_profile = multi.getFilesystemName("user_profile"); 
		String user_msg = multi.getParameter("user_msg");
		
		boolean result = new chattingDao().getInstacnDao().setinfo(user_num , user_profile, user_msg);
		response.getWriter().print(result);
	}

}
