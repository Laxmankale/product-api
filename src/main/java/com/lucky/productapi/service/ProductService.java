package com.lucky.productapi.service;

import com.lucky.productapi.entity.Product;
import com.lucky.productapi.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public Product createProduct(Product product) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		product.setCreatedBy(username);
		product.setCreatedOn(LocalDateTime.now());
		return productRepository.save(product);
	}

	public Page<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Product getProductById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}

	public Product updateProduct(Long id, Product updatedProduct) {
		Product existing = getProductById(id);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		existing.setProductName(updatedProduct.getProductName());
		existing.setModifiedBy(username);
		existing.setModifiedOn(LocalDateTime.now());

		return productRepository.save(existing);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}