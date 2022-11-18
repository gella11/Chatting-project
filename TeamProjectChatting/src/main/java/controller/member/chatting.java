package controller.member;

import java.io.IOException;
import java.util.Hashtable;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatting/{roomnumber}")
public class chatting{  
   //클라이언트소켓 명단  
   public static Hashtable<Session , Integer> room = new Hashtable<>();  
   @OnOpen //서버웹소켓 들어옴
   public void onOpen(Session session , @PathParam("roomnumber") int roomnumber) throws IOException, EncodeException {
      room.put(session,roomnumber);
      new listInChattingRoom().list(session, roomnumber);    
   }  
   
   @OnClose //서버웹소켓 나감 -- 닫기요청시 , 서버재부팅시 , 새로고침시
   public void onClose(Session session) throws IOException {
      room.remove(session);  
   }  
   
   @OnMessage //메시지 들어옴  
   public void onMessage(Session session , String msg) throws IOException {
	   //채팅에서 채팅방번호 추출하기   
	  new sendMsg().send(room,session,msg);
   }
}