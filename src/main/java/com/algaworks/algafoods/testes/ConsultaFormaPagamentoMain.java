package com.algaworks.algafoods.testes;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafoods.AlgafoodApiApplication;
import com.algaworks.algafoods.domain.model.FormaPagamento;
import com.algaworks.algafoods.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafoods.infrastructure.repository.FormaPagamentoRepositoryImpl;

public class ConsultaFormaPagamentoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		
		FormaPagamentoRepository consultaFormaPagamento = applicationContext.getBean(FormaPagamentoRepositoryImpl.class);
		
		List<FormaPagamento> TodasFormaPagamento = consultaFormaPagamento.listar();
		
		for(FormaPagamento formaPagamento : TodasFormaPagamento) {
			System.out.println(formaPagamento);
		}

	}

}
