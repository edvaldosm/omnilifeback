package com.br.omnilife.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.br.omnilife.domain.enuns.TipoFone;

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
public class Fone {

	@Column(name = "ddd", length = 3, nullable = false)
	private String ddd;

	@Column(name = "numero", length = 9, nullable = false)
	private String nro;

	@Column(name = "tpFone", length = 15)
	@Enumerated(EnumType.STRING)
	private TipoFone tpfone;

}
