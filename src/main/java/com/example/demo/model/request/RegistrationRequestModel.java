package com.example.demo.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public @Data class RegistrationRequestModel {
	
	private String userName;
	private String email;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
    
}
