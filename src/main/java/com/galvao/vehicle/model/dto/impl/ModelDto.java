package com.galvao.vehicle.model.dto.impl;

import com.galvao.vehicle.model.dto.BaseDto;
import com.galvao.vehicle.model.entity.impl.Model;

import org.springframework.data.jpa.domain.Specification;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ModelDto extends BaseDto<Model> {
	@NotNull
	private ManufacturerDto manufacturer;
	@NotNull
	private String name;

	@Override
	public Specification<Model> getSpecification() {
		return (root, query, cb) -> cb.equal(root.get("id"), super.getId());
	}
}