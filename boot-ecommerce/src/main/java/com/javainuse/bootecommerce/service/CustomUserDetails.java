package com.javainuse.bootecommerce.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.javainuse.bootecommerce.model.User;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails{

	private User user;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println(user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
				.collect(Collectors.toList()));
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setUser(User user2) {
		this.user = user2;
	}

}
