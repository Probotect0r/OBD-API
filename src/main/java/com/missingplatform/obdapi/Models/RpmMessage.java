package com.missingplatform.obdapi.Models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

public class RpmMessage extends ProcessedMessage {
	private int rpm;

	public RpmMessage() {

	}

	public RpmMessage(Date timestamp, int rpm) {
		super(timestamp, "RPM");
		this.rpm = rpm;
	}

	public int getRpm() {
		return rpm;
	}

	public void setRpm(int rpm) {
		this.rpm = rpm;
	}
}
