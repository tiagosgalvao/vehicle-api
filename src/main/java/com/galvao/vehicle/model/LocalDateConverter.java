package com.galvao.vehicle.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Timestamp> {
	@Override
	public Timestamp convertToDatabaseColumn(LocalDate attribute) {
		return Optional.ofNullable(attribute)
				.map(d -> LocalDateTime.of(d, LocalTime.MIDNIGHT))
				.map(Timestamp::valueOf)
				.orElse(null);
	}

	@Override
	public LocalDate convertToEntityAttribute(Timestamp dbData) {
		return Optional.ofNullable(dbData)
				.map(Timestamp::toLocalDateTime)
				.map(LocalDateTime::toLocalDate)
				.orElse(null);
	}
}
