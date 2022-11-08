package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.Dao.boardDao.boardDao;
import model.Dao.chattingDao.chattingDao;

@WebServlet("/write")
public class write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public write() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 저장경로 [ 배포된 프로젝트의(서버) 폴더 저장 ]
		String uploadpath = request.getSession().getServletContext().getRealPath("/upload"); //최상의경로
		// 2. muti 객체 생성
		   MultipartRequest multi = new MultipartRequest(
				   request ,					 // 요청방식
				   uploadpath ,
				   1024 * 1024 * 10, 			 // [1024 : 1kb] [1024*1024 : 1mb] [1024*1024*1024 : 1G]
				   "UTF-8",					     // 인코딩
				   new DefaultFileRenamePolicy() // 업로드 된 파일의 이름이 중복일 경우 자동으로 이름 지정
				   );
		
		
		int user_num = (Integer)request.getSession().getAttribute("user_num");
			String user_name = chattingDao.getInstacnDao().findname(user_num);
		int c_no = Integer.parseInt(request.getParameter("c_no"));
		String b_title 	= multi.getParameter("b_title");
		String b_content  = multi.getParameter("b_content");
		String b_file = multi.getFilesystemName("b_file");
		
		boolean result = boardDao.getInstance().write(b_title,b_content,b_file,c_no,user_name);
		
		
		request.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
		
		
	}

}
