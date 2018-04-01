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
import java.util.List;
import java.util.Map;

@Service
public class CreateService {

	@Autowired
	private ProcessedMessageRepository processedMessageRepository;

	@Autowired
	private DriveRepository driveRepository;

	public CreateService() {}

	public void processMessages(RawMessage rawMessage) {
		Map<String, String> messages = rawMessage.getRawMessages();

		Map<String, Integer> processedValues = new HashMap<>();

		for (String key : messages.keySet()) {
			String value = messages.get(key);
			processedValues.put(key, processMessage(key, value));
		}

		System.out.println(processedValues);
		ProcessedMessage processedMessage = new ProcessedMessage(processedValues, rawMessage.getDriveId());

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
			case "COOLANT_TEMPERATURE":
				return calculateCoolantTemperature(value);
			case "FUEL_PRESSURE":
				return calculateFuelPressure(value);
			case "MAF":
				return calculateMAF(value);
			case "FUEL_RATE":
				return calculateFuelRate(value);
			default:
				return 0;
		}
	}

	private int calculateRPM(String response) {
		String valueBytes = response.substring(4);
		String byteA = valueBytes.substring(0, 2);
		String byteB = valueBytes.substring(2);

		int a = Integer.parseInt(byteA, 16);
		int b = Integer.parseInt(byteB, 16);

		int rpm = ((256 * a) + b) / 4;
		return rpm;
	}

	private int calculateEngineLoad(String response) {
		String byteA = response.substring(4);
		int a = Integer.parseInt(byteA, 16);
		int load = (100 * a) / 255;
		return load;
	}

	private int calculateSpeed(String response) {
		String byteA = response.substring(4);
		int speed = Integer.parseInt(byteA, 16);
		return speed;
	}

	private int calculateCoolantTemperature(String response) {
		String byteA = response.substring(4);
		int a = Integer.parseInt(byteA, 16);
		int temp = a - 40;
		return temp;
	}

	private int calculateFuelPressure(String response) {
		String byteA = response.substring(4);
		int a = Integer.parseInt(byteA, 16);
		return 3 * a;
	}

	private int calculateMAF(String response) {
		String valueBytes = response.substring(4);
		String byteA = valueBytes.substring(0, 2);
		String byteB = valueBytes.substring(2);

		int a = Integer.parseInt(byteA, 16);
		int b = Integer.parseInt(byteB, 16);

		int maf = ((256 * a) + b) / 100;
		return maf;
	}

	private int calculateFuelRate(String response) {
		String valueBytes = response.substring(4);
		String byteA = valueBytes.substring(0, 2);
		String byteB = valueBytes.substring(2);

		int a = Integer.parseInt(byteA, 16);
		int b = Integer.parseInt(byteB, 16);

		int fuelRate = ((256 * a) + b) / 20;
		return fuelRate;
	}

	public List<Drive> recentDrives() {
		return this.driveRepository.findTop5ByOrderByStartDesc();
	}

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

	public List<ProcessedMessage> findDataByDriveId(String driveId) {
		return this.processedMessageRepository.findByDriveId(driveId);
	}

	public List<Drive> findDriveByDate(Date date) {
		return this.findDriveByDate(date);
	}
}
