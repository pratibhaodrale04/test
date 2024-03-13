package com.val.config.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.val.repo.IUserInfoRepo;

@Service
public class UserInfoManagerConfig implements UserDetailsService {

	@Autowired
	IUserInfoRepo userInfoRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userInfoRepo.findByEmail(username).map(UserInfoConfig::new)
				.orElseThrow(() -> new UsernameNotFoundException("User Email-->" + username + "does not exist"));
	}

}
