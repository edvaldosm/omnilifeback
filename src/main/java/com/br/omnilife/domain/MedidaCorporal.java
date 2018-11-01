package com.br.omnilife.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class MedidaCorporal {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, name="datamedida")
	private Date dtMedida;
	
	private BigDecimal peso;
	
	private BigDecimal busto;
	
	private BigDecimal cintura;
	
	private BigDecimal umbigo;
	
	private BigDecimal quadril;
	
	private BigDecimal coxa;
	
	private BigDecimal braco;
	
	private BigDecimal gorduraViceral;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

}