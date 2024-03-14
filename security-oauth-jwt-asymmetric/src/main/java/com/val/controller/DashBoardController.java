package com.val.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class DashBoardController {

	@GetMapping("/adminData")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_USER')")
	@PreAuthorize("hasAuthority('SCOPE_WRITE')")
	public ResponseEntity<String> getAdminData(Principal principal) {
		return ResponseEntity.ok("Admin data::" + principal.getName());
	}

	@GetMapping("/managerData")
//	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_USER')")
	@PreAuthorize("hasAuthority('SCOPE_READ')")
	public ResponseEntity<String> getManagerData(Principal principal) {
		return ResponseEntity.ok("Manager data::" + principal.getName());
	}

	@GetMapping("/userData")
//	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@PreAuthorize("hasAuthority('SCOPE_READ')")
	public ResponseEntity<String> getUserData(Principal principal) {
		return ResponseEntity.ok("User data::" + principal.getName());
	}
}
