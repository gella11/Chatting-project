package controller.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/filedown")
public class filedown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public filedown() { super();    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 업로드된 파일을 다운로드
			// 1. 다운로드 할 파일명 요청
			request.setCharacterEncoding("UTF-8");			// 한글 인코딩
			String b_file = request.getParameter("b_file");	// 다운로드할 파일명 요청
			
			// 2. 경로 + 파일명으로 해당 파일 위치 찾기
			// String uploadpath = "C:\\Users\\504\\git\\ezen_web_2022_webapp\\jspweb1\\src\\main\\webapp\\upload\\"+bfile;
			String uploadpath = request.getSession().getServletContext().getRealPath("/upload/"+b_file); //최상의경로
			// 3. 해당 경로의 파일을 객체화 [ java에서 제공되는 file 클래스 이용 ]
			// 필요한 파일을 객체화 하였기 때문에  [ 해당 파일을 조작/메소드 가능]
			File file = new File(uploadpath);
			// 4. HTTP에서 지원하는 다운로드 메소드 [ response ] 안써도 된대요. 뭐지..
			// Content-Disposition  				// 다운로드 형식. HTML에서 지원 [ 웹에서 다운로드 받을 때 밑에 뜨는거 ][ 브라우저 마다 차이가 있음 ]
			// attachment;filename=					// 파일 다운로드 시 , 다운로드 된 파일 명.
			// URLEncoder.encode(bfile,"UTF-8")		// URL 경로에 한글이 있을 경우 (데이터 ,"인코딩타입")
			// response.setHeader("Content-Disposition", "attachment;filename="+bfile );	// 한글 없으면  요런식.
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(b_file,"UTF-8") );
			
			// 5. 파일 전송 [ 외부와 데이터 통신 (스트림)을 이요하는데 방식은(전송은) 바이트로 ]
			// 5-1) [입력] bufferdInputStream  파일의 내용물을 모두 바이트로 읽어온다.
			//		입력스트림 객체 생성
			BufferedInputStream fin = new BufferedInputStream( new FileInputStream(file) );
			
			// 5-2) 파일의 바이트 길이만큼 배열 선언
			//		file.length() 해당 파일의 바이트길이 메소드
			byte[] bytes = new byte[ (int)file.length() ];
			// 5-3) 파일의 내용[바이트] 읽어오기
			// 		read는 바이트만 읽을 수 잇음.
			// 		scanner는 좀 더 다양함.
			//		읽어온 바이트를 바이트배열에 저장
			fin.read(bytes);
			// 5-4) [출력] http로 출력하기 위한 response.getOutputStream()
			BufferedOutputStream fout = new BufferedOutputStream( response.getOutputStream() );
			// 5-5) 배열에 존재하는 바이트 출력하기
			//		write 바이트 배열에 저장된 '바이트(만)'를 모두 내보내기
			fout.write(bytes);
			// 스트림은 버퍼 ( 전송시 사용되는 메모리 공간 )
			// 			보낼 때 허공에 빡 뿌리는게 아닌 어디에 임시로 저장해서 보내는 것이니. 다 되면 버퍼를 초기화 해주는 것
			// 버퍼 :  데이터를 한 곳에서 다른 한 곳으로 전송하는 동안 일시적으로 그 데이터를 보관하는 메모리의 영역
			fout.flush(); 	// 출력 스트림 버퍼 초기화
			fin.close();	// 입력 스트림 닫기
			fout.close();	// 출력 스트림 닫기 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
