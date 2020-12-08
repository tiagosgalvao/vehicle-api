package com.galvao.vehicle.service.impl;

import com.galvao.vehicle.mapper.impl.VehicleMapper;
import com.galvao.vehicle.model.dto.batch.VehicleFromFileBuilder;
import com.galvao.vehicle.model.dto.batch.VehicleFromFileDto;
import com.galvao.vehicle.model.dto.impl.VehicleDto;
import com.galvao.vehicle.model.entity.impl.Model;
import com.galvao.vehicle.model.entity.impl.Vehicle;
import com.galvao.vehicle.model.enums.Status;
import com.galvao.vehicle.repository.impl.ModelRepository;
import com.galvao.vehicle.repository.impl.VehicleRepository;
import com.galvao.vehicle.service.BaseService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VehicleService extends BaseService<Vehicle, VehicleDto, VehicleRepository, VehicleMapper> {
	private ModelRepository modelRepository;

	@Autowired
	public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper, ModelRepository modelRepository) {
		super(vehicleRepository, vehicleMapper);
		this.modelRepository = modelRepository;
	}

	public void batchCreate(MultipartFile file) {
		List<Vehicle> vehicles = new ArrayList<>();
		Map<Long, Model> models = new HashMap<>();
		try (InputStream is = file.getInputStream(); BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			while (br.ready()) {
				String line = br.readLine();
				VehicleFromFileDto vehicleFromFile = getVehicleFromFile(line);
				if (Objects.nonNull(vehicleFromFile.getModel())) {
					Vehicle vehicle = new Vehicle();
					BeanUtils.copyProperties(vehicle, vehicleFromFile);
					Model model = models.get(vehicleFromFile.getModel());
					if (Objects.isNull(model)) {
						model = modelRepository.findOneByIdAndStatus(vehicleFromFile.getModel(), Status.ACTIVE);
						models.put(model.getId(), model);
					}
					vehicle.setModel(model);
					vehicles.add(vehicle);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Error parsing file.");
		}
		repository.saveAll(vehicles);
	}

	private VehicleFromFileDto getVehicleFromFile(String line) {
		try {
			return VehicleFromFileBuilder.builder().buildFromLine(line).build();
		} catch (Exception e) {
			log.error(String.format("Error parsing line: %s", line.substring(0, Math.min(line.length(), 20))));
		}
		return new VehicleFromFileDto();
	}
}