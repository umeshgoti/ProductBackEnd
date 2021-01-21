package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.utils.MethodUtils;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Page<Product> getProductDetails(String productName, String categoryName, int page, int size,
			String sortColumn, String sortDirection) {

		Pageable pageable = MethodUtils.getPageable(page, size, sortColumn, sortDirection, productName);

		return productRepository.findAll(pageable);

	}

}
