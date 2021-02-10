package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.interfaceService.RegistrationServiceInterface;
import com.example.demo.model.request.RegistrationRequestModel;
import com.example.demo.model.response.OperationStatusModel;
import com.example.demo.model.response.RegistrationResponse;
import com.example.demo.model.response.RequestOperationName;
import com.example.demo.model.response.RequestOperationStatus;
import com.example.demo.shared.dto.RegistrationDto;


@RestController
@RequestMapping("users")
public class RegistrationController {
	
	@Autowired
	RegistrationServiceInterface registrationService;
	
	@PostMapping
	public RegistrationResponse createUser(@RequestBody RegistrationRequestModel user) {
		RegistrationResponse userDetails=new RegistrationResponse();
		
		RegistrationDto userDto=new RegistrationDto();
		BeanUtils.copyProperties(user, userDto);
		
		RegistrationDto createuser=registrationService.createUser(userDto);
		BeanUtils.copyProperties(createuser, userDetails);
		
		return userDetails;
	}
	
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public RegistrationResponse getUser(@PathVariable String id) {
		RegistrationResponse returnvalue = new RegistrationResponse();
		RegistrationDto dto = registrationService.getByUserId(id);
		BeanUtils.copyProperties(dto, returnvalue);
		return returnvalue;
	}
	
	@PutMapping(path = "/{id}")
	public RegistrationResponse updateUser(@PathVariable String id, @RequestBody RegistrationRequestModel userDetails) {
		RegistrationResponse userDetail = new RegistrationResponse();
		RegistrationDto userdto = new RegistrationDto();
		BeanUtils.copyProperties(userDetails, userdto);
		RegistrationDto createdUser = registrationService.updateUser(id, userdto);
		BeanUtils.copyProperties(createdUser, userDetail);
		return userDetail;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		registrationService.deleteByUserId(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<RegistrationResponse> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "5") int limit) {
		List<RegistrationResponse> returnValue = new ArrayList<>();
		List<RegistrationDto> userdto = registrationService.getUserList(page, limit);
		System.out.println(userdto);
		for (RegistrationDto users : userdto) {
			
			RegistrationResponse userModel = new RegistrationResponse();
			BeanUtils.copyProperties(users, userModel);
			returnValue.add(userModel);
		}
		return returnValue;
	}

}
