package com.missingplatform.obdapi.Models;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Map;

public class ProcessedMessage {
	@Id
	private String id;
	private String driveId;
	private Date timestamp;
	private Map<String, Object> values;

	public ProcessedMessage() {}

	public ProcessedMessage(Map<String, Object> values, String driveId) {
		this.values = values;
		this.timestamp = new Date();
		this.driveId = driveId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDriveId() {
		return driveId;
	}

	public void setDriveId(String driveId) {
		this.driveId = driveId;
	}

	public Map<String, Object> getValues() {
		return values;
	}

	public void setValues(Map<String, Object> values) {
		this.values = values;
	}
}
