package com.missingplatform.obdapi;

import com.missingplatform.obdapi.Models.Drive;
import com.missingplatform.obdapi.Models.ProcessedMessage;
import com.missingplatform.obdapi.Models.RawMessage;
import com.missingplatform.obdapi.Repositories.DriveRepository;
import com.missingplatform.obdapi.Repositories.ProcessedMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CreateService {

	@Autowired
	private ProcessedMessageRepository processedMessageRepository;

	@Autowired
	private DriveRepository driveRepository;

	public CreateService() {}

	public Drive createDrive() {
		Drive drive = new Drive();
		return this.driveRepository.save(drive);
	}

	public Drive endDrive(String driveId) {
		Drive drive = this.driveRepository.findOne(driveId);
		drive.setEnd(new Date());
		this.driveRepository.save(drive);

		return drive;
	}

	public void processMessages(RawMessage rawMessage) {
		Map<String, String> messages = rawMessage.getRawMessages();

		Map<String, Integer> processedValues = new HashMap<>();

		for (String key : messages.keySet()) {
			String value = messages.get(key);
			processedValues.put(key, processMessage(key, value));
		}

		System.out.println(processedValues);
		ProcessedMessage processedMessage = new ProcessedMessage(processedValues);

		this.processedMessageRepository.save(processedMessage);
	}

	private Integer processMessage(String key, String response) {
		String[] parts = response.split("\n");
		String command = parts[0].trim();
		String value = parts[1].trim();

		switch (key) {
			case "RPM":
				return calculateRPM(value);
			case "THROTTLE_POSITION":
				return calculateEngineLoad(value);
			case "SPEED":
				return calculateSpeed(value);
			default:
				return 0;
		}
	}

	public int calculateRPM(String response) {
		String valueBytes = response.substring(4);
		String byteA = valueBytes.substring(0, 2);
		String byteB = valueBytes.substring(2);

		int a = Integer.parseInt(byteA, 16);
		int b = Integer.parseInt(byteB, 16);

		int rpm = ((256 * a) + b) / 4;
		return rpm;
	}

	public int calculateEngineLoad(String response) {
		String byteA = response.substring(4);
		int a = Integer.parseInt(byteA, 16);
		int load = (100 * a) / 255;
		System.out.println("Load: " + load);
		return load;
	}

	public int calculateSpeed(String response) {
		String byteA = response.substring(4);
		int speed = Integer.parseInt(byteA, 16);
		return speed;
	}
}
