package com.galvao.vehicle.mapper.config;

import com.galvao.vehicle.model.dto.BaseDto;
import com.galvao.vehicle.model.entity.BaseEntity;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG;
import static org.mapstruct.ReportingPolicy.WARN;

@MapperConfig(unmappedTargetPolicy = WARN, mappingInheritanceStrategy = AUTO_INHERIT_ALL_FROM_CONFIG)
public interface BaseMapperConfig {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createDate", ignore = true)
	@Mapping(target = "updateDate", ignore = true)
	@Mapping(target = "status", constant = "ACTIVE")
	void dtoToEntity(BaseDto source, @MappingTarget BaseEntity target);
}