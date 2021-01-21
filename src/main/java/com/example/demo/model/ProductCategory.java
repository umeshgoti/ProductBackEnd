package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.example.demo.audit.Auditable;
import com.example.demo.utils.VariableUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@JsonInclude(Include.NON_EMPTY)
@NoArgsConstructor
@Table(name = VariableUtils.TABLE_NAME_FOR_PRODUCT_CATEGORY)
public class ProductCategory extends Auditable {

	@Getter
	@Setter
	private String name;

	@OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
	@Getter
	@Setter
	@JsonIgnore
	private List<Product> products;
	
	public ProductCategory(String name) {
		this.name = name;
	}

	@PreRemove
	protected void preRemove() {

		List<Product> products = this.getProducts();
		if (products != null && !products.isEmpty()) {
			products.forEach(product -> product.setProductCategory(null));
		}
	}

}
