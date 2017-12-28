package com.adv.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.adv.service.TerminalService;


@ServerEndpoint(value = "/ws")
@Component//部署的时候去掉
public class WSServer  {
   
  private static Map<Long, CopyOnWriteArraySet<WSServer>> shops = new ConcurrentHashMap<Long, CopyOnWriteArraySet<WSServer>>();
  
  private Session session;
  
  private Long userId;
  
  private static ApplicationContext applicationContext;
  
 
  private static TerminalService terminalService;
 
  
  
  public static void setApplicationContext(ApplicationContext applicationContext) {
	  WSServer.applicationContext = applicationContext;
  }

  @OnOpen
  public void onOpen(Session session){
//  	String mac = null;
	  terminalService = applicationContext.getBean(TerminalService.class);
  	  String mac = session.getRequestParameterMap().get("mac").get(0);
   	  terminalService.save(mac, "a");
  	
  }
   
  @OnClose
  public void onClose() {
	  System.out.println("closed");

  }
   
  @OnMessage
  public void onMessage(String message, Session session) {
//  	String mac = null;
//  	Long playAdvId = null;
//  	Integer num = null;
//  	try {
//			org.json.JSONObject messageJson = new org.json.JSONObject(message);
//			mac = messageJson.getString("mac");
//			playAdvId = messageJson.getLong("playAdvId");
//			num = messageJson.getInt("num");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//  	
//  	if (mac == null || playAdvId == null || num == null) {
//			return;
//		}
//  	AdvOrder advOrder = new AdvOrder();
//  	advOrder.setPlayAdvId(playAdvId);
//  	advOrder.setNum(num);;
//  	
//  	String dataWrapper = advOrderService.addWithWs(mac, advOrder);
//  	
//		if (dataWrapper == null) {
//			return;
//		}
//		this.sendMessage(dataWrapper);
//		if (dataWrapper.contains("成功") && advOrder.getUserId() != null) {
//			CopyOnWriteArraySet<WSServer> shop = shops.get(advOrder.getUserId());
//			if (shop != null) {
//				for(WSServer wsServer : shop) {
//					wsServer.sendMessage(dataWrapper);
//				}
//				
//			}
//		}
  }
  
  
  public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@OnError
  public void onError(Session session, Throwable error){
      System.out.println("Conntion Error!");
      error.printStackTrace();
  }
   

  public void sendMessage(String message) {
  	try {
  		 this.session.getBasicRemote().sendText(message);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
     
      //this.session.getAsyncRemote().sendText(message);
  }
  
  
  public static void newOrder(Long userId, String message) {
  	CopyOnWriteArraySet<WSServer> shop = shops.get(userId);
		if (shop != null) {
			for(WSServer wsServer : shop) {
				wsServer.sendMessage(message);
			}
			
		}
  }
  
  public static void verifyOrderState(Long userId, String message) {
  	CopyOnWriteArraySet<WSServer> shop = shops.get(userId);
		if (shop != null) {
			for(WSServer wsServer : shop) {
				wsServer.sendMessage(message);
			}
			
		}
  }

  
}
