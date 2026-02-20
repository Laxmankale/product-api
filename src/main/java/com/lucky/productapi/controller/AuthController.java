package com.lucky.productapi.controller;

import com.lucky.productapi.dto.LoginRequest;
import com.lucky.productapi.dto.RegisterRequest;
import com.lucky.productapi.entity.User;
import com.lucky.productapi.repository.UserRepository;
import com.lucky.productapi.security.JwtUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
			UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		User user = userRepository.findByUsername(authentication.getName()).orElseThrow();

		return jwtUtil.generateToken(user.getUsername(), user.getRole());
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

		if (userRepository.existsByUsername(request.getUsername())) {
			return ResponseEntity.badRequest().body("Username already exists");
		}

		String role = request.getRole();
		if (role == null || (!role.equals("ROLE_ADMIN") && !role.equals("ROLE_USER"))) {
			return ResponseEntity.badRequest().body("Role must be ROLE_ADMIN or ROLE_USER");
		}

		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(role);

		userRepository.save(user);

		return ResponseEntity.ok("User registered successfully");
	}
}