package controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.json.simple.JSONObject;

@ServerEndpoint("/chatting/{mid}")
public class chatting{
	//클라이언트소켓 명단  
	public static Hashtable<Session , String> clients = new Hashtable<>();
	//알람보내기   
	public void sendAlarm(String content) throws IOException {
		JSONObject object = new JSONObject();
		object.put("type", "alarm");
		object.put("content", content);
		
		for(Session s : clients.keySet()) {
			s.getBasicRemote().sendText(object.toString());
		}
	}
	@OnOpen //서버웹소켓 들어옴
	public void onOpen(Session session , @PathParam("mid") String mid) throws IOException {
		clients.put(session,mid);//접속된 세션 아이디 저장
		//ArrayList<memberDto> list = memberDao.getInstance().getinfolist();
		//for(memberDto a : list) {
		//	String A = a.getMid();
		//	sendAlarm(A); 
		//}
		sendAlarm(mid+"님이 들어왔습니다.");
	}
	
	@OnClose //서버웹소켓 나감 -- 닫기요청시 , 서버재부팅시 , 새로고침시
	public void onClose(Session session) throws IOException {
		String mid=clients.get(session);
		clients.remove(session); 
		sendAlarm(mid+"님이 나갔습니다.");		
	}
	
	@OnMessage //메시지 들어옴  
	public void onMessage(Session session , String msg) throws IOException {
		for(Session s: clients.keySet()) {
			s.getBasicRemote().sendText(msg);
		}
	}
}
