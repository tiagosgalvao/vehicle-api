package com.galvao.vehicle.model.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galvao.vehicle.model.entity.BaseEntity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "model")
public class Model extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "manufacturer_id", referencedColumnName = "id", nullable = false)
	private Manufacturer manufacturer;

	@JsonIgnore
	@OneToMany(mappedBy = "model", fetch = FetchType.LAZY)
	private List<Vehicle> vehicles;

	@Column
	private String name;
}