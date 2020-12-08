package com.galvao.vehicle.model.dto.impl;

import com.galvao.vehicle.model.dto.BaseDto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ManufacturerDto extends BaseDto {
	@NotNull
	private String name;
}