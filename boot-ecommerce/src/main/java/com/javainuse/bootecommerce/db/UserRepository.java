package com.javainuse.bootecommerce.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javainuse.bootecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByName(String name);
}
