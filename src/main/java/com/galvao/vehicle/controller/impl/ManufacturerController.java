package com.galvao.vehicle.controller.impl;

import com.galvao.vehicle.controller.BaseController;
import com.galvao.vehicle.mapper.impl.ManufacturerMapper;
import com.galvao.vehicle.model.dto.impl.ManufacturerDto;
import com.galvao.vehicle.model.entity.impl.Manufacturer;
import com.galvao.vehicle.repository.impl.ManufacturerRepository;
import com.galvao.vehicle.service.impl.ManufacturerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Manufacturers")
@RestController
@RequestMapping("v1/manufacturers")
public class ManufacturerController extends BaseController<Manufacturer, ManufacturerDto, ManufacturerRepository, ManufacturerMapper, ManufacturerService> {
	public ManufacturerController(ManufacturerService service) {
		super(service);
	}
}