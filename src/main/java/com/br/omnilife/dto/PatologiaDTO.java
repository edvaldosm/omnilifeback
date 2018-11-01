package com.br.omnilife.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(value=Include.NON_NULL)
public class PatologiaDTO {

	private Integer id;
	
	@NotNull(message="Campo nome obrigatório")
	@NotBlank(message="Campo nome não pode estar em branco")
	private String nome;

	@NotNull(message="Campo descricao obrigatório")
	@NotBlank(message="Campo descricao não pode estar em branco")
	private String descricao;


}