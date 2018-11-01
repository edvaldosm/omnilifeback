package com.br.omnilife.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

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
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=50, nullable=false, name="nome")
	private String nome;
	
	@Email
	@Column(length=50, name="email")
	private String email;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, name="dtnascimento")
	private Date dtNascimento;
	
	@Column(length=255, nullable=false, name="observacao")
	private String obs;
	
	@Column(nullable=false, name="atividadesportiva")
	private boolean atividadeEsportiva;
	
	@Column(nullable=false, name="usasuplementos")
	private boolean usaSuplementos;
	
	@ElementCollection
	@CollectionTable(name = "Fone", joinColumns = @JoinColumn(name = "cliente_id", nullable = true))
	private List<Fone> fones = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "Endereco", joinColumns = @JoinColumn(name = "cliente_id", nullable = true))
	private List<Endereco> enderecos = new ArrayList<>();
	
/*	@ElementCollection
	@CollectionTable(name = "MedidaCorporal", joinColumns = @JoinColumn(name = "cliente_id", nullable = true))*/
	@OneToMany(mappedBy="cliente")
	private List<MedidaCorporal> medidas;
	
	@OneToMany(mappedBy="cliente")
	private List<Sintoma> sintomas; 
	
	@OneToMany(mappedBy="cliente")
	private List<Compras> compras; 
}
