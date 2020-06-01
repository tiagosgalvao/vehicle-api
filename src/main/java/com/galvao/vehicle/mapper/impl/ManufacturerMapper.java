package com.galvao.vehicle.mapper.impl;

import com.galvao.vehicle.mapper.BaseMapper;
import com.galvao.vehicle.mapper.config.BaseMapperConfig;
import com.galvao.vehicle.model.dto.impl.ManufacturerDto;
import com.galvao.vehicle.model.entity.impl.Manufacturer;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public interface ManufacturerMapper extends BaseMapper<Manufacturer, ManufacturerDto> {
	@Override
	ManufacturerDto entityToDto(Manufacturer source);
}