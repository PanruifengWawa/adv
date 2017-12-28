package com.adv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.adv.websocket.TerminalServer;
import com.adv.websocket.WSServer;

@SpringBootApplication
public class AdvApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(AdvApplication.class, args);
		WSServer.setApplicationContext(applicationContext);
		TerminalServer.setApplicationContext(applicationContext);
	}
}
