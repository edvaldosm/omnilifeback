package com.br.omnilife.service;

import java.util.List;

import com.br.omnilife.dto.PatologiaDTO;
import com.br.omnilife.exceptions.PatologiaExceptions;

public interface PatologiaService {

	public PatologiaDTO inserir(PatologiaDTO patologia) throws PatologiaExceptions;;

	public PatologiaDTO atualizar(PatologiaDTO patologia, Integer id) throws PatologiaExceptions;;

	public PatologiaDTO consultaID(Integer id) throws PatologiaExceptions;;

	public List<PatologiaDTO> consultaPorNome(String nome) throws PatologiaExceptions;

	public List<PatologiaDTO> listaGeral() throws PatologiaExceptions;
}
