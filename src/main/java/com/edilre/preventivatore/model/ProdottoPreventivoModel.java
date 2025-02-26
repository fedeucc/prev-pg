package com.edilre.preventivatore.model;

import java.math.BigDecimal;

import com.edilre.preventivatore.entity.Preventivo;
import com.edilre.preventivatore.entity.ProdottoPreventivo;

public class ProdottoPreventivoModel {
	
	private Integer id;
	private String desc;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	
	public static ProdottoPreventivoModel fromEntity(ProdottoPreventivo entity) {
		
		ProdottoPreventivoModel model = new ProdottoPreventivoModel();
		
		model.setDesc(entity.getDescrizione());
		model.setUm(entity.getUm());
		model.setQty(entity.getQty());
		model.setUnit(entity.getUnit());
		model.setPrice(entity.getPrice());
		model.setId(entity.getId());
		
		return model;
		
	}
	

}
