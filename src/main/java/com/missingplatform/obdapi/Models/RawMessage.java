package com.missingplatform.obdapi.Models;

import java.util.Map;

public class RawMessage {
	private Map<String, String> rawMessages;

	public RawMessage() {}

	public Map<String, String> getRawMessages() {
		return rawMessages;
	}

	public void setRawMessages(Map<String, String> rawMessages) {
		this.rawMessages = rawMessages;
	}

	@Override
	public String toString() {
		return "RawMessage{" +
				"rawMessages=" + rawMessages +
				'}';
	}
}
