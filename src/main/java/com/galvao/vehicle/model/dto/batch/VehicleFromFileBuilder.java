package com.galvao.vehicle.model.dto.batch;

import java.util.Arrays;
import java.util.List;

public class VehicleFromFileBuilder {
	private Long model;
	private String plaque;
	private Integer year;
	private Boolean abs;
	private Boolean armored;
	private Boolean airbag;

	public static VehicleFromFileBuilder builder() {
		return new VehicleFromFileBuilder();
	}

	public VehicleFromFileDto build() {
		return VehicleFromFileDto.builder().model(model).plaque(plaque).year(year).abs(abs).armored(armored).airbag(airbag).build();
	}

	public VehicleFromFileBuilder buildFromLine(String line) {
		String[] strFrom = line.split(",");
		List<String> fields = Arrays.asList(strFrom);
		this.model = Long.valueOf(fields.get(0));
		this.plaque = fields.get(1);
		this.year = Integer.valueOf(fields.get(2));
		this.abs = Boolean.valueOf(fields.get(3));
		this.armored = Boolean.valueOf(fields.get(4));
		this.airbag = Boolean.valueOf(fields.get(5));
		return this;
	}

}