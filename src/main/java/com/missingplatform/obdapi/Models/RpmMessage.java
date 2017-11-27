package com.missingplatform.obdapi.Models;

import java.util.Date;

public class RpmMessage extends ProcessedMessage {
	private int value;

	public RpmMessage(Date timestamp, int rpm) {
		super(timestamp, "RPM");
		this.value = rpm;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
