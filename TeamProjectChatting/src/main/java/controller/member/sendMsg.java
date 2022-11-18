package controller.member;

import java.io.IOException;
import java.util.Hashtable;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/sendMsg")
public class sendMsg{
	public void send(Hashtable<Session , Integer> room, Session session , String msg) throws IOException {
	   //채팅에서 채팅방번호 추출하기
	   String str = msg.split(",")[0].split(":")[1];
	   //채팅방번호
	   String msgtype = "";
	   //"1"이면 ""빼고 추출하기
	   for(int i=1; i<str.length()-1; i++) {
		   msgtype+=str.charAt(i);
	   }
	  //들어온 모든세션들 중에 
      for(Session s: room.keySet()) {
    	  //갖고있는 채팅방번호가 채팅에서 추출한 채팅방번호와 같으면
    	  if(room.get(s) == Integer.parseInt(msgtype)) {
    		  s.getBasicRemote().sendText(msg);
    	  }   
      }
   }
}
