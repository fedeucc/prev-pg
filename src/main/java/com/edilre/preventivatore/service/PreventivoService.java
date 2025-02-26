package com.edilre.preventivatore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import com.edilre.preventivatore.entity.Preventivo;
import com.edilre.preventivatore.model.PreventivoModel;
import com.edilre.preventivatore.repo.PreventivoRepository;
import com.mysql.cj.util.StringUtils;

@Service
public class PreventivoService {

	@Autowired
	private PreventivoRepository repo;

	@Autowired
	private MongoOperations mongoOperations;

	public PreventivoModel save(PreventivoModel model) {

		model.getDatiPreventivo().setData(LocalDate.now());
		Preventivo entity = Preventivo.fromModel(model);

		Preventivo lastPrev = this.repo.findFirstByOrderByIdDesc();

		entity.setId(lastPrev.getId()+1);
		entity = this.repo.save(entity);

		model.setId(entity.getId());
		return model;

	}

	public List<PreventivoModel> getAll(String text) {
		if (StringUtils.isEmptyOrWhitespaceOnly(text)) {
			return this.repo.findAll().stream().map(PreventivoModel::fromEntity).filter(pre -> Objects.nonNull(pre.getDatiPreventivo())).sorted((pre, pre1) -> pre1.getDatiPreventivo().getData().compareTo(pre.getDatiPreventivo().getData())).collect(Collectors.toList());
		}
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(text);
		Query query = TextQuery.queryText(criteria);
		return mongoOperations.find(query, Preventivo.class).stream().map(PreventivoModel::fromEntity).filter(pre -> Objects.nonNull(pre.getDatiPreventivo())).sorted((pre, pre1) -> pre1.getDatiPreventivo().getData().compareTo(pre.getDatiPreventivo().getData())).collect(Collectors.toList());
	}

	public PreventivoModel update(PreventivoModel model) {

		Preventivo entity = Preventivo.fromModel(model);

		Preventivo oldEntity = this.repo.findById(model.getId());
		entity.set_id(oldEntity.get_id());

		entity = this.repo.save(entity);

		model.setId(entity.getId());
		return model;

	}

	public PreventivoModel getById(Integer id) {

		Preventivo preventivo = this.repo.findById(id);

		return PreventivoModel.fromEntity(preventivo);

	}

}
