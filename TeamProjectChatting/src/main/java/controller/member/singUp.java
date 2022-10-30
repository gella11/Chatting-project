package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao.memberDao.memberDao;

@WebServlet("/member/singUp")
public class singUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public singUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// [10-30] 허혜영 - 핸드폰&이메일 중복 검사
		String user_phone = request.getParameter("user_phone"); // 핸드폰 중복 검사 값 가져오기
		String user_email = request.getParameter("user_email"); // 이메일 중복 검사 값 가져오기
		response.setCharacterEncoding("UTF-8");
		
		if( user_phone != null ) { // 핸드폰 번호가 기존에 등록되어있는지 확인
			boolean result = new memberDao().getInstance().phone_check( user_phone );
			response.getWriter().print(result);
		}
		if ( user_email != null ) { // 이메일이 기존에 등록되어있는지 확인
			boolean result = new memberDao().getInstance().email_check( user_email );
			response.getWriter().print(result);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user_name = request.getParameter("user_name");
		String user_phone = request.getParameter("user_phone");
		String user_email = request.getParameter("user_email");
		String user_pw = request.getParameter("user_pw");
	
		
		boolean result = new memberDao().getInstance().sign_up(user_name, user_pw, user_email, user_phone);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
		
	
	}

}
