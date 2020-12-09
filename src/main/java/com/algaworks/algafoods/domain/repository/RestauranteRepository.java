package com.algaworks.algafoods.domain.repository;

import java.util.List;

import com.algaworks.algafoods.domain.model.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> listar();
	Restaurante buscar(Long id);
	Restaurante adicionar(Restaurante restaurante);
	void deletar(Long id);

}
