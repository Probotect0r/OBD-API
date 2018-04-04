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
		return driveRepository.findByStart(date);
	}

	public Drive getPreviousDrive() {
		return driveRepository.findTopByOrderByStartDesc();
	}

	public ProcessedMessage getLatestMessage() {
		return processedMessageRepository.findTopByOrderByTimestampDesc();
	}

	public List<ProcessedMessage> getLastTenMessages() {
		return processedMessageRepository.findTop10ByOrderByTimestampDesc();
	}

	public void processMessages(RawMessage rawMessage) {
		Map<String, String> messages = rawMessage.getRawMessages();

		Map<String, Object> processedValues = new HashMap<>();

		for (String key : messages.keySet()) {
			String value = messages.get(key);
			processedValues.put(key, processMessage(key, value));
		}

		double fuelEconomy = calculateFuelEconomy((int) processedValues.get("SPEED"), (int) processedValues.get("MAF"));
		processedValues.put("FUEL_ECONOMY", fuelEconomy);

		System.out.println(processedValues);

		ProcessedMessage processedMessage = new ProcessedMessage(processedValues, rawMessage.getDriveId());
		processedMessageRepository.save(processedMessage);
	}

	private Object processMessage(String key, String response) {
		String[] parts = response.split("\n");
		String command = parts[0].trim();
		String value = parts[1].trim();

		if("NODATA".equals(value)) {
			return null;
		}

		switch (key) {
			case "RPM":
				return calculateRPM(value);
			case "THROTTLE_POSITION":
				return calculateEngineLoad(value);
			case "ENGINE_LOAD":
				return calculateEngineLoad(value);
			case "SPEED":
				return calculateSpeed(value);
			case "COOLANT_TEMPERATURE":
				return calculateCoolantTemperature(value);
			case "FUEL_PRESSURE":
				return calculateFuelPressure(value);
			case "MAF":
				return calculateMAF(value);
			case "FUEL_SYSTEM_STATUS":
				return calculateFuelSystemStatus(value);
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

	private String calculateFuelSystemStatus(String response) {
		String valueBytes = response.substring(4);
		String byteA = valueBytes.substring(0, 2);
		int a = Integer.parseInt(byteA, 16);
		switch (a) {
			case 1:
				return "Insufficient Engine Temperature";
			case 2:
				return "Good";
			case 4:
				return "High engine load";
			case 8:
				return "System failure";
			case 16:
				return "Functioning, but has system failure";
			default:
				return "Good";
		}
	}

	private double calculateFuelEconomy(int speed, int maf) {
		return (3600 * maf)/(9069.90 * speed);
	}
}
