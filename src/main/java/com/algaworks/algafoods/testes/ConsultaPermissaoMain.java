package com.algaworks.algafoods.testes;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoods.AlgafoodApiApplication;
import com.algaworks.algafoods.domain.model.Permissao;
import com.algaworks.algafoods.domain.repository.PermissaoRepository;
import com.algaworks.algafoods.infrastructure.repository.PermissaoRepositoryImpl;

public class ConsultaPermissaoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		
		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepositoryImpl.class);
		
		List<Permissao> todasPermissoes = permissaoRepository.listar();
		
		for(Permissao permissao : todasPermissoes) {
			System.out.println(permissao);
		}

	}

}
