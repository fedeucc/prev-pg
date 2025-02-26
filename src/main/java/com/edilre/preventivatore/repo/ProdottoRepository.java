package com.edilre.preventivatore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edilre.preventivatore.entity.Prodotto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProdottoRepository extends MongoRepository<Prodotto, UUID> {

}
