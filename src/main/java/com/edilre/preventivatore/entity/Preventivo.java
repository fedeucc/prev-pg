package com.edilre.preventivatore.entity;

import javax.persistence.*;

import com.edilre.preventivatore.model.PreventivoModel;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Document(collection = "preventivi")
public class Preventivo {

	@Id
	private ObjectId _id;

	@Field
	private Integer id;

	@Field
	@TextIndexed
	private String nome;

	@Field
	@TextIndexed
	private String cognome;

	@Field
	@TextIndexed
	private String email;

	@Field
	@TextIndexed
	private String telefono;

	@Field
	private String indirizzo;

	@Field
	@TextIndexed
	private String oggetto;

	@Field
	private LocalDate data;

	@Field
	private List<ProdottoPreventivo> prodotti;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

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

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public List<ProdottoPreventivo> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<ProdottoPreventivo> prodotti) {
		this.prodotti = prodotti;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
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

	public static Preventivo fromModel(PreventivoModel model) {
		Preventivo prev = new Preventivo();
		prev.setId(model.getId());
		prev.setIndirizzo(model.getDatiCliente().getIndirizzo());
		prev.setData(model.getDatiPreventivo().getData());
		prev.setCognome(model.getDatiCliente().getCognome());
		prev.setEmail(model.getDatiCliente().getEmail());
		prev.setTelefono(model.getDatiCliente().getTelefono());
		prev.setNome(model.getDatiCliente().getNome());
		prev.setOggetto(model.getDatiPreventivo().getOggetto());
		prev.setProdotti(model.getDatiPreventivo().getProdotti().stream().map(ProdottoPreventivo::fromModel).collect(Collectors.toList()));
		return prev;
	}

}
