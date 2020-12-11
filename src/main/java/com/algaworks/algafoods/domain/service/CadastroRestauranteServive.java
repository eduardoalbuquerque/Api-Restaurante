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
		Long cozinhaId  = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if(cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("ID %d é inválido para a cozinha", cozinhaId));
		}
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.salvar(restaurante);
	}

}
