package controller.member;

import java.io.IOException;
import java.util.ArrayList;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;

import model.Dao.chattingDao.chattingDao;
import model.Dto.memberDto.chattingDto;

@ServerEndpoint("/listInChattingRoom")
public class listInChattingRoom{ 
   public void list(Session session , int roomnumber) throws IOException, EncodeException {
      // 11/3 도현 1개의 채팅방에 모든 채팅내역
      ArrayList<chattingDto> chatlist = chattingDao.getInstacnDao().getchat(roomnumber);
      for(chattingDto dto : chatlist) {
         // 11/3 도현 채팅 하나씩 보내기.
         JSONObject object = new JSONObject();
         object.put("type", dto.getType());
         object.put("name", dto.getName());
         object.put("mid", dto.getMid());
         object.put("content", dto.getContent());
         object.put("date", dto.getDate());
         object.put("img", dto.getImg());
         String msg = String.valueOf(object);
         //채팅방번호에 맞는 모든 채팅을 소켓을 연 세션에게 전송
         session.getBasicRemote().sendText(msg);
      }      
   }  
}
