package com.galvao.vehicle.controller;

import com.galvao.vehicle.mapper.BaseMapper;
import com.galvao.vehicle.model.dto.BaseDto;
import com.galvao.vehicle.model.entity.BaseEntity;
import com.galvao.vehicle.repository.BaseRepository;
import com.galvao.vehicle.service.BaseService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@AllArgsConstructor
public abstract class BaseController<E extends BaseEntity, D extends BaseDto, R extends BaseRepository<E>, M extends BaseMapper<E, D>, S extends BaseService<E, D, R, M>> {

	protected S service;

	@Operation(description = "Create a new entity")
	@PostMapping
	@ResponseStatus(CREATED)
	public D create(@RequestBody @Valid final D dto) throws Throwable {
		return service.create(dto);
	}

	@Operation(description = "Update entity by id")
	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable final Long id) {
		service.deleteById(id);
	}

	@Operation(description = "Find all entities")
	@GetMapping
	public Page<D> findAll(@Parameter(hidden = true) Pageable pageable) {
		return service.findAll(pageable);
	}

	@Operation(description = "Update entity by id")
	@GetMapping("/{id}")
	public D findById(@PathVariable final Long id) {
		return service.findOneById(id);
	}

	@Operation(description = "Update entity by id")
	@PutMapping("/{id}")
	public D update(@PathVariable final Long id, @RequestBody @Valid final D dto) {
		return service.updateById(id, dto);
	}

}