package com.galvao.vehicle.config.logging.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestLog implements Serializable {
	private String uri;
	private String method;
	private Map<String, List<String>> headers;
	private String body;
	private String logbookCid;
}