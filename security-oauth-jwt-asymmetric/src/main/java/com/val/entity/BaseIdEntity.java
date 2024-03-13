package com.val.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseIdEntity {
	
	@Id
	@GeneratedValue
	private Long id;

}
