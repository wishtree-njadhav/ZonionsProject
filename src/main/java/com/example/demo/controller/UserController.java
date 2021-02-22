package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RegistrationEntity;
import com.example.demo.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserDetailsServiceImpl userService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<RegistrationEntity> allAccess() {
		
		return userService.getAllUsers();
	}
	
	@GetMapping("/users")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}


}
