package com.galvao.vehicle.config.logging.model;

import org.zalando.logbook.Origin;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseLog implements Serializable {
	private Origin origin;
	private int status;
	private Map<String, List<String>> headers;
	private String body;
	private String logbookCid;
}
