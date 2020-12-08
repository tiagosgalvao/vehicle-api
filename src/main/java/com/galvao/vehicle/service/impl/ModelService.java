package com.galvao.vehicle.service.impl;

import com.galvao.vehicle.mapper.impl.ModelMapper;
import com.galvao.vehicle.model.dto.impl.ModelDto;
import com.galvao.vehicle.model.entity.impl.Model;
import com.galvao.vehicle.repository.impl.ModelRepository;
import com.galvao.vehicle.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService extends BaseService<Model, ModelDto, ModelRepository, ModelMapper> {
	@Autowired
	public ModelService(ModelRepository modelRepository, ModelMapper modelMapper) {
		super(modelMapper, modelRepository, Model.class);
	}
}