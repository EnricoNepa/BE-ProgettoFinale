package com.epicenergy.security.controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.epicenergy.security.model.User;
import com.epicenergy.security.service.UserService;

@RestController
@RequestMapping("/api/user/")
@SecurityRequirement(name="bearerAuth")
@Tag(name = "Utente")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Ricerca utente per ID")
	public ResponseEntity<User> findById(@PathVariable(required = true) Integer id) {
		Optional<User> find = userService.findById(id);
		
		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
