package com.algaworks.algafoods.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.algaworks.algafoods.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoods.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.algafoods.domain.model.Estado;
import com.algaworks.algafoods.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository{
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		try {
			manager.remove(id);
		}catch (EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaException(
					String.format("Estado de ID 5d não encontrado",id));
		}catch (DataIntegrityViolationException e){
			throw new EntidadeEmUsoException(
					String.format("Estado de ID %d está em uso, então nao poderá ser exluído"));
		}
			
	}
	

}
