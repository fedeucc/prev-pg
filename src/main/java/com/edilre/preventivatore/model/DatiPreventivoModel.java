package com.edilre.preventivatore.model;

import com.edilre.preventivatore.entity.Preventivo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DatiPreventivoModel {
	
	private List<ProdottoPreventivoModel> prodotti;
	private String oggetto;
	private LocalDate data;

	public List<ProdottoPreventivoModel> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<ProdottoPreventivoModel> prodotti) {
		this.prodotti = prodotti;
	}
	
	
	
	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	
	

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public static DatiPreventivoModel fromEntity(Preventivo entity) {

		DatiPreventivoModel model = new DatiPreventivoModel();
		model.setProdotti(entity.getProdotti().stream().peek(prod -> {
			if(Objects.isNull(prod.getId())) {
				prod.setId(entity.getProdotti().indexOf(prod)+1);
			}
        }).map(ProdottoPreventivoModel::fromEntity).collect(Collectors.toList()));
		model.setOggetto(entity.getOggetto());
		model.setData(entity.getData());

		return model;

	}

}
