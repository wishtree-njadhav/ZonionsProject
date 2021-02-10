package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.security.AppProperties;

@SpringBootApplication
public class ZonionsRestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZonionsRestaurantApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptpasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	
	@Bean(name="AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}


}
