package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entity.RegistrationEntity;
import com.example.demo.entity.Role;
import com.example.demo.repository.RegistrationRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	RegistrationRepository registrationRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RegistrationEntity user = registrationRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		//return UserDetailsImpl.build(user);
		List<GrantedAuthority> authorities = getGrantedAuthorities(user.getRoles());
		//return new User(userEntity.getEmail(), userEntity.getEncryptedpassword(), new ArrayList<>());
		return new User(user.getUsername(),user.getPassword(),authorities);
	}
	
	public List<RegistrationEntity> getAllUsers(){
		return registrationRepository.findAll();
	}
	
	public RegistrationEntity getById(long id) {
		return null;
	}

	private List<GrantedAuthority> getGrantedAuthorities(Set<Role> roles){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for(Role rolesname:roles) {
        	authorities.add(new SimpleGrantedAuthority("ROLE_" + rolesname.getName()));	
		}
		
        return authorities;
    }

}
