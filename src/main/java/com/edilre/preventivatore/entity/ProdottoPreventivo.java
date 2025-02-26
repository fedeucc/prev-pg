package com.edilre.preventivatore.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.edilre.preventivatore.model.ProdottoPreventivoModel;


public class ProdottoPreventivo {

	private Integer id;

	private String descrizione;

	private String um;

	private BigDecimal qty;

	private BigDecimal unit;

	private BigDecimal price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getUnit() {
		return unit;
	}

	public void setUnit(BigDecimal unit) {
		this.unit = unit;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public static ProdottoPreventivo fromModel(ProdottoPreventivoModel model) {
		ProdottoPreventivo prod = new ProdottoPreventivo();
		prod.setDescrizione(model.getDesc());
		prod.setUm(model.getUm());
		prod.setQty(model.getQty());
		prod.setUnit(model.getUnit());
		prod.setPrice(model.getPrice());
		
		return prod;
	}

}
