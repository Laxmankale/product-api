package com.lucky.productapi.controller;

import com.lucky.productapi.dto.LoginRequest;
import com.lucky.productapi.entity.User;
import com.lucky.productapi.repository.UserRepository;
import com.lucky.productapi.security.JwtUtil;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		User user = userRepository.findByUsername(authentication.getName()).orElseThrow();

		return jwtUtil.generateToken(user.getUsername(), user.getRole());
	}
}