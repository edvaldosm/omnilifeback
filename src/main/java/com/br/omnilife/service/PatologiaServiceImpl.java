package com.br.omnilife.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.omnilife.domain.Patologia;
import com.br.omnilife.dto.PatologiaDTO;
import com.br.omnilife.exceptions.PatologiaExceptions;
import com.br.omnilife.mapper.MapperGeneric;
import com.br.omnilife.repository.PatologiaRepository;

@Service
public class PatologiaServiceImpl implements PatologiaService {

	@Autowired
	private PatologiaRepository repository;

	@Autowired
	private MapperGeneric<Patologia, PatologiaDTO> mapper;

	@Override
	public PatologiaDTO inserir(PatologiaDTO patologia) throws PatologiaExceptions {
		try {
			Patologia entity = mapper.convertToEntity(patologia, Patologia.class);
			return mapper.convertToDto(repository.save(entity), PatologiaDTO.class);

		} catch (Exception e) {
			throw new PatologiaExceptions().inserirPatologia(e);
		}
	}

	@Override
	public PatologiaDTO atualizar(PatologiaDTO dto, Integer id) throws PatologiaExceptions {
		try {
			Patologia entity = repository.findById(id).get();
			entity = mapper.convertToEntity(dto, Patologia.class);
			return mapper.convertToDto(repository.save(entity), PatologiaDTO.class);

		} catch (Exception e) {
			throw new PatologiaExceptions().atualizarPatologia(e);
		}
	}

	@Override
	public PatologiaDTO consultaID(Integer id) throws PatologiaExceptions {
		try {
			return mapper.convertToDto(repository.findById(id).get(), PatologiaDTO.class);

		} catch (IllegalArgumentException e) {
			throw new PatologiaExceptions().consultarIdPatologia(e);
		} catch (NoSuchElementException e) {
			throw new PatologiaExceptions().registroNaoEncontrado(id.toString());
		} catch (Exception e) {
			throw new PatologiaExceptions().errorInterno("consultaID", e);
		}

	}

	@Override
	public List<PatologiaDTO> consultaPorNome(String nome) throws PatologiaExceptions {
		try {
			Optional<List<Patologia>> listaEntity = repository.consultaPorNome(nome);
			if (listaEntity.isPresent()) {
				return listaEntity.get().stream().map(c -> mapper.convertToDto(c, PatologiaDTO.class))
						.collect(Collectors.toList());
			}
			throw new PatologiaExceptions().registroNaoEncontrado(nome);
		} catch (PatologiaExceptions e) {
			throw e;
		} catch (Exception e) {
			throw new PatologiaExceptions().errorInterno("consultaPorNome", e);
		}

	}

	@Override
	public List<PatologiaDTO> listaGeral() throws PatologiaExceptions {
		List<PatologiaDTO> lista = StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(c -> mapper.convertToDto(c, PatologiaDTO.class)).collect(Collectors.toList());
		if (!lista.isEmpty()) {
			return lista;
		}
		throw new PatologiaExceptions().registroNaoEncontrado(null);
	}

}
