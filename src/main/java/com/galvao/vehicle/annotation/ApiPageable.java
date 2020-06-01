package com.galvao.vehicle.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, ANNOTATION_TYPE, TYPE})
@Retention(RUNTIME)
@Parameter(in = QUERY, name = "page", description = "Results page you want to retrieve. (default: 0)",
		content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
@Parameter(in = QUERY, name = "size", description = "Number of records per page. (default: 20)",
		content = @Content(schema = @Schema(type = "integer", defaultValue = "20")))
@Parameter(in = QUERY, name = "sort", description = "Sorting criteria in the format: property(,asc|desc).",
		content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))))
public @interface ApiPageable {
}