package com.missingplatform.obdapi.Models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

public class LoadMessage extends ProcessedMessage {
	private int load;

	public LoadMessage() {

	}

	public LoadMessage(Date timestamp, int load) {
		super(timestamp, "ENGINE_LOAD");
		this.load = load;
	}

	public int getLoad() {
		return load;
	}

	public void setLoad(int load) {
		this.load = load;
	}
}
