package com.br.omnilife.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.br.omnilife.domain.enuns.TipoEndereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Endereco {

	@Column(name = "numero", nullable = false)
	private Integer nro;

	@Column(name = "logradouro", length = 50, nullable = false)
	private String logradouro;

	@Column(name = "bairro", length = 20, nullable = false)
	private String bairro;

	@Column(name = "cidade", length = 25)
	private String cidade;

	@Column(name = "uf", length = 2, nullable = false)
	private String uf;

	@Column(name = "cep", length = 8, nullable = false)
	private String cep;

	@Column(name = "tpEndereco", length = 15)
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipoEndereco;
}
