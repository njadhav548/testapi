package com.javainuse.bootecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue
	private Long role_Id;
	private String role;
	
}
