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
@Table(name = VariableUtils.TABLE_NAME_FOR_VENDOR)
public class Vendor extends Auditable {

	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String address;
	
	@Getter
	@Setter
	private String gstNo;

	@OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
	@JsonIgnore
	@Getter
	@Setter
	private List<Product> products;

	public Vendor(String name, String address, String gstNo) {
		this.name = name;
		this.address = address;
		this.gstNo = gstNo;
	}

	@PreRemove
	protected void preRemove() {

		List<Product> products = this.getProducts();
		if (products != null && !products.isEmpty()) {
			products.forEach(product -> product.setVendor(null));
		}
	}

}
