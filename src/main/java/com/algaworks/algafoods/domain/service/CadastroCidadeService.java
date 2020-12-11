package com.algaworks.algafoods.domain.service;

import com.algaworks.algafoods.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoods.domain.model.Cidade;
import com.algaworks.algafoods.domain.model.Estado;
import com.algaworks.algafoods.domain.repository.CidadeRepository;
import com.algaworks.algafoods.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    private CidadeRepository cidadeRepository;
    private EstadoRepository estadoRepository;

    public CadastroCidadeService(CidadeRepository cidadeRepository,
                                 EstadoRepository estadoRepository){
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    public Cidade salvar(Cidade cidade){
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.buscar(estadoId);
        if(estadoId == null){
            throw  new EntidadeNaoEncontradaException(
                    String.format("Estado com ID %d não encontrado",estadoId));
        }
        cidade.setEstado(estado);
        return cidadeRepository.salvar(cidade);
    }

    public void excluir(Long id){
        try {
            cidadeRepository.remover(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format("Cidade com ID %d não encontrada"));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Cidade de ID %d nõ pode ser excluída", id));
        }
    }


}
