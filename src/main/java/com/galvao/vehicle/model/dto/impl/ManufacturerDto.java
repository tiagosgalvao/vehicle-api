package com.galvao.vehicle.model.dto.impl;

import com.galvao.vehicle.model.dto.BaseDto;
import com.galvao.vehicle.model.entity.impl.Manufacturer;

import org.springframework.data.jpa.domain.Specification;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ManufacturerDto extends BaseDto<Manufacturer> {
	@NotNull
	private String name;

	@Override
	public Specification<Manufacturer> getSpecification() {
		return (root, query, cb) -> cb.equal(root.get("id"), super.getId());
	}
}