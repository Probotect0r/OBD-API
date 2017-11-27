package com.missingplatform.obdapi.Models;

import java.util.Date;

public class ProcessedMessage {
	private Date timestamp;
	private String type;

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
}
