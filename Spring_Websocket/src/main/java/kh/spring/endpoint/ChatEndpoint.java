package kh.spring.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;

import kh.spring.configurator.WSConfig;

@ServerEndpoint(value = "/chat" , configurator = WSConfig.class) // default Configurtor를 쓰는게 아닌 Modify Configurator 사용
public class ChatEndpoint {
	// 일반적으로는 인스턴스가 새로 들어오는 요청에 따라 만들어 지기 때문에 Autowired를 주입할 수 없다.
	@Autowired
	private HttpSession session; 
	
	// 멤버필드에 보관하기
	private static List<Session> clients = Collections.synchronizedList(new ArrayList<>()); //concrete modification exception 을 방지하기 위하여
	
	// 클라이언트 웹소켓 통신이 시작되었을 때, 제일 먼저 들어온다.
	@OnOpen
	public void onConnect(Session session, EndpointConfig config) { // 연결이 발생 했을 때
		System.out.println("클라이언트 연결 확인");
		clients.add(session);
		this.session = (HttpSession) config.getUserProperties().get("hSession");
	}
	
	// 클라이언트로 부터 메세지가 도착했을 경우
	@OnMessage 
	public void onMessage(String message){ 
		
		String userId = (String) this.session.getAttribute("loginID");
		
		System.out.println("클라이언트가 보낸 메세지 : " + message);
		// 메세지를 같은 웹소켓 엔드포인트에 접속한 클라이언트 들에게 배포해야한다.
		System.out.println("메세지 도착");
		// A가 보낸걸 받아서 B한테 보내라
		
		synchronized (clients) { //concrete modification exception 을 방지하기 위하여
			
		for(Session client : clients) {
	
			try { // throws Exception을 쓸 수도 있지만 오류를 넘길 수 있으므로 try~catch문으로
				client.getBasicRemote().sendText(userId + " : " + message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("클라이언트와의 접속이 끊어졌습니다.");
		
	}
	
	
}
