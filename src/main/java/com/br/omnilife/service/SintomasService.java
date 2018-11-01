package com.br.omnilife.service;

import java.util.List;

import com.br.omnilife.dto.SintomaDTO;
import com.br.omnilife.exceptions.SintomasExceptions;

public interface SintomasService {

	public SintomaDTO inserir(SintomaDTO sintoma) throws SintomasExceptions;;

	public SintomaDTO atualizar(SintomaDTO sintoma, Integer id) throws SintomasExceptions;;

	public SintomaDTO consultaID(Integer id) throws SintomasExceptions;;

	public List<SintomaDTO> listaGeral() throws SintomasExceptions;
}
