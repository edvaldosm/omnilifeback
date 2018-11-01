package com.br.omnilife.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.omnilife.domain.Suplementos;
import com.br.omnilife.dto.SuplementosDTO;
import com.br.omnilife.exceptions.SuplementosExceptions;
import com.br.omnilife.mapper.MapperGeneric;
import com.br.omnilife.repository.SuplementosRepository;

@Service
public class SuplementosServiceImpl implements SuplementosService {

	@Autowired
	private SuplementosRepository repository;

	@Autowired
	private MapperGeneric<Suplementos, SuplementosDTO> mapper;

	@Override
	public SuplementosDTO inserir(SuplementosDTO suplementos) throws SuplementosExceptions {
		try {
			Suplementos entity = mapper.convertToEntity(suplementos, Suplementos.class);
			return mapper.convertToDto(repository.save(entity), SuplementosDTO.class);

		} catch (Exception e) {
			throw new SuplementosExceptions().inserirSuplementos(e);
		}
	}

	@Override
	public SuplementosDTO atualizar(SuplementosDTO suplementos, Integer id) throws SuplementosExceptions {
		try {
			Suplementos entity = repository.findById(id).get();
			entity = mapper.convertToEntity(suplementos, Suplementos.class);
			return mapper.convertToDto(repository.save(entity), SuplementosDTO.class);

		} catch (Exception e) {
			throw new SuplementosExceptions().atualizarSuplementos(e);
		}
	}

	@Override
	public SuplementosDTO consultaID(Integer id) throws SuplementosExceptions {
		try {
			return mapper.convertToDto(repository.findById(id).get(), SuplementosDTO.class);

		} catch (IllegalArgumentException e) {
			throw new SuplementosExceptions().consultarIdSuplementos(e);
		} catch (NoSuchElementException e) {
			throw new SuplementosExceptions().registroNaoEncontrado(id.toString());
		} catch (Exception e) {
			throw new SuplementosExceptions().errorInterno("consultaID", e);
		}

	}

	@Override
	public List<SuplementosDTO> consultaPorNome(String descricao) throws SuplementosExceptions {
		try {
			Optional<List<Suplementos>> listaEntity = repository.consultaPorNome(descricao);
			if (listaEntity.isPresent()) {
				return listaEntity.get().stream().map(c -> mapper.convertToDto(c, SuplementosDTO.class))
						.collect(Collectors.toList());
			}
			throw new SuplementosExceptions().registroNaoEncontrado(descricao);
		} catch (SuplementosExceptions e) {
			throw e;
		} catch (Exception e) {
			throw new SuplementosExceptions().errorInterno("consultaPorNome", e);
		}

	}

	@Override
	public List<SuplementosDTO> listaGeral() throws SuplementosExceptions {
		List<SuplementosDTO> lista = StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(c -> mapper.convertToDto(c, SuplementosDTO.class)).collect(Collectors.toList());
		if (!lista.isEmpty()) {
			return lista;
		}
		throw new SuplementosExceptions().registroNaoEncontrado(null);
	}

}
