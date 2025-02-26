package com.edilre.preventivatore.model;

import javax.persistence.Column;

import com.edilre.preventivatore.entity.Preventivo;
import com.edilre.preventivatore.entity.Prodotto;

public class ProdottoModel {
	
	private Integer id;
	private String descrizione;
	private String um;
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
	
	public static ProdottoModel fromEntity(Prodotto entity) {
		
		ProdottoModel model = new ProdottoModel();
		model.setDescrizione(entity.getDescrizione());
		model.setUm(entity.getUm());
		
		return model;
		
	}

}
