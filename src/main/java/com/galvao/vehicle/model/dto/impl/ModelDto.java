package com.galvao.vehicle.model.dto.impl;

import com.galvao.vehicle.model.dto.BaseDto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ModelDto extends BaseDto {
	@NotNull
	private ManufacturerDto manufacturer;
	@NotNull
	private String name;
}