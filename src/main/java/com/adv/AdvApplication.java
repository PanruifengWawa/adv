package com.adv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.adv.websocket.TerminalServer;

@SpringBootApplication
public class AdvApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(AdvApplication.class, args);
		TerminalServer.setApplicationContext(applicationContext);
	}
}
