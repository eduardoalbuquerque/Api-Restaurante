package com.algaworks.algafoods.domain.repository;

import java.util.List;

import com.algaworks.algafoods.domain.model.Estado;

public interface EstadoRepository {

	Estado salvar(Estado estado);
	void remover(Long id);
	
}
