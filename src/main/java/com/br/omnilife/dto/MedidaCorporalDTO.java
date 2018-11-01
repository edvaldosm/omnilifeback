package com.br.omnilife.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class MedidaCorporalDTO {
	
	private Integer id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dtMedida;
	
	private BigDecimal peso;
	
	private BigDecimal busto;
	
	private BigDecimal cintura;
	
	private BigDecimal umbigo;
	
	private BigDecimal quadril;
	
	private BigDecimal coxa;
	
	private BigDecimal braco;
	
	private BigDecimal gorduraViceral;

	private ClienteDTO cliente;

}
