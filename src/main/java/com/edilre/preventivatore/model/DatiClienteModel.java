package com.edilre.preventivatore.model;

import com.edilre.preventivatore.entity.Preventivo;

public class DatiClienteModel {
	
	private String nome;
	private String cognome;
	private String indirizzo;
	private String email;
	private String telefono;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public static DatiClienteModel fromEntity(Preventivo entity) {

		DatiClienteModel model = new DatiClienteModel();
		model.setNome(entity.getNome());
		model.setCognome(entity.getCognome());
		model.setIndirizzo(entity.getIndirizzo());
		model.setEmail(entity.getEmail());
		model.setTelefono(entity.getTelefono());

		return model;

	}

	

}
