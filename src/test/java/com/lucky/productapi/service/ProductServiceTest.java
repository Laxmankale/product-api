package com.lucky.productapi.service;

import com.lucky.productapi.entity.Product;
import com.lucky.productapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

	private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);

	private final ProductService productService = new ProductService(productRepository);

	@Test
	void shouldCreateProduct() {

		Product product = new Product();
		product.setProductName("Laptop");

		when(productRepository.save(any(Product.class))).thenReturn(product);

		Product saved = productService.createProduct(product);

		assertNotNull(saved);
		assertEquals("Laptop", saved.getProductName());
	}

	@Test
	void shouldReturnAllProducts() {

		when(productRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(new Product())));

		assertFalse(productService.getAllProducts(Pageable.unpaged()).isEmpty());
	}

	@Test
	void shouldThrowWhenProductNotFound() {

		when(productRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> productService.getProductById(1L));
	}
}