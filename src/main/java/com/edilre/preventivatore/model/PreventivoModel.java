package com.edilre.preventivatore.model;

import com.edilre.preventivatore.entity.Preventivo;

public class PreventivoModel {
	
	private Integer id;
	private DatiClienteModel datiCliente;
	private DatiPreventivoModel datiPreventivo;
	public DatiClienteModel getDatiCliente() {
		return datiCliente;
	}
	public void setDatiCliente(DatiClienteModel datiCliente) {
		this.datiCliente = datiCliente;
	}
	public DatiPreventivoModel getDatiPreventivo() {
		return datiPreventivo;
	}
	public void setDatiPreventivo(DatiPreventivoModel datiPreventivo) {
		this.datiPreventivo = datiPreventivo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public static PreventivoModel fromEntity(Preventivo entity) {
		
		PreventivoModel model = new PreventivoModel();
		model.setDatiCliente(DatiClienteModel.fromEntity(entity));
		model.setDatiPreventivo(DatiPreventivoModel.fromEntity(entity));
		model.setId(entity.getId());
		
		return model;
		
	}
	
	

}
