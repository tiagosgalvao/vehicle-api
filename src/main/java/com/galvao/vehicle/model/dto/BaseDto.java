package com.galvao.vehicle.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galvao.vehicle.model.entity.BaseEntity;
import com.galvao.vehicle.model.enums.Status;

import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public abstract class BaseDto<E extends BaseEntity> implements Serializable {
	private Long id;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	private Status status;

	@JsonIgnore
	public abstract Specification<E> getSpecification();
}
