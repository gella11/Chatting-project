package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Dao.memberDao.memberDao;

@WebServlet("/member/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_email = request.getParameter("mid"); // http서버에 ajax로 통신한 아이디값 요청
		String mpassword = request.getParameter("mpassword");// http서버에 ajax로 통신한 비밀번호값 요청
		
		System.out.println("아이디"+user_email);System.out.println("비밀번호"+mpassword);
		int result = memberDao.getInstance().login(user_email, mpassword);

		if (result == 1) { // 리턴값이 1이면 일치하는 아이디가 있으면 
			int user_num=memberDao.getInstance().user_num(user_email); // 회원번호 Dao접근
			
			HttpSession session = request.getSession();// 세션값 요청객체
			
			session.setAttribute("user_num", user_num); //세션에 회원번호 값 저장 
		}
	
		response.getWriter().print(result); //결과 전송
	}

	public login() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
