package board;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.Dao.boardDao.boardDao;
import model.Dao.chattingDao.chattingDao;
import model.Dto.memberDto.BoardDto;
import model.dao.BoardDao;

@WebServlet("/write")
public class write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public write() {super();  }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int b_no = Integer.parseInt( request.getParameter("b_no") );
		int type = Integer.parseInt(request.getParameter("type"));
		int user_num = (Integer)request.getSession().getAttribute("user_num");
		
		// [ type : 2 ] 해당 글 삭제
		// [ type : 4 ] 해당 글 상세보기
		if(type == 2) {
			// 글 삭제
			BoardDto dto = boardDao.getInstacnDao().getboard(b_no);// 삭제할 게시물 정보 호출 [ 삭제 전에 해야하는 당연함 주의 ]
			// DAO 처리 [ db내 데이터 삭제 ]
			boolean result = boardDao.getInstacnDao().delete(b_no);
			if(result) {
				String deletepath = request.getSession().getServletContext().getRealPath("/upload/"+dto.getB_file());
				File file = new File(deletepath);
				// file 클래스
				// 자바 외부에 존재하는 파일 조작/제어 메소드 제공하는 클래스
				// 객체명.length() 해당 파일의 바이트 길이
				// 객체명.delete() 해당 파일의 삭제
				// 객체명.exists() 해당 파일이 존재하면 true/ false
				if(file.exists() )
				file.delete(); // 해당 경로에 존재하는 파일을 삭제
			}
			response.getWriter().print(result);
		}
		else if (type == 2){
			// 유저 번호 확인용
			String my_name = chattingDao.getInstacnDao().findname("user_num");
			// 2. DAO 처리 
			BoardDto dto = boardDao.getInstacnDao().getboard(b_no);

			// 3. DTO --> JSON 형변환
			JSONObject object = new JSONObject();
			object.put("b_no", dto.getB_no());
			object.put("b_title", dto.getB_title());
			object.put("b_content", dto.getB_content());
			object.put("b_file", dto.getB_file() );
			object.put("user_name", dto.getUser_name());

				// 삭제 , 수정 버튼 활성화를 위한 식별 변수 선언
				if(my_name.equals(dto.getUser_name())) {
					object.put("btnaction", true);
				}
			// 4. 응답 
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print( object );
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int b_no = Integer.parseInt( request.getParameter("b_no") );
		int type = Integer.parseInt(request.getParameter("type"));
		int my_num = (Integer)request.getSession().getAttribute("user_num");

		// [ type : 1 ] 해당 글 등록
		// [ type : 3 ] 해당 글 삭제
		if(type == 1) {
			// 1. 첨부파일 [cos.jar 라이브러리 필요]
			// cos.jar로 MultipartRequest 쓸 수 있음.
			// HttpServletRequest : 첨부파일x 소량의 문자만 지원
			// 첨부파일이 있다면,, 첨부파일을 주소에 노출시키면 안되니 post 형식으로만 가능하다.
			
			// 저장경로 [ 프로젝트 ] [ 개발중인 프로텍트 폴더에 저장 ]
			//String uploadpath = "C:\\Users\\504\\git\\ezen_web_2022_webapp\\jspweb1\\src\\main\\webapp\\upload";
			// 1. 저장경로 [ 배포된 프로젝트의(서버) 폴더 저장 ]
			//  
			String uploadpath = request.getSession().getServletContext().getRealPath("/upload"); //최상의경로
			//System.out.println(uploadpath);
			
			// 2. muti 객체 생성
			// MultipartRequest multi = new MultipartRequest( 1.요청방식 , 2.파일저장경로 , 3.최대용량범위 , 4.인코딩타입 , 5.기타 보안(필수x) );
			   MultipartRequest multi = new MultipartRequest(
					   request ,					 // 요청방식
					   uploadpath ,
					   1024 * 1024 * 10, 			 // [1024 : 1kb] [1024*1024 : 1mb] [1024*1024*1024 : 1G]
					   "UTF-8",					     // 인코딩
					   new DefaultFileRenamePolicy() // 업로드 된 파일의 이름이 중복일 경우 자동으로 이름 지정
					   );
			// 3. g해당 저장경로에 첨부파일 업로드가 된다.
			   
			// 4. 나머지 데이터 직접 요청
			   String b_title 	= multi.getParameter("btitle");
			   String b_content  = multi.getParameter("bcontent");
			   String mid = (String)request.getSession().getAttribute("mid");		
			   int myname = chattingDao.getInstacnDao().findname("mid");
			   
			// * 어떤 파일을 업로드 했는지 db에 저장할 첨부파일된 경로/이름 호출
	        // 이름 호출
			// getParameter 아님!!!!!
			   String bfile = multi.getFilesystemName("b_file");
			   System.out.println(bfile);
			   
			   boolean result = boardDao.getInstacnDao().write(b_title, b_content, myname, b_file);
			   	
			   response.getWriter().print(result);
		}
		else if( type == 3) {
			String uploadpath = request.getSession().getServletContext().getRealPath("/upload/");
			MultipartRequest multi = new MultipartRequest(
					   request ,					 // 요청방식
					   uploadpath ,
					   1024 * 1024 * 10, 			 // [1024 : 1kb] [1024*1024 : 1mb] [1024*1024*1024 : 1G]
					   "UTF-8",	
					   new DefaultFileRenamePolicy() // 업로드 된 파일의 이름이 중복일 경우 자동으로 이름 지정
					   );
			// 요청
			String b_title = multi.getParameter("b_title");			// 수정할 제목
			String b_content = multi.getParameter("b_content");
			String b_file = multi.getFilesystemName("b_file");
			
			// 1) 수정시 새로운 첨부파일 등록 있을 경우[ 기존첨부파일 삭제 ]
			// 수정 전 게시물 정보 
			BoardDto dto = boardDao.getInstacnDao().getboard(b_no);
			// 기존 첨부파일 변경 여부 판단
			boolean bfilechange = true;
			if(b_file == null) { // 2) 수정시 첨부파일 등록 없을 경우 [ 기존 첨부파일 호출 ]
				b_file =  dto.getB_file();
				bfilechange = false;
			}
			// dao 처리
			boolean result = boardDao.getInstacnDao().bupdate(b_no, b_title, b_content , b_file);
			// *. 수정시 첨부파일 등록 없을경우 [ 기존첨부파일 호출  ]
			if( b_file == null ) {  b_file = dto.getB_file(); bfilechange =false; }
			
			if(result) {	// 업데이트 성공시 [ 기존첨부파일 변경되었을 때 ] 기존파일 삭제
				if(bfilechange) {
					String deletepath = request.getSession().getServletContext().getRealPath("/upload/"+dto.getB_file());
					File file = new File(deletepath);
					if(file.exists()) {
						file.delete();
					}
				} 
			}
			// 결과 반환
			response.getWriter().print(result);
		}
			
	}

}
