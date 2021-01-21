package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.model.Product;

public interface ProductService {

	Page<Product> getProductDetails(String productName, String categoryName, int page, int size, String sortColumn,
			String sortDirection);

}
