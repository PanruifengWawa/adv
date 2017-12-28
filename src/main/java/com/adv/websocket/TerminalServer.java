package com.adv.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.adv.service.TerminalService;


@ServerEndpoint(value = "/terminal")
@Component
public class TerminalServer  {
   
	private static Map<String, TerminalServer> terminals = new HashMap<>();
  
	private static ApplicationContext applicationContext;
  
 
	private static TerminalService terminalService;
	
	private Session session;
	private String mac;
 
  
  
	public static void setApplicationContext(ApplicationContext applicationContext) {
		TerminalServer.applicationContext = applicationContext;
	}

	@OnOpen
	public void onOpen(Session session){
  	  	
  	  	saveTerminal(session);
  	
	}
   
	@OnClose
	public void onClose() {
		System.out.println("close terminal : " + mac);
		if (this.mac != null) {
			terminals.remove(this.mac);
		}
		
	}
   
	@OnMessage
	public void onMessage(String message, Session session) {
		
	}
  
  


	@OnError
	public void onError(Session session, Throwable error){
		System.out.println("Conntion Error!");
		error.printStackTrace();
	}
   
	public static void removeTerminal(String mac) {
		TerminalServer terminal = terminals.get(mac);
		if (terminal != null) {
			try {
				terminal.session.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		printServer();
		
	}
	
	
	private void saveTerminal(Session session) {
		
		terminalService = applicationContext.getBean(TerminalService.class);
		String mac = null;
		if (session.getRequestParameterMap().get("mac") != null && session.getRequestParameterMap().get("mac").get(0) != null) {
			 mac = session.getRequestParameterMap().get("mac").get(0);
		}
		boolean flag = false;
		if (mac == null) { //没有mac，被认为是非法连接，将被关闭
			flag = true;
		} else {
			TerminalServer ifExisted = terminals.get(mac);
			if (ifExisted != null) { //如果某个mac地址已经被使用了，那么会被认为是非法连接，将被关闭
				flag = true;
			} else {
				if (terminalService.save(mac, "")) { //保存该终端信息
					this.mac = mac;
					this.session = session;
					terminals.put(mac, this); //保存连接
				} else {//保存终端信息失败，关闭终端
					flag = true;
				}
			}
		}
		
		if (flag) { //关闭操作
			try {
				session.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		printServer();
		
	}
	
	public static void printServer() {
		System.out.println(terminals);
	}

//	public void sendMessage(String message) {
//		try {
//			this.session.getBasicRemote().sendText(message);
//			} catch (Exception e) {
//			// TODO: handle exception
//				e.printStackTrace();
//			}
//     
//      //this.session.getAsyncRemote().sendText(message);
//	}
  
  
 

  
}
