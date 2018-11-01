package com.br.omnilife.service;

import java.util.List;

import com.br.omnilife.dto.MedidaCorporalDTO;
import com.br.omnilife.exceptions.MedidaCorporalExceptions;

public interface MedidaCorporalService {

	public MedidaCorporalDTO inserir(MedidaCorporalDTO medida) throws MedidaCorporalExceptions;;

	public MedidaCorporalDTO atualizar(MedidaCorporalDTO medida, Integer id) throws MedidaCorporalExceptions;;

	public MedidaCorporalDTO consultaID(Integer id) throws MedidaCorporalExceptions;;

	public List<MedidaCorporalDTO> listaGeral() throws MedidaCorporalExceptions;
}
