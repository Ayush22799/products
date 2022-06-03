package com.example.demo.service;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
	
	@Mock
	@Autowired
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	
	@Test
	public void saveProductTest() {
		when(productRepository.save(Mockito.any(Product.class))).thenReturn(getProduct());
		Product productSaved = productServiceImpl.createProduct(getProduct());
		Assertions.assertNotNull(productSaved);
	}
	private Product getProduct() {
		
		return new Product("Product1", 3200);
		
	}
}	
