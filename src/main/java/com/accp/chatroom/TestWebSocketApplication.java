package com.accp.chatroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.accp.chatroom.ws.MySocketHandler;

@SpringBootApplication
public class TestWebSocketApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TestWebSocketApplication.class, args);
		SpringApplication springApplication = new SpringApplication(TestWebSocketApplication.class);        
		ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);        
		MySocketHandler.ac=configurableApplicationContext;//非常重要
	}
}