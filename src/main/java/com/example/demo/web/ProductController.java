package com.example.demo.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.filter.ActionResponse;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
@NoArgsConstructor
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/getProductDetails")
	public ResponseEntity<Object> getAllProductDetails(
			@RequestParam(name = "productName", required = false) String productName,
			@RequestParam(name = "categoryName", required = false) String categoryName, 
			@RequestParam("page") int page,
			@RequestParam("size") int size, 
			@RequestParam(name = "sortColumn", required = false, defaultValue = "id") String sortColumn,
			@RequestParam(name = "sortDirection", required = false, defaultValue = "DESC") String sortDirection, 
			HttpServletRequest httpServletRequest) {

		try {

			Page<Product> productDetails = productService.getProductDetails(productName, categoryName, page, size,
					sortColumn, sortDirection);

			return ActionResponse.getResponse("Get Products", productDetails.getContent(), HttpStatus.OK,
					httpServletRequest);

		} catch (Exception e) {

			return ActionResponse.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST, httpServletRequest);
		}

	}

}
