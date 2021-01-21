package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, String> {
	
	Vendor findByName(String name);

}
