package com.edilre.preventivatore.model;

import com.edilre.preventivatore.entity.Preventivo;

public class DatiClienteModel {
	
	private String nome;
	private String cognome;
	private String indirizzo;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public static DatiClienteModel fromEntity(Preventivo entity) {

		DatiClienteModel model = new DatiClienteModel();
		model.setNome(entity.getNome());
		model.setCognome(entity.getCognome());
		model.setIndirizzo(entity.getIndirizzo());

		return model;

	}

	

}
