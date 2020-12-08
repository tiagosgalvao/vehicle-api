package com.galvao.vehicle.controller.impl;

import com.galvao.vehicle.controller.BaseController;
import com.galvao.vehicle.mapper.impl.VehicleMapper;
import com.galvao.vehicle.model.dto.impl.VehicleDto;
import com.galvao.vehicle.model.entity.impl.Vehicle;
import com.galvao.vehicle.repository.impl.VehicleRepository;
import com.galvao.vehicle.service.impl.VehicleService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Tag(name = "Vehicles")
@RestController
@RequestMapping("v1/vehicles")
public class VehicleController extends BaseController<Vehicle, VehicleDto, VehicleRepository, VehicleMapper, VehicleService> {
	public VehicleController(VehicleService service) {
		super(service);
	}

	@PostMapping(value = "/batch", consumes = MULTIPART_FORM_DATA_VALUE)
	public void upload(@RequestPart @RequestParam MultipartFile file) {
		this.service.batchCreate(file);
	}
}