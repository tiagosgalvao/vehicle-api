package com.galvao.vehicle.model.dto.impl;

import com.galvao.vehicle.model.dto.BaseDto;
import com.galvao.vehicle.model.entity.impl.Vehicle;
import com.galvao.vehicle.model.enums.Colour;
import com.galvao.vehicle.model.enums.Fuel;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VehicleDto extends BaseDto<Vehicle> {
	@NotNull
	private ModelDto model;
	private Boolean abs;
	private Boolean armored;
	private Boolean airbag;
	private Boolean alarm;
	private Boolean airConditioning;
	private Boolean automatic;
	private Boolean bluetooth;
	private Boolean cdPlayer;
	private Colour color;
	private Boolean eletricSteering;
	private Fuel fuel;
	private Boolean hydraulicSteering;
	private Boolean gps;
	private BigDecimal km;
	private Boolean leatherSeat;
	private Boolean mp3Player;
	@NotNull
	private String plaque;
	private Integer qtDoors;
	private Boolean radio;
	@NotNull
	private Integer year;

	@Override
	public Specification<Vehicle> getSpecification() {
		return (root, query, cb) -> cb.equal(root.get("id"), super.getId());
	}
}