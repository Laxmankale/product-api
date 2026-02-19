package com.lucky.productapi.service;

import com.lucky.productapi.entity.Product;
import com.lucky.productapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public Product createProduct(Product product) {
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

		existing.setProductName(updatedProduct.getProductName());
		existing.setModifiedBy(updatedProduct.getModifiedBy());
		existing.setModifiedOn(LocalDateTime.now());

		return productRepository.save(existing);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}