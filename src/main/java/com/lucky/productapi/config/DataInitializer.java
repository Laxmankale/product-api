package com.lucky.productapi.config;

import com.lucky.productapi.entity.User;
import com.lucky.productapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) {

		if (userRepository.findByUsername("admin").isEmpty()) {

			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("admin123"));
			admin.setRole("ROLE_ADMIN");

			userRepository.save(admin);
		}
	}
}