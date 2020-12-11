package com.algaworks.algafoods.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.algafoods.domain.model.Cidade;
import com.algaworks.algafoods.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public Cidade salvar(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		manager.remove(id);
	}

}
