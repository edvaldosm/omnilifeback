package com.br.omnilife.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.br.omnilife.domain.Suplementos;

@Repository
@Transactional
public interface SuplementosRepository extends CrudRepository<Suplementos, Integer> {
	@Query("Select s from Suplementos s Where s.descricao like %:descricao% ")
	public Optional<List<Suplementos>> consultaPorNome(@Param("descricao") String descricao);

}
