package com.missingplatform.obdapi.Models;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Map;

public class ProcessedMessage {
	@Id
	private String id;
	private Date timestamp;
	private String type;
	private Map<String, Object> values;

	public ProcessedMessage() {}

	public ProcessedMessage(Date timestamp, String type) {
		this.timestamp = timestamp;
		this.type = type;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
