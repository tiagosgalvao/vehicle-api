package com.galvao.vehicle.model.entity.impl;

import com.galvao.vehicle.model.entity.BaseEntity;
import com.galvao.vehicle.model.enums.Colour;
import com.galvao.vehicle.model.enums.Fuel;

import org.hibernate.annotations.Type;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.galvao.vehicle.model.enums.Colour.BLACK;
import static com.galvao.vehicle.model.enums.Fuel.GASOLINE;
import static java.math.BigDecimal.ZERO;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicle")
public class Vehicle extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "model_id", referencedColumnName = "id", nullable = false)
	private Model model;
	@Column(nullable = false)
	@Builder.Default
	private Boolean abs = false;
	@Column(nullable = false)
	@Builder.Default
	private Boolean armored = false;
	@Column(nullable = false)
	@Builder.Default
	private Boolean airbag = false;
	@Column(nullable = false)
	@Builder.Default
	private Boolean alarm = false;
	@Column(name = "air_conditioning")
	@Builder.Default
	private Boolean airConditioning = false;
	@Column(nullable = false)
	@Builder.Default
	private Boolean automatic = false;
	@Column(nullable = false)
	@Builder.Default
	private Boolean bluetooth = false;
	@Column(name = "cd_player", nullable = false)
	@Builder.Default
	private Boolean cdPlayer = false;
	@Column(nullable = false)
	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Type(type = "pg_enum")
	private Colour color = BLACK;
	@Column(name = "eletric_steering")
	@Builder.Default
	private Boolean eletricSteering = false;
	@Column(nullable = false)
	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Type(type = "pg_enum")
	private Fuel fuel = GASOLINE;
	@Column(name = "hydraulic_steering", nullable = false)
	@Builder.Default
	private Boolean hydraulicSteering = false;
	@Column(nullable = false)
	@Builder.Default
	private Boolean gps = false;
	@Column(nullable = false)
	@Builder.Default
	private BigDecimal km = ZERO;
	@Column(name = "leather_seat", nullable = false)
	@Builder.Default
	private Boolean leatherSeat = false;
	@Column(nullable = false)
	@Builder.Default
	private Boolean mp3Player = false;
	@Column(nullable = false)
	private String plaque;
	@Column(name = "qt_doors", nullable = false)
	@Builder.Default
	private Integer qtDoors = 2;
	@Column(nullable = false)
	@Builder.Default
	private Boolean radio = false;
	@Column(nullable = false)
	private Integer year;
}