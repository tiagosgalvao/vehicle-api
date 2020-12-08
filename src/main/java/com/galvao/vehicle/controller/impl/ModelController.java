package com.galvao.vehicle.controller.impl;

import com.galvao.vehicle.controller.BaseController;
import com.galvao.vehicle.mapper.impl.ModelMapper;
import com.galvao.vehicle.model.dto.impl.ModelDto;
import com.galvao.vehicle.model.entity.impl.Model;
import com.galvao.vehicle.repository.impl.ModelRepository;
import com.galvao.vehicle.service.impl.ModelService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Models")
@RestController
@RequestMapping("v1/models")
public class ModelController extends BaseController<Model, ModelDto, ModelRepository, ModelMapper, ModelService> {
	public ModelController(ModelService service) {
		super(service);
	}
}