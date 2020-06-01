package com.galvao.vehicle.model.dto.batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleFromFileDto {
	private Long model;
	private String plaque;
	private Integer year;
	private Boolean abs;
	private Boolean armored;
	private Boolean airbag;
}