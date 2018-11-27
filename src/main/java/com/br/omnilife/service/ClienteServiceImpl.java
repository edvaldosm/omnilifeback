package com.br.omnilife.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.omnilife.domain.Cliente;
import com.br.omnilife.dto.ClienteDTO;
import com.br.omnilife.dto.ClienteNovoDTO;
import com.br.omnilife.exceptions.ClienteExceptions;
import com.br.omnilife.mapper.MapperGeneric;
import com.br.omnilife.repository.ClienteRepository;


@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private MapperGeneric<Cliente, ClienteDTO> mapper;
	
	@Autowired
	private MapperGeneric<Cliente, ClienteNovoDTO> mapperNovo;

	@Override
	public ClienteNovoDTO inserir(ClienteNovoDTO cliente) throws ClienteExceptions {
		try {
			Cliente entity = mapperNovo.convertToEntity(cliente, Cliente.class);
			return mapperNovo.convertToDto(repository.save(entity), ClienteNovoDTO.class);

		} catch (Exception e) {
			throw new ClienteExceptions().inserirCliente(e);
		}
	}

	@Override
	public ClienteDTO atualizar(ClienteDTO dto, Integer id) throws ClienteExceptions {
		try {
			Cliente entity = repository.findById(id).get();
			entity = mapper.convertToEntity(dto, Cliente.class);
			return mapper.convertToDto(repository.save(entity), ClienteDTO.class);

		} catch (Exception e) {
			throw new ClienteExceptions().atualizarCliente(e);
		}
	}

	@Override
	public ClienteDTO consultaID(Integer id) throws ClienteExceptions {
		try {
			return mapper.convertToDto(repository.findById(id).get(), ClienteDTO.class);

		} catch (IllegalArgumentException e) {
			throw new ClienteExceptions().consultarIdCliente(e);
		} catch (NoSuchElementException e) {
			throw new ClienteExceptions().registroNaoEncontrado(id.toString());
		} catch (Exception e) {
			throw new ClienteExceptions().errorInterno("consultaID", e);
		}

	}

	@Override
	public List<ClienteDTO> listaGeral() throws ClienteExceptions {
		List<ClienteDTO> lista = StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(c -> mapper.convertToDto(c, ClienteDTO.class)).collect(Collectors.toList());
		if (!lista.isEmpty()) {
			return lista;
		}
		throw new ClienteExceptions().registroNaoEncontrado(null);
	}
}
