package com.edilre.preventivatore.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.edilre.preventivatore.entity.Preventivo;

public interface PreventivoRepository extends MongoRepository<Preventivo, UUID> {


    Preventivo findFirstByOrderByIdDesc();

    Preventivo findById(Integer id);

}
