package com.adv.websocket;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.adv.parameters.Parameters;


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
		this.handleMessage(message);
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
	
	public static void sendMessageToAll(String message) {
		for(AdminServer admin: admins) {
			admin.sendMessage(message);
		}
	}
  
  
 
	public void handleMessage(String message) {
		System.out.println(message);
		Integer code = 0;
		JSONObject json = null;
		try {
			json = new JSONObject(message);
			code = json.getInt("code");
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		switch (code) {
		case 2:
			try {
				JSONArray macs = json.getJSONObject("data").getJSONArray("macs");
				for(int i = 0; i < macs.length(); i ++) {
					String mac = macs.getString(i);
					TerminalServer.sendMessageToOne(mac, message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 5:
			Parameters.canAnswer = 1;
			Parameters.answer.clear();
			TerminalServer.sendMessageToAll(message);
			AdminServer.sendMessageToAll(message);
			break;
			
		case 6:
			Parameters.canAnswer = 0;
			TerminalServer.sendMessageToAll(message);
			AdminServer.sendMessageToAll(message);
			break;
		default:
			break;
		}
	}

  
}
