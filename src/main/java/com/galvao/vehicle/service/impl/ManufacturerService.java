package com.galvao.vehicle.service.impl;

import com.galvao.vehicle.mapper.impl.ManufacturerMapper;
import com.galvao.vehicle.model.dto.impl.ManufacturerDto;
import com.galvao.vehicle.model.entity.impl.Manufacturer;
import com.galvao.vehicle.repository.impl.ManufacturerRepository;
import com.galvao.vehicle.service.BaseService;

import org.springframework.stereotype.Service;

@Service
public class ManufacturerService extends BaseService<Manufacturer, ManufacturerDto, ManufacturerRepository, ManufacturerMapper> {
	public ManufacturerService(ManufacturerRepository manufacturerRepository, ManufacturerMapper manufacturerMapper) {
		super(manufacturerRepository, manufacturerMapper);
	}
}