package com.example.demo.interfaceService;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.shared.dto.RegistrationDto;

public interface RegistrationServiceInterface extends UserDetailsService{
	
	RegistrationDto createUser(RegistrationDto user);
	
	RegistrationDto getByUserId(String userId);
	RegistrationDto getByEmail(String email);
	RegistrationDto updateUser(String userId,RegistrationDto user);
	void deleteByUserId(String userId);
	List<RegistrationDto> getUserList(int page,int limit);


}
