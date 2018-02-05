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

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.adv.models.Terminal;
import com.adv.parameters.Parameters;
import com.adv.service.TerminalService;


@ServerEndpoint(value = "/terminal")
@Component
public class TerminalServer  {
   
	private static Map<String, TerminalServer> terminals = new HashMap<>();
  
	private static ApplicationContext applicationContext;
  
 
	private static TerminalService terminalService;
	
	private Session session;
	private String mac;
	
	private Integer page;
	
 
  

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

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
		this.handleMessage(message);
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
		case 1:
			try {
				this.page = json.getJSONObject("data").getInt("page");
				AdminServer.sendMessageToAll(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 7:
			if (Parameters.canAnswer == 1) {
				if (Parameters.answer.get(mac) == null) {
					Parameters.answer.put(mac, System.currentTimeMillis());
				}
				
			}
			break;
		default:
			break;
		}
		
		
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
					this.page = 0;
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

	public static void getTerminalState(Terminal terminal) {
		TerminalServer terminalServer = terminals.get(terminal.getMac());
		if (terminalServer != null) {
			terminal.setState(1);
			terminal.setPage(terminalServer.getPage());
		} else {
			terminal.setState(0);
			terminal.setPage(0);
		}
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
	
	public static void sendMessageToOne(String mac, String message) {
		TerminalServer terminalServer = terminals.get(mac);
		if (terminalServer != null) {
			terminalServer.sendMessage(message);
		}
	}
	
	public static boolean closeByMac(String mac) {
		TerminalServer terminalServer = terminals.get(mac);
		if (terminalServer == null) {
			return false;
		}
		try {
			terminalServer.session.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void sendMessageToAll(String message) {
		for(TerminalServer terminalServer: terminals.values()) {
			terminalServer.sendMessage(message);
		}
		
	}
  
  
 

  
}
