package com.galvao.vehicle.model.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galvao.vehicle.model.entity.BaseEntity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "manufacturer")
public class Manufacturer extends BaseEntity {
	@JsonIgnore
	@OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
	private List<Model> models;
	@Column
	private String name;
}