package com.algaworks.algafoods.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.algaworks.algafoods.domain.model.Cidade;
import com.algaworks.algafoods.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;

	public CidadeRepositoryImpl(EntityManager manager){
		this.manager = manager;
	}

	@Override
	public List<Cidade> listar() {
		return manager.createQuery("From Cidade ", Cidade.class).getResultList();
	}

	@Override
	public Cidade buscar(Long cidadeId) {
		return manager.find(Cidade.class,cidadeId);
	}

	@Transactional
	@Override
	public Cidade salvar(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Transactional
	@Override
	public void remover(Long cidadeId) {
		Cidade cidade = buscar(cidadeId);
		manager.remove(cidade);
	}
}
