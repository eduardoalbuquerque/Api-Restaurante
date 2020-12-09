package com.algaworks.algafoods.testes;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoods.AlgafoodApiApplication;
import com.algaworks.algafoods.domain.model.Cidade;
import com.algaworks.algafoods.domain.repository.CidadeRepository;
import com.algaworks.algafoods.infrastructure.repository.CidadeRepositoryImpl;

public class ConsultaCidadeMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		
		CidadeRepository cidadeRepository  = applicationContext.getBean(CidadeRepositoryImpl.class);
		
		List<Cidade> todasCidades = cidadeRepository .listar();
		
		for(Cidade cidade : todasCidades) {
			System.out.println(cidade);
		}
	}
}
