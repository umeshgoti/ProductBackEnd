package com.example.demo.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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
@Table(name = VariableUtils.TABLE_NAME_FOR_INVENTORY)
public class Inventory extends Auditable {

	@Getter
	@Setter
	private String display_name;

	@Getter
	@Setter
	private int qty;

	@Getter
	@Setter
	private BigDecimal costPrice;

	@Getter
	@Setter
	private BigDecimal retailPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@Getter
	@Setter
	@JsonIgnore
	private Product product;

	public Inventory(String display_name, int qty, BigDecimal costPrice, BigDecimal retailPrice) {
		this.display_name = display_name;
		this.qty = qty;
		this.costPrice = costPrice;
		this.retailPrice = retailPrice;
	}

	@PreRemove
	protected void preRemove() {

		Product product = this.getProduct();
		if (product != null) {
			product.getInventories().remove(this);
		}

	}

}
