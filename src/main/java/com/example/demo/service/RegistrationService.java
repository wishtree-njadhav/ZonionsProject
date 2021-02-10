package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import com.example.demo.entity.RegistrationEntity;
import com.example.demo.interfaceService.RegistrationServiceInterface;
import com.example.demo.repository.RegistrationRepository;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.RegistrationDto;

@Service
public class RegistrationService implements RegistrationServiceInterface{

	@Autowired
	RegistrationRepository registrationRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public RegistrationDto createUser(RegistrationDto user) {
		
		System.out.println("email=="+user.getEmail());
		if(registrationRepository.findUserByEmail(user.getEmail())!=null)
			throw new RuntimeException("Record already exits");
		
		RegistrationEntity userEntity=new RegistrationEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		String publicUserId =utils.generateUserId(20);
		userEntity.setUserId(publicUserId);
		RegistrationEntity storedValue=registrationRepository.save(userEntity);
		
		RegistrationDto returnValue=new RegistrationDto();
		BeanUtils.copyProperties(storedValue, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		RegistrationEntity userEntity = registrationRepository.findUserByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		//return new UserPrincipal(userEntity);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	
	}

	@Override
	public RegistrationDto getByUserId(String userId) {
		RegistrationDto userDto =new RegistrationDto();
		RegistrationEntity userEntity=registrationRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException("user with id " + userId + " not found");
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public RegistrationDto getByEmail(String email) {
		RegistrationEntity userEntity=registrationRepository.findUserByEmail(email);
		if(userEntity==null) throw new UsernameNotFoundException(email);
		RegistrationDto userDto=new RegistrationDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public RegistrationDto updateUser(String userId, RegistrationDto user) {
		RegistrationDto returnValue=new RegistrationDto();
		RegistrationEntity userEntity=registrationRepository.findByUserId(userId);
		if(userEntity==null) throw new UsernameNotFoundException(userId);
		userEntity.setUserName(user.getUserName());
		userEntity.setEmail(user.getEmail());
		RegistrationEntity updatedDetails=registrationRepository.save(userEntity);
		BeanUtils.copyProperties(updatedDetails, returnValue);
		return returnValue;
	}

	@Override
	public void deleteByUserId(String userId) {
		RegistrationEntity userEntity=registrationRepository.findByUserId(userId);
        if(userEntity==null) throw new UsernameNotFoundException(userId);
        registrationRepository.delete(userEntity);
	
		
	}

	@Override
	public List<RegistrationDto> getUserList(int page, int limit) {
		List<RegistrationDto> userList=new ArrayList<RegistrationDto>();
		Pageable pageablerequest = PageRequest.of(page, limit);
		Page<RegistrationEntity> userPage = registrationRepository.findAll(pageablerequest);
		List<RegistrationEntity> users = userPage.getContent();
        
		for (RegistrationEntity userEntity : users) {
			System.out.println("users in service for loop=="+userEntity);
			RegistrationDto userDto = new RegistrationDto();
			BeanUtils.copyProperties(userEntity, userDto);
			userList.add(userDto);
		}
		return userList;
	}
	
	

}
