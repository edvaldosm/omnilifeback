package com.br.omnilife.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.br.omnilife.domain.Patologia;

@Repository
@Transactional
public interface PatologiaRepository extends CrudRepository<Patologia, Integer> {
	@Query("Select p from Patologia p Where p.nome like %:nome% ")
	public Optional<List<Patologia>> consultaPorNome(@Param("nome") String nome);

}
