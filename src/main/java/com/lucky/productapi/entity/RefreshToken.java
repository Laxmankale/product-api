package com.lucky.productapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String token;

	@Column(nullable = false)
	private LocalDateTime expiryDate;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}