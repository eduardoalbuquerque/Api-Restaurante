package com.algaworks.algafoods.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

//@JsonRootName("Gastronomia") //muda o nome do objeto em xml
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {
	
	//@JsonIgnore
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//@JsonProperty("titulo") CUIDADO POIS NO INSERT O CAMPO DEVERA SER titulo TAMBÉM
	@Column(nullable=false)
	private String nome;
}
