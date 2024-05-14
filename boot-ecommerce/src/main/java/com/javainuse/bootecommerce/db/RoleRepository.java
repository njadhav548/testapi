package com.javainuse.bootecommerce.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javainuse.bootecommerce.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
