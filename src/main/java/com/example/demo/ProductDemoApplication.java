package com.example.demo;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Inventory;
import com.example.demo.model.Product;
import com.example.demo.model.ProductCategory;
import com.example.demo.model.Vendor;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.ProductCategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.VendorRepository;

import lombok.NoArgsConstructor;

@SpringBootApplication
@NoArgsConstructor
public class ProductDemoApplication implements CommandLineRunner {

	private ProductRepository productRepository;
	private ProductCategoryRepository productCategoryRepository;
	private VendorRepository vendorRepository;
	private InventoryRepository inventoryRepository;

	@Autowired
	public ProductDemoApplication(ProductRepository productRepository,
			ProductCategoryRepository productCategoryRepository, VendorRepository vendorRepository,
			InventoryRepository inventoryRepository) {

		this.productRepository = productRepository;
		this.productCategoryRepository = productCategoryRepository;
		this.vendorRepository = vendorRepository;
		this.inventoryRepository = inventoryRepository;

	}

	public static void main(String[] args) {
		SpringApplication.run(ProductDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		this.deleteAll();
		this.insertAll();
	}

	private void deleteAll() {

		inventoryRepository.deleteAll();
		productRepository.deleteAll();
		productRepository.deleteAll();
		vendorRepository.deleteAll();

	}

	private void insertAll() {

		this.initVendors();
		this.initProductCategories();
		this.initProducts();
		this.initInventories();

	}

	private void initVendors() {

		Vendor umesh = new Vendor("Umesh", "Surat", "ASD234");
		Vendor ashish = new Vendor("Ashish", "Surat", "G4534");
		Vendor dhrumin = new Vendor("Dhrumin", "Surat", "GB345");

		vendorRepository.saveAll(Arrays.asList(umesh, ashish, dhrumin));

	}

	private void initProductCategories() {

		ProductCategory cricket = new ProductCategory("Cricket");
		ProductCategory elecrtonics = new ProductCategory("Electronics");

		productCategoryRepository.saveAll(Arrays.asList(cricket, elecrtonics));

	}

	private void initProducts() {

		Vendor umesh = vendorRepository.findByName("Umesh");
		Vendor ashish = vendorRepository.findByName("Ashish");

		ProductCategory cricket = productCategoryRepository.findByName("Cricket");
		ProductCategory ele = productCategoryRepository.findByName("Electronics");

		Product batProduct = new Product("Bat", new BigDecimal(1050.00), new BigDecimal(1000.00));
		batProduct.setProductCategory(cricket);
		batProduct.setVendor(ashish);

		Product ball = new Product("Ball", new BigDecimal(123.00), new BigDecimal(143.00));
		ball.setProductCategory(cricket);
		ball.setVendor(umesh);

		Product mobile = new Product("Mobile", new BigDecimal(5000.00), new BigDecimal(44500.00));
		mobile.setProductCategory(ele);
		mobile.setVendor(umesh);

		productRepository.saveAll(Arrays.asList(batProduct, ball, mobile));

	}

	private void initInventories() {

		Product bat = productRepository.findByProductName("Bat");
		Product ball = productRepository.findByProductName("Ball");
		Product mobile = productRepository.findByProductName("Mobile");

		Inventory in1 = new Inventory("abc", 20, new BigDecimal(1050.00), new BigDecimal(1000.00));
		in1.setProduct(bat);
		Inventory in2 = new Inventory("def", 10, new BigDecimal(1090.00), new BigDecimal(1000.00));
		in2.setProduct(ball);
		Inventory in3 = new Inventory("xyz", 15, new BigDecimal(1100.00), new BigDecimal(1000.00));
		in3.setProduct(mobile);

		inventoryRepository.saveAll(Arrays.asList(in1, in2, in3));

	}

}
