package com.galvao.vehicle.model.dto;

import com.galvao.vehicle.model.enums.Status;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public abstract class BaseDto implements Serializable {
	private Long id;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	private Status status;
}
