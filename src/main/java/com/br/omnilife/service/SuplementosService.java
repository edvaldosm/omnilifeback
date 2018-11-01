package com.br.omnilife.service;

import java.util.List;

import com.br.omnilife.dto.SuplementosDTO;
import com.br.omnilife.exceptions.SuplementosExceptions;

public interface SuplementosService {

	public SuplementosDTO inserir(SuplementosDTO suplementos) throws SuplementosExceptions;;

	public SuplementosDTO atualizar(SuplementosDTO suplementos, Integer id) throws SuplementosExceptions;;

	public SuplementosDTO consultaID(Integer id) throws SuplementosExceptions;;

	public List<SuplementosDTO> consultaPorNome(String descricao) throws SuplementosExceptions;

	public List<SuplementosDTO> listaGeral() throws SuplementosExceptions;
}
