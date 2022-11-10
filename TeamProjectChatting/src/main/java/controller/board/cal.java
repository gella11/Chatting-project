package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao.boardDao.boardDao;
import model.Dao.chattingDao.chattingDao;

@WebServlet("/cal")
public class cal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public cal() { super();    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		//회원 번호 가져와서 이름 뽑아오기
		int user_num = (Integer)request.getSession().getAttribute("user_num");
		String user_name = chattingDao.getInstacnDao().findname(user_num);
		System.out.println("내 이름"+user_name);
		// 등록하고자 하는 날짜
		String t_date = request.getParameter("t_date");
		
		System.out.println("cal 서블릿 t_date ::: "+t_date);
		// 등록하고자 하는 내용
		String t_condent = request.getParameter("t_content");
		String tt_content = "-"+t_condent;
		System.out.println("cal 서블릿 t_content:::"+t_condent);
		
		
		boolean result = boardDao.getInstance().caladd(user_name , t_date , tt_content);
		
		response.getWriter().print(result);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
