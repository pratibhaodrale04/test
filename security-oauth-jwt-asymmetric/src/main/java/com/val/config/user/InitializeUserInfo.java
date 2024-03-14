package com.val.config.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.val.entity.UserInfo;
import com.val.repo.IUserInfoRepo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InitializeUserInfo implements CommandLineRunner{
	@Autowired
	IUserInfoRepo userInfoRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		log.info("Loading user data from CommandLineRunner");
		
		userInfoRepo.saveAll(List
				.of(UserInfo.builder()
				.username("Admin")
				.email("admin@gmail.com")
				.roles("ROLE_ADMIN")
				.password(passwordEncoder.encode("admin"))
				.build(),
				UserInfo.builder()
				.username("Manager")
				.email("manager@gmail.com")
				.roles("ROLE_MANAGER")
				.password(passwordEncoder.encode("manager"))
				.build(),
				UserInfo.builder()
				.username("User")
				.email("user@gmail.com")
				.roles("ROLE_USER")
				.password(passwordEncoder.encode("user"))
				.build()));
	}
}
