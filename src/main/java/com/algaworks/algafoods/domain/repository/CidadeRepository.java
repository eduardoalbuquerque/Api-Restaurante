package com.algaworks.algafoods.domain.repository;

import java.util.List;

import com.algaworks.algafoods.domain.model.Cidade;

public interface CidadeRepository {
	
	Cidade salvar(Cidade cidade);
	void remover(Long id);
	

}
