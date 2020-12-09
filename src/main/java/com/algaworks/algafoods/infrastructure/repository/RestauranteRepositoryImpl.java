package com.algaworks.algafoods.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.algafoods.domain.model.Restaurante;
import com.algaworks.algafoods.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> listar() {
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}

	@Transactional
	@Override
	public Restaurante adicionar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}

	@Transactional
	@Override
	public void deletar(Long restauranteId) {
		Restaurante restaurante = this.buscar(restauranteId);
		if(restaurante == null) 
			throw new EmptyResultDataAccessException(1);
		manager.remove(restaurante);
	}

}
