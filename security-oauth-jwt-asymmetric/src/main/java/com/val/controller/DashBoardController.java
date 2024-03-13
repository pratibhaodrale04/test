package com.val.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/api")
@RestController
public class DashBoardController {

	@GetMapping("/adminData")
	public ResponseEntity<String> getAdminData(Principal principal) {
		return ResponseEntity.ok("Admin data::" + principal.getName());
	}

	@GetMapping("/managerData")
	public ResponseEntity<String> getManagerData(Principal principal) {
		return ResponseEntity.ok("Manager data::" + principal.getName());
	}

	@GetMapping("/userData")
	public ResponseEntity<String> getUserData(Principal principal) {
		return ResponseEntity.ok("User data::" + principal.getName());
	}
}