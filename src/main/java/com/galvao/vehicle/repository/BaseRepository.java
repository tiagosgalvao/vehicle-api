package com.galvao.vehicle.repository;

import com.galvao.vehicle.model.entity.BaseEntity;
import com.galvao.vehicle.model.enums.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends PagingAndSortingRepository<E, Long>, JpaSpecificationExecutor<E> {
	Page<E> findAllByStatus(Status status, Pageable pageable);

	Optional<E> findOneByIdAndStatus(Long id, Status status);
}
