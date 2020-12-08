package com.galvao.vehicle.service;

import com.galvao.vehicle.mapper.BaseMapper;
import com.galvao.vehicle.model.dto.BaseDto;
import com.galvao.vehicle.model.entity.BaseEntity;
import com.galvao.vehicle.repository.BaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

import static com.galvao.vehicle.model.enums.Status.ACTIVE;
import static com.galvao.vehicle.model.enums.Status.INACTIVE;

@Transactional
@AllArgsConstructor
public abstract class BaseService<E extends BaseEntity, D extends BaseDto, R extends BaseRepository<E>, M extends BaseMapper<E, D>> {

	protected M mapper;
	protected R repository;
	protected Class<E> clazz;

	public D create(final D dto) throws Throwable {
		E entity = repository.findById(dto.getId()).orElse(clazz.getDeclaredConstructor().newInstance());
		mapper.dtoToEntity(dto, entity);
		return mapper.entityToDto(repository.save(entity));
	}

	public void deleteById(final Long id) {
		repository.findOneByIdAndStatus(id, ACTIVE).map(e -> {
			e.setStatus(INACTIVE);
			repository.save(e);
			return e;
		});
	}

	@Transactional(readOnly = true)
	public Page<D> findAll(Pageable pageable) {
		return mapper.entitiesToDtos(repository.findAllByStatus(ACTIVE, pageable));
	}

	@Transactional(readOnly = true)
	public D findOneById(final Long id) {
		return repository.findOneByIdAndStatus(id, ACTIVE)
				.map(e -> mapper.entityToDto(e))
				.orElse(null);
	}

	public D updateById(final Long id, final D d) {
		return repository.findOneByIdAndStatus(id, ACTIVE)
				.map(e -> {
					mapper.dtoToEntity(d, e);
					repository.save(e);
					return mapper.entityToDto(e);
				}).orElse(null);
	}

}