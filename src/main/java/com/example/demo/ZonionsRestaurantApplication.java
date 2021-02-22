package com.example.demo;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class ZonionsRestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZonionsRestaurantApplication.class, args);
	}
//	@Bean
//	public BCryptPasswordEncoder bCryptpasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
	@Bean 
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	
	


}
