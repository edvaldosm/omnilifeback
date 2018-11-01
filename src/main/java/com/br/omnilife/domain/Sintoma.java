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
public class Sintoma {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, name="dtsintoma")
	private Date dtsintoma;
	
	@ManyToMany
	@JoinTable(name = "sintoma_patologia", 
			   joinColumns = { @JoinColumn(name = "id_sintoma") },
			   inverseJoinColumns = {@JoinColumn(name = "id_patologia") })
	private List<Patologia> patologias;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

}