package com.adv.websocket;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;


@ServerEndpoint(value = "/admin")
@Component
public class AdminServer  {
   
	private static CopyOnWriteArrayList<AdminServer> admins = new CopyOnWriteArrayList<>();
  
	
	private Session session;
 
  
 

	@OnOpen
	public void onOpen(Session session){
  	  	
  	  	this.session = session;
  	  	admins.add(this);
  	
	}
   
	@OnClose
	public void onClose() {
		admins.remove(this);
		
	}
   
	@OnMessage
	public void onMessage(String message, Session session) {
		
	}
  
  


	@OnError
	public void onError(Session session, Throwable error){
		System.out.println("Conntion Error!");
		error.printStackTrace();
	}
   
	
	
	public static void printServer() {
		System.out.println(admins);
	}

	public void sendMessage(String message) {
		try {
			this.session.getBasicRemote().sendText(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
     
	}
  
  
 

  
}
