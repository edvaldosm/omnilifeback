package com.br.omnilife.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.omnilife.domain.Compras;
import com.br.omnilife.dto.ComprasDTO;
import com.br.omnilife.exceptions.ComprasExceptions;
import com.br.omnilife.mapper.MapperGeneric;
import com.br.omnilife.repository.ComprasRepository;

@Service
public class ComprasServiceImpl implements ComprasService {

	@Autowired
	private ComprasRepository repository;

	@Autowired
	private MapperGeneric<Compras, ComprasDTO> mapper;

	@Override
	public ComprasDTO inserir(ComprasDTO medida) throws ComprasExceptions {
		try {
			Compras entity = mapper.convertToEntity(medida, Compras.class);
			return mapper.convertToDto(repository.save(entity), ComprasDTO.class);

		} catch (Exception e) {
			throw new ComprasExceptions().inserirCompras(e);
		}
	}

	@Override
	public ComprasDTO atualizar(ComprasDTO dto, Integer id) throws ComprasExceptions {
		try {
			Compras entity = repository.findById(id).get();
			entity = mapper.convertToEntity(dto, Compras.class);
			return mapper.convertToDto(repository.save(entity), ComprasDTO.class);

		} catch (Exception e) {
			throw new ComprasExceptions().atualizarCompras(e);
		}
	}

	@Override
	public ComprasDTO consultaID(Integer id) throws ComprasExceptions {
		try {
			return mapper.convertToDto(repository.findById(id).get(), ComprasDTO.class);

		} catch (IllegalArgumentException e) {
			throw new ComprasExceptions().consultarIdCompras(e);
		} catch (NoSuchElementException e) {
			throw new ComprasExceptions().registroNaoEncontrado(id.toString());
		} catch (Exception e) {
			throw new ComprasExceptions().errorInterno("consultaID", e);
		}

	}

	@Override
	public List<ComprasDTO> listaGeral() throws ComprasExceptions {
		List<ComprasDTO> lista = StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(c -> mapper.convertToDto(c, ComprasDTO.class)).collect(Collectors.toList());
		if (!lista.isEmpty()) {
			return lista;
		}
		throw new ComprasExceptions().registroNaoEncontrado(null);
	}

}
