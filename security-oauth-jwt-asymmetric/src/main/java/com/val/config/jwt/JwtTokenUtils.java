package com.val.config.jwt;

import java.time.Instant;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.val.config.user.UserInfoConfig;
import com.val.repo.IUserInfoRepo;

@Component
public class JwtTokenUtils {
	
	@Autowired
	IUserInfoRepo userInfoRepo;

	public String getUserName(Jwt decodedToken) {
		return decodedToken.getSubject();
	}

	public UserDetails getUserDetails(String username) {
		return userInfoRepo.findByUsername(username)
				.map(UserInfoConfig::new)
				.orElseThrow(() -> new UsernameNotFoundException("UserEmail: "+username+" does not exist"));
	}

	public boolean isTokenValid(Jwt decodedToken, UserDetails userDetails) {
		String username = getUserName(decodedToken);
		boolean isTokenExpired = getIfTokenIsExpired(decodedToken);
		boolean isTokenUserSameAsDatabase = username.equals(userDetails.getUsername());
		return !isTokenExpired && isTokenUserSameAsDatabase;
	}

	private boolean getIfTokenIsExpired(Jwt decodedToken) {
		return Objects.requireNonNull(decodedToken.getExpiresAt()).isBefore(Instant.now());
	}

}
