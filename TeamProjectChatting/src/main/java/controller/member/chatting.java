package controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;

import model.Dao.chattingDao.chattingDao;
import model.Dto.memberDto.chattingDto;

@ServerEndpoint("/chatting/{str}")
public class chatting{
   //클라이언트소켓 명단  
   public static Hashtable<Session , String> clients = new Hashtable<>();
   
   @OnOpen //서버웹소켓 들어옴
   public void onOpen(Session session , @PathParam("str") String str) throws IOException, EncodeException {
      String mid = str.split(",")[0];
      int cno = Integer.parseInt(str.split(",")[1]);
      
      System.out.println("접속아이디 :"+mid);
      System.out.println("접속채팅방 :"+cno);
      clients.put(session,mid);

         // 11/3 도현 1개의 채팅방에 모든 채팅내역
      ArrayList<chattingDto> chatlist = chattingDao.getInstacnDao().getchat(cno);
      for(chattingDto dto : chatlist) {
         // 11/3 도현 채팅 하나씩 보내기.
         JSONObject object = new JSONObject();            
         object.put("type", dto.getType());
         object.put("mid", dto.getMid());
         object.put("content", dto.getContent());
         
         String msg = String.valueOf(object);
         
         session.getBasicRemote().sendText(msg);
      }      
   }
   
   @OnClose //서버웹소켓 나감 -- 닫기요청시 , 서버재부팅시 , 새로고침시
   public void onClose(Session session) throws IOException {
      clients.remove(session);      
   }
   
   @OnMessage //메시지 들어옴  
   public void onMessage(Session session , String msg) throws IOException {
      for(Session s: clients.keySet()) {
         s.getBasicRemote().sendText(msg);
      }
   }
}