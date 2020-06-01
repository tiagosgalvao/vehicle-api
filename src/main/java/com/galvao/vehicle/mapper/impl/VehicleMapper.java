package com.galvao.vehicle.mapper.impl;

import com.galvao.vehicle.mapper.BaseMapper;
import com.galvao.vehicle.mapper.config.BaseMapperConfig;
import com.galvao.vehicle.model.dto.impl.VehicleDto;
import com.galvao.vehicle.model.entity.impl.Vehicle;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public interface VehicleMapper extends BaseMapper<Vehicle, VehicleDto> {
	@Override
	VehicleDto entityToDto(Vehicle source);
}