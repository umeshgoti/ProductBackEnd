
package com.example.demo.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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
@Table(name = VariableUtils.TABLE_NAME_FOR_PRODUCT)
public class Product extends Auditable {

	@Getter
	@Setter
	private String productName;

	@Getter
	@Setter
	private BigDecimal costPrice;
	
	@Getter
	@Setter
	private BigDecimal retailPrice;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	
	@Getter
	@Setter
	private List<Inventory> inventories;

	@ManyToOne(fetch = FetchType.LAZY)
	@Getter
	@Setter
	private ProductCategory productCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@Getter
	@Setter
	private Vendor vendor;

	public Product(String productName, BigDecimal costPrice, BigDecimal retailPrice) {
		this.productName = productName;
		this.costPrice = costPrice;
		this.retailPrice = retailPrice;
	}

	@PreRemove
	protected void preRemove() {

		List<Inventory> inventories = this.getInventories();
		if (inventories != null && !inventories.isEmpty()) {
			inventories.forEach(inventory -> inventory.setProduct(null));
		}

		ProductCategory productCategory = this.getProductCategory();
		if (productCategory != null) {
			productCategory.getProducts().remove(this);
		}

		Vendor vendor = this.getVendor();
		if (vendor != null) {
			vendor.getProducts().remove(this);
		}
	}

}
