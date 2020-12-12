package com.algaworks.algafoods.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoods.domain.model.Restaurante;
import com.algaworks.algafoods.domain.service.CadastroRestauranteServive;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	private CadastroRestauranteServive cadastroRestaurante;
	
	public RestauranteController(CadastroRestauranteServive cadastroRestaurante) {
		this.cadastroRestaurante = cadastroRestaurante;
	}
	
	@GetMapping
	public List<Restaurante> listar(){

		return cadastroRestaurante.listar();
	}
	
	@GetMapping(value="/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable(value="restauranteId") Long id) {
		Restaurante restaurante = cadastroRestaurante.burcar(id);

		if(restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
		try {
			restaurante =  cadastroRestaurante.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(restaurante);
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping(value="/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, 
			@RequestBody Restaurante restaurante){
		
		try {
			
			Restaurante restauranteAtual = cadastroRestaurante.burcar(restauranteId);
			
			if(restauranteAtual != null) {
			
				BeanUtils.copyProperties(restaurante, restauranteAtual,"id");
				
				restauranteAtual = cadastroRestaurante.salvar(restauranteAtual);
				
				return ResponseEntity.ok(restauranteAtual);
			}
			
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}

}
