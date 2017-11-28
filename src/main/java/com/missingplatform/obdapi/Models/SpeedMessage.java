package com.missingplatform.obdapi.Models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "messages")
public class SpeedMessage extends ProcessedMessage {
	private int speed;

	public SpeedMessage() {

	}

	public SpeedMessage(Date timestamp, int speed) {
		super(timestamp, "SPEED");
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
