package com.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableAuthorizationServer
public class SpringCloudOAuthServerUdemyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudOAuthServerUdemyApplication.class, args);
	}

}
