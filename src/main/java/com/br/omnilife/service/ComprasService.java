package com.br.omnilife.service;

import java.util.List;

import com.br.omnilife.dto.ComprasDTO;
import com.br.omnilife.exceptions.ComprasExceptions;

public interface ComprasService {

	public ComprasDTO inserir(ComprasDTO compras) throws ComprasExceptions;;

	public ComprasDTO atualizar(ComprasDTO compras, Integer id) throws ComprasExceptions;;

	public ComprasDTO consultaID(Integer id) throws ComprasExceptions;;

	public List<ComprasDTO> listaGeral() throws ComprasExceptions;
}
