package com.missingplatform.obdapi.Models;

import java.util.Map;

public class RawMessage {
	private String driveId;
	private Map<String, String> rawMessages;

	public RawMessage() {}

	public Map<String, String> getRawMessages() {
		return rawMessages;
	}

	public void setRawMessages(Map<String, String> rawMessages) {
		this.rawMessages = rawMessages;
	}

	public String getDriveId() {
		return driveId;
	}

	public void setDriveId(String driveId) {
		this.driveId = driveId;
	}

	@Override
	public String toString() {
		return "RawMessage{" +
				"driveId='" + driveId + '\'' +
				", rawMessages=" + rawMessages +
				'}';
	}
}
