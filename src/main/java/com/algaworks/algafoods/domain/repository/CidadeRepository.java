package com.algaworks.algafoods.domain.repository;

import java.util.List;

import com.algaworks.algafoods.domain.model.Cidade;

public interface CidadeRepository {
	
	List<Cidade> listar();
	Cidade buscar(Long cidadeId);
	Cidade salvar(Cidade cidade);
	void remover(Long cidadeId);
	

}
