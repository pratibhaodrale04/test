package com.val.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Autowired
	private UserInfoManagerConfig userInfoManagerConfig;
	
	@Order(value = 1)
	@Bean
	public SecurityFilterChain apiSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.securityMatcher(new AntPathRequestMatcher("/api/**"))
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
				.userDetailsService(userInfoManagerConfig)
				.httpBasic(withDefaults())
				.build();
	}
	
	@Order(value = 2)
	public SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.securityMatcher(new AntPathRequestMatcher("/h2-console/**"))
				.csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
				.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
//				 to display the h2Console in Iframe
				.headers(header -> header.frameOptions(withDefaults()).disable())
				.build();
	}
	
}
