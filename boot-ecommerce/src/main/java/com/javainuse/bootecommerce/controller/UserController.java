package com.javainuse.bootecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.bootecommerce.db.UserRepository;
import com.javainuse.bootecommerce.model.AuthenticationRequest;
import com.javainuse.bootecommerce.model.AuthenticationResponse;
import com.javainuse.bootecommerce.model.User;
import com.javainuse.bootecommerce.service.CustomUserDetailsService;
import com.javainuse.bootecommerce.service.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@GetMapping("/get")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/add")
	public void createUser(@RequestBody User user) {
		String pwd = user.getPassword();
		System.out.println(pwd);
		String encryptedPwd = passwordEncoder.encode(pwd);
		user.setPassword(encryptedPwd);
		
		userRepository.save(user);
	}
	
	@DeleteMapping(path = { "/{id}" })
	public Optional<User> deleteUser(@PathVariable("id") long id) {
		Optional<User> user = userRepository.findById(id);
		userRepository.deleteById(id);
		return user;
	}
	
	//For jwt
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		System.out.println("Authenticated");
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		System.out.println(userDetails);

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}