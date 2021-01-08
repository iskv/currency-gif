package com.example.WebApp0;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WebApp0Application {

	public static void main(String[] args) {
		SpringApplication.run(WebApp0Application.class, args);
	}

}
