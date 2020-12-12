package com.algaworks.algafoods.api.controller;

import com.algaworks.algafoods.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoods.domain.model.Estado;
import com.algaworks.algafoods.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private CadastroEstadoService cadastroEstado;

    public EstadoController(CadastroEstadoService cadastroEstado){
        this.cadastroEstado = cadastroEstado;
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId){
        Estado estado = cadastroEstado.buscar(estadoId);
        if(estado != null){
            return ResponseEntity.ok(estado);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Estado>  adicionar(@RequestBody Estado estado){
        Estado currentEstado = cadastroEstado.salvar(estado);
        if(currentEstado != null){
            return ResponseEntity.ok(currentEstado);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @PutMapping("/{estadoId}")
    public  ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){
        Estado currentEstado = cadastroEstado.buscar(estadoId);
        if(currentEstado != null){
            BeanUtils.copyProperties(estado,currentEstado,"id");
            return ResponseEntity.ok(currentEstado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId){
        try {
            cadastroEstado.remover(estadoId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

}
