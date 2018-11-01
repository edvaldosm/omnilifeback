package com.br.omnilife.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.omnilife.domain.MedidaCorporal;
import com.br.omnilife.dto.MedidaCorporalDTO;
import com.br.omnilife.exceptions.MedidaCorporalExceptions;
import com.br.omnilife.mapper.MapperGeneric;
import com.br.omnilife.repository.MedidaCorporalRepository;

@Service
public class MedidaCorporalServiceImpl implements MedidaCorporalService {

	@Autowired
	private MedidaCorporalRepository repository;

	@Autowired
	private MapperGeneric<MedidaCorporal, MedidaCorporalDTO> mapper;

	@Override
	public MedidaCorporalDTO inserir(MedidaCorporalDTO medida) throws MedidaCorporalExceptions {
		try {
			MedidaCorporal entity = mapper.convertToEntity(medida, MedidaCorporal.class);
			return mapper.convertToDto(repository.save(entity), MedidaCorporalDTO.class);

		} catch (Exception e) {
			throw new MedidaCorporalExceptions().inserirMedidaCorporal(e);
		}
	}

	@Override
	public MedidaCorporalDTO atualizar(MedidaCorporalDTO dto, Integer id) throws MedidaCorporalExceptions {
		try {
			MedidaCorporal entity = repository.findById(id).get();
			entity = mapper.convertToEntity(dto, MedidaCorporal.class);
			return mapper.convertToDto(repository.save(entity), MedidaCorporalDTO.class);

		} catch (Exception e) {
			throw new MedidaCorporalExceptions().atualizarMedidaCorporal(e);
		}
	}

	@Override
	public MedidaCorporalDTO consultaID(Integer id) throws MedidaCorporalExceptions {
		try {
			return mapper.convertToDto(repository.findById(id).get(), MedidaCorporalDTO.class);

		} catch (IllegalArgumentException e) {
			throw new MedidaCorporalExceptions().consultarIdMedidaCorporal(e);
		} catch (NoSuchElementException e) {
			throw new MedidaCorporalExceptions().registroNaoEncontrado(id.toString());
		} catch (Exception e) {
			throw new MedidaCorporalExceptions().errorInterno("consultaID", e);
		}

	}

	@Override
	public List<MedidaCorporalDTO> listaGeral() throws MedidaCorporalExceptions {
		List<MedidaCorporalDTO> lista = StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(c -> mapper.convertToDto(c, MedidaCorporalDTO.class)).collect(Collectors.toList());
		if (!lista.isEmpty()) {
			return lista;
		}
		throw new MedidaCorporalExceptions().registroNaoEncontrado(null);
	}

}
