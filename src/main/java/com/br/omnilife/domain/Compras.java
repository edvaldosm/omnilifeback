package com.br.omnilife.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Entity
public class Compras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, name="dtcompra")
	private Date dtCompra;

	@ManyToMany
	@JoinTable(name = "compras_suplementos", 
			   joinColumns = { @JoinColumn(name = "id_compras") },
			   inverseJoinColumns = {@JoinColumn(name = "id_suplementos") })
	private List<Suplementos> suplementos;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

}
