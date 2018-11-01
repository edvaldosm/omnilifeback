package com.br.omnilife.mapper;

import java.util.List;

import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperGeneric<Entity, DTO> {

	@Autowired
	ModelMapper mapper;

	/**
	 * 
	 * @param dto
	 * @param entity
	 * @return
	 */
	public Entity convertToEntity(DTO dto, Class<Entity> entity) {
		try {
			return mapper.map(dto, entity);
		} catch (IllegalArgumentException | ConfigurationException | MappingException e) {
			throw new RuntimeException("Erro no metodo convertToEntity -> ", e);
		}
	}

	/**
	 * 
	 * @param entity
	 * @param dto
	 * @return
	 */
	public DTO convertToDto(Entity entity, Class<DTO> dto) {
		try {
			return mapper.map(entity, dto);
		} catch (IllegalArgumentException | ConfigurationException | MappingException e) {
			throw new RuntimeException("Erro no metodo convertToDto -> ", e);
		}
	}

	
	/**
	 * 
	 * @param dto
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Entity> convertToListEntity(List<DTO> dto, Class<Entity> entity) {
		try {
			return (List<Entity>) mapper.map(dto, entity);
		} catch (IllegalArgumentException | ConfigurationException | MappingException e) {
			throw new RuntimeException("Erro no metodo convertToEntity -> ", e);
		}
	}

	/**
	 * 
	 * @param entity
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DTO> convertToListDto(List<Entity> entity, Class<DTO> dto) {
		try {
			return ((List<DTO>) mapper.map(entity, dto));
		} catch (IllegalArgumentException | ConfigurationException | MappingException e) {
			throw new RuntimeException("Erro no metodo convertToDto -> ", e);
		}
	}

}
