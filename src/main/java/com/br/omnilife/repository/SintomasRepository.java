package com.br.omnilife.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.br.omnilife.domain.Sintoma;

@Repository
@Transactional
public interface SintomasRepository extends CrudRepository<Sintoma, Integer> {

}
