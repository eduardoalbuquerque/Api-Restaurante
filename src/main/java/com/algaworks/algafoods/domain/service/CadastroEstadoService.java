package com.algaworks.algafoods.domain.service;

import com.algaworks.algafoods.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoods.domain.model.Estado;
import com.algaworks.algafoods.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroEstadoService {

    private EstadoRepository estadoRepository;

    public CadastroEstadoService(EstadoRepository estadoRepository){
        this.estadoRepository = estadoRepository;
    }

    public Estado buscar(Long estadoId){
        return estadoRepository.buscar(estadoId);
    }

    public Estado salvar(Estado estado){
        return estadoRepository.salvar(estado);
    }

    public void remover(Long id){
        try {
            estadoRepository.remover(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não Existe um cadastro de estado com o código %d", id));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser exluído, pois está em uso", id));
        }

    }

}
