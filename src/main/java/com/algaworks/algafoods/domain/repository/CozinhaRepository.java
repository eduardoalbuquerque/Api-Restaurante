package com.algaworks.algafoods.domain.repository;

import java.util.List;

import com.algaworks.algafoods.domain.model.Cozinha;

public interface CozinhaRepository {
	
	List<Cozinha> listar();
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Long id);

}
