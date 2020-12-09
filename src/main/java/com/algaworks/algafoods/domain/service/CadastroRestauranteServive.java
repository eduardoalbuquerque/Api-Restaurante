package com.algaworks.algafoods.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.algaworks.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoods.domain.model.Cozinha;
import com.algaworks.algafoods.domain.model.Restaurante;
import com.algaworks.algafoods.domain.repository.CozinhaRepository;
import com.algaworks.algafoods.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteServive {
	
	private RestauranteRepository restauranteRepository;
	private CozinhaRepository cozinhaRepository;
	
	public CadastroRestauranteServive(RestauranteRepository restauranteRepository, 
			CozinhaRepository cozinhaRepository) {
		this.restauranteRepository = restauranteRepository;
		this.cozinhaRepository = cozinhaRepository;
	}
	
	public List<Restaurante> listar(){
		return restauranteRepository.listar();
	}
	
	public Restaurante burcar(Long id) {
		return restauranteRepository.buscar(id);
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		Long id  = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(id);
		
		if(cozinha == null) {
			throw new EntidadeNaoEncontradaException("ID inválido para a cozinha");
		}
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.adicionar(restaurante);
	}
	

}
