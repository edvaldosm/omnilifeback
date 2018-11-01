package com.br.omnilife.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.omnilife.domain.Sintoma;
import com.br.omnilife.dto.SintomaDTO;
import com.br.omnilife.exceptions.SintomasExceptions;
import com.br.omnilife.mapper.MapperGeneric;
import com.br.omnilife.repository.SintomasRepository;

@Service
public class SintomasServiceImpl implements SintomasService {

	@Autowired
	private SintomasRepository repository;

	@Autowired
	private MapperGeneric<Sintoma, SintomaDTO> mapper;

	@Override
	public SintomaDTO inserir(SintomaDTO sintoma) throws SintomasExceptions {
		try {
			Sintoma entity = mapper.convertToEntity(sintoma, Sintoma.class);
			return mapper.convertToDto(repository.save(entity), SintomaDTO.class);

		} catch (Exception e) {
			throw new SintomasExceptions().inserirSintoma(e);
		}
	}

	@Override
	public SintomaDTO atualizar(SintomaDTO sintoma, Integer id) throws SintomasExceptions {
		try {
			Sintoma entity = repository.findById(id).get();
			entity = mapper.convertToEntity(sintoma, Sintoma.class);
			return mapper.convertToDto(repository.save(entity), SintomaDTO.class);

		} catch (Exception e) {
			throw new SintomasExceptions().atualizarSintoma(e);
		}
	}

	@Override
	public SintomaDTO consultaID(Integer id) throws SintomasExceptions {
		try {
			return mapper.convertToDto(repository.findById(id).get(), SintomaDTO.class);

		} catch (IllegalArgumentException e) {
			throw new SintomasExceptions().consultarIdSintoma(e);
		} catch (NoSuchElementException e) {
			throw new SintomasExceptions().registroNaoEncontrado(id.toString());
		} catch (Exception e) {
			throw new SintomasExceptions().errorInterno("consultaID", e);
		}

	}

	@Override
	public List<SintomaDTO> listaGeral() throws SintomasExceptions {
		List<SintomaDTO> lista = StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(c -> mapper.convertToDto(c, SintomaDTO.class)).collect(Collectors.toList());
		if (!lista.isEmpty()) {
			return lista;
		}
		throw new SintomasExceptions().registroNaoEncontrado(null);
	}

}
