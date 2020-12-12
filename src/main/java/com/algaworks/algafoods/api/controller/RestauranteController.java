package com.algaworks.algafoods.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

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

	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParial(@PathVariable Long restauranteId,
											 @RequestBody Map<String,Object> campos){
		Restaurante restaurante = cadastroRestaurante.burcar(restauranteId);
		if(restaurante == null){
			ResponseEntity.notFound().build();
		}
		merge(campos,restaurante);
		return atualizar(restauranteId, restaurante);

	}

	private void  merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino){

		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem,Restaurante.class);

		camposOrigem.forEach((key, value) -> {
			//retorna as propriedades da class :: findField
			Field field = ReflectionUtils.findField(Restaurante.class,key);
			field.setAccessible(true); //permitir acesso a propriedades cmo visibilidade private

			//agora pegando o valor da propriedade e nao mais o nome da propriedade :: getField
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			ReflectionUtils.setField(field,restauranteDestino,novoValor);
		});
	}

}
