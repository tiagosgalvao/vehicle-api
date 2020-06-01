package com.galvao.vehicle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvao.vehicle.mapper.BaseMapper;
import com.galvao.vehicle.model.dto.BaseDto;
import com.galvao.vehicle.model.entity.BaseEntity;
import com.galvao.vehicle.repository.BaseRepository;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseControllerTest<E extends BaseEntity, D extends BaseDto<E>, R extends BaseRepository<E>, M extends BaseMapper<E, D>> {
	@Autowired
	protected ObjectMapper objectMapper;
	@Autowired
	protected R repository;
	@Autowired
	protected M mapper;
	protected String rootUrl;

	protected abstract E createRandomEntity();
}