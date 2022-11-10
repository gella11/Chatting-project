package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao.boardDao.boardDao;
import model.Dao.chattingDao.chattingDao;

@WebServlet("/rwrite")
public class rwrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public rwrite() {super();    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청
			// 타입 요청 [ 0 댓글 / 1 대댓글 ]
			// 타입 자료형은 무조건 문자형
			String type = request.getParameter("type");
			request.setCharacterEncoding("UTF-8");
			// 댓글 내용
			String r_content = request.getParameter("r_content");
			// 유저 번호로 회원 이름 가져오기
			int user_num = (Integer)request.getSession().getAttribute("user_num");
			String user_name = chattingDao.getInstacnDao().findname(user_num);
			// 게시물 번호 가져오기
			int b_no = (Integer)request.getSession().getAttribute("b_no");
			// 유저번호가 0 일경우 즉 회원이 아닌 경우
			if(user_num==0) {response.getWriter().print("0"); return;}// 비 로그인일 경우 반환
			
			
		// DB DAO
			boolean result = false;
			if(type.equals("reply")) {
				result = boardDao.getInstance().rwrite(r_content, user_name ,b_no);
				if(result) {response.getWriter().print("1");}
				else 	   {response.getWriter().print("2");}
				
			}
			else if(type.equals("rereply")) {
				int r_index = Integer.parseInt(request.getParameter("r_no"));
				result = boardDao.getInstance().rrwrite(r_content, user_name ,b_no, r_index);
				if(result) {response.getWriter().print("1");}
				else 	   {response.getWriter().print("2");}
				
			}
	}

}
