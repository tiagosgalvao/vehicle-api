package com.galvao.vehicle.model.entity;

import com.galvao.vehicle.model.LocalDateTimeConverter;
import com.galvao.vehicle.model.enums.PostgreSQLEnumType;
import com.galvao.vehicle.model.enums.Status;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@DynamicUpdate
@SuperBuilder
@TypeDef(name = "pg_enum", typeClass = PostgreSQLEnumType.class)
public abstract class BaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@CreationTimestamp
	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "CREATE_DATE", nullable = false)
	private LocalDateTime createDate;
	@UpdateTimestamp
	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "UPDATE_DATE", nullable = false)
	private LocalDateTime updateDate;
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	@Type(type = "pg_enum")
	private Status status = Status.ACTIVE;
}