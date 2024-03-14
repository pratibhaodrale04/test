package com.val.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.val.dto.AuthResponseDTO;
import com.val.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

//@RequestMapping("/api/auth")
@RestController
@Slf4j
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(Authentication authentication){

        return ResponseEntity.ok(authService.getJwtTokensAfterAuthentication(authentication));
    }

}
