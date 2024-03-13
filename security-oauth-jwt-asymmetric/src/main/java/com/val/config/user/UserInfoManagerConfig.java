package com.val.config.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.val.repo.IUserInfoRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInfoManagerConfig implements UserDetailsService {

	@Autowired
	IUserInfoRepo userInfoRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Loading user from DB");
		return userInfoRepo.findByUsername(username).map(UserInfoConfig::new)
				.orElseThrow(() -> new UsernameNotFoundException("User Name-->" + username + "does not exist"));
	}

}
