package com.galvao.vehicle.config.logging.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvao.vehicle.config.logging.model.RequestLog;
import com.galvao.vehicle.config.logging.model.ResponseLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpLogWriter;
import org.zalando.logbook.Precorrelation;

import java.io.IOException;

/**
 * For any kind of modifications, please refer to docs below : https://github.com/zalando/logbook
 */
@Order
public class LogInterceptor implements HttpLogWriter {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void writeRequest(Precorrelation<String> precorrelation) throws IOException {
		RequestLog requestInfoLog = RequestLog.builder().uri(precorrelation.getOriginalRequest().getRequestUri())
				.method(precorrelation.getOriginalRequest().getMethod())
				.headers(precorrelation.getOriginalRequest().getHeaders())
				.logbookCid(precorrelation.getId()).build();
		LogInterceptor.LOGGER.info(objectMapper.writeValueAsString(requestInfoLog));
		RequestLog requestDebugLog = RequestLog.builder().uri(precorrelation.getOriginalRequest().getRequestUri())
				.method(precorrelation.getOriginalRequest().getMethod())
				.headers(precorrelation.getOriginalRequest().getHeaders())
				.body(precorrelation.getOriginalRequest().getBodyAsString())
				.logbookCid(precorrelation.getId()).build();
		LogInterceptor.LOGGER.debug(objectMapper.writeValueAsString(requestDebugLog));
	}

	@Override
	public void writeResponse(Correlation<String, String> correlation) throws IOException {
		ResponseLog responseInfoLog = ResponseLog.builder()
				.origin(correlation.getOriginalResponse().getOrigin())
				.status(correlation.getOriginalResponse().getStatus())
				.headers(correlation.getOriginalRequest().getHeaders())
				.logbookCid(correlation.getId()).build();
		LogInterceptor.LOGGER.info(objectMapper.writeValueAsString(responseInfoLog));
		ResponseLog responseDebugLog = ResponseLog.builder()
				.origin(correlation.getOriginalResponse().getOrigin())
				.status(correlation.getOriginalResponse().getStatus())
				.headers(correlation.getOriginalRequest().getHeaders())
				.body(correlation.getOriginalResponse().getBodyAsString())
				.logbookCid(correlation.getId()).build();
		LogInterceptor.LOGGER.debug(objectMapper.writeValueAsString(responseDebugLog));
	}
}