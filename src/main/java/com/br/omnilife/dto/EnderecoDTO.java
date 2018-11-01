package com.br.omnilife.dto;

import com.br.omnilife.domain.enuns.TipoEndereco;
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
public class EnderecoDTO {
	
	private Integer nro;

	private String logradouro;

	private String bairro;

	private String cidade;

	private String uf;

	private String cep;

	private TipoEndereco tipoEndereco;

}
