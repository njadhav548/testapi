package com.javainuse.bootecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javainuse.bootecommerce.db.UserRepository;
import com.javainuse.bootecommerce.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
	
		User user = repository.findByName(name);
		CustomUserDetails userDetails= null;
		if(user != null)
		{
			userDetails = new CustomUserDetails();
			userDetails.setUser(user);
		
		}else {
			throw new UsernameNotFoundException("User not exist with name : "+name);
		}
		return userDetails;
		
	}
}
