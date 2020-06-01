package com.galvao.vehicle.mapper;

import com.galvao.vehicle.model.dto.BaseDto;
import com.galvao.vehicle.model.entity.BaseEntity;

import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto<E>> {

	void dtoToEntity(D source, @MappingTarget E target);

	default Page<D> entitiesToDtos(final Page<E> source) {
		return source.map(this::entityToDto);
	}

	D entityToDto(E source);
}