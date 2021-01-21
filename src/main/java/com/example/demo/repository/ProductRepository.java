package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

	Product findByProductName(String name);

	Page<Product> findAll(Pageable pageable);

}
