package com.edilre.preventivatore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edilre.preventivatore.model.ProdottoModel;
import com.edilre.preventivatore.service.ProdottoService;

@RestController
@RequestMapping(value = "/api/prodotti")
public class ProdottoController {
	
	@Autowired
	private ProdottoService service;
	
	@GetMapping(value = "/")
	public ResponseEntity<List<ProdottoModel>> getProdotti() {
		
		return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
	}

}
