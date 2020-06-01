package com.galvao.vehicle.mapper.impl;

import com.galvao.vehicle.mapper.BaseMapper;
import com.galvao.vehicle.mapper.config.BaseMapperConfig;
import com.galvao.vehicle.model.dto.impl.ModelDto;
import com.galvao.vehicle.model.entity.impl.Model;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public interface ModelMapper extends BaseMapper<Model, ModelDto> {
	@Override
	ModelDto entityToDto(Model source);
}