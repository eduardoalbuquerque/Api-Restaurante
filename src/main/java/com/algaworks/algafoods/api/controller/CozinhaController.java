package com.algaworks.algafoods.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoods.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoods.domain.model.Cozinha;
import com.algaworks.algafoods.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@GetMapping
	public List<Cozinha> listar(){
		return cadastroCozinha.listar();
	}
	
	//@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cadastroCozinha.buscar(cozinhaId);
		if(cozinha != null) 
			return ResponseEntity.ok(cozinha);
			//return ResponseEntity.status(HttpStatus.OK).body(cozinha);
		
			return ResponseEntity.notFound().build();
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}
	

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
			@RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = cadastroCozinha.buscar(cozinhaId);
		
		if (cozinhaAtual != null) {
//			cozinhaAtual.setNome(cozinha.getNome());
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			
			cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
			return ResponseEntity.ok(cozinhaAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping(value="/{cozinhaId}")
	public ResponseEntity<Cozinha> delete(@PathVariable Long cozinhaId) {
		try {

			cadastroCozinha.excluir(cozinhaId);
			return ResponseEntity.noContent().build();
		
		}catch(EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		} catch (EntidadeEmUsoException e ) {
		
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		
		}
		
	}
		

}
