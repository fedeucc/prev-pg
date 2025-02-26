package com.edilre.preventivatore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edilre.preventivatore.model.ProdottoModel;
import com.edilre.preventivatore.repo.ProdottoRepository;

@Service
public class ProdottoService {
	
	@Autowired
	private ProdottoRepository repo;
	
	public List<ProdottoModel> findAll() {
		return this.repo.findAll().stream().map(ProdottoModel::fromEntity).collect(Collectors.toList());
	}

}
