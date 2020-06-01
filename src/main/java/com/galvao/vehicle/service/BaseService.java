package com.galvao.vehicle.service;

import com.galvao.vehicle.mapper.BaseMapper;
import com.galvao.vehicle.model.dto.BaseDto;
import com.galvao.vehicle.model.entity.BaseEntity;
import com.galvao.vehicle.repository.BaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static com.galvao.vehicle.model.enums.Status.ACTIVE;
import static com.galvao.vehicle.model.enums.Status.INACTIVE;

@Transactional
public abstract class BaseService<E extends BaseEntity, D extends BaseDto<E>, R extends BaseRepository<E>, M extends BaseMapper<E, D>> {

	protected M mapper;
	protected R repository;
	private Class<E> clazz;

	@Autowired
	public BaseService(Class<E> clazz, R repository, M mapper) {
		this.clazz = clazz;
		this.repository = repository;
		this.mapper = mapper;
	}

	public D create(final D dto) throws Exception {
		E entity = repository.findOne(dto.getSpecification()).orElse(clazz.getDeclaredConstructor().newInstance());
		mapper.dtoToEntity(dto, entity);
		return mapper.entityToDto(repository.save(entity));
	}

	public void deleteById(final Long id) {
		final E entity = repository.findOneByIdAndStatus(id, ACTIVE);
		if (entity != null) {
			entity.setStatus(INACTIVE);
		}
	}

	@Transactional(readOnly = true)
	public Page<D> findAll(Pageable pageable) {
		return mapper.entitiesToDtos(repository.findAllByStatus(ACTIVE, pageable));
	}

	@Transactional(readOnly = true)
	public D findOneById(final Long id) {
		return mapper.entityToDto(repository.findOneByIdAndStatus(id, ACTIVE));
	}

	public void updateById(final Long id, final D d) {
		final E entity = repository.findOneByIdAndStatus(id, ACTIVE);
		if (entity != null) {
			mapper.dtoToEntity(d, entity);
		}
	}

}