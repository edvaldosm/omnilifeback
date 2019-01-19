package com.br.omnilife.service;

import java.util.List;

import com.br.omnilife.dto.ClienteDTO;
import com.br.omnilife.dto.ClienteNovoDTO;
import com.br.omnilife.exceptions.ClienteExceptions;

public interface ClienteService {

	public ClienteNovoDTO inserir(ClienteNovoDTO compras) throws ClienteExceptions;;

	public ClienteDTO atualizar(ClienteDTO compras, Integer id) throws ClienteExceptions;;

	public ClienteDTO consultaID(Integer id) throws ClienteExceptions;;

	public List<ClienteDTO> listaGeral() throws ClienteExceptions;
}
