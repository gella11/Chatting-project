package controller.member;

import java.io.IOException;
import java.util.Hashtable;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatting/{mid}")
public class chatting{
	//클라이언트소켓 명단  
	public static Hashtable<Session , String> clients = new Hashtable<>();

	@OnOpen //서버웹소켓 들어옴
	public void onOpen(Session session , @PathParam("mid") String mid) throws IOException {
		clients.put(session,mid);
	}
	
	@OnClose //서버웹소켓 나감 -- 닫기요청시 , 서버재부팅시 , 새로고침시
	public void onClose(Session session) throws IOException {
		String mid=clients.get(session);
		clients.remove(session);		
	}
	
	@OnMessage //메시지 들어옴  
	public void onMessage(Session session , String msg) throws IOException {
		for(Session s: clients.keySet()) {
			s.getBasicRemote().sendText(msg);
		}
	}
}
