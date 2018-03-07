package com.missingplatform.obdapi;

import com.missingplatform.obdapi.Models.RawMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CreateService {
	public CreateService() {
	}

	public void processMessages(RawMessage rawMessage) {
		Map<String, String> messages = rawMessage.getRawMessages();
		for (String key : messages.keySet()) {
			String value = messages.get(key);
			System.out.println(key + value);
//			processMessage(key, value);
		}

//		splitMessage();
	}
//
//	private void splitMessage() {
//		String[] parts = message.split("\n");
//		String command = parts[0].trim();
//		String response = parts[1].trim();
//		// Note: first 4 bytes of response is 41 + PID (ex. 410C)
//	}
//
//	private void processMessage(String key, String value) {
//		switch (key) {
//			// RPM
//			case "RPM":
//				int rpm = calculateRPM(messages.get(key));
//				RpmMessage rpmMessage = new RpmMessage(new Date(), rpm);
//				rpmReopsitory.save(rpmMessage);
//				break;
//
//			// Engine load
//			case "THROTTLE_POSITION":
//				int load = calculateEngineLoad(messages.get(key));
//				engineLoadReopsitory.save(new LoadMessage(new Date(), load));
//				break;
//
//			// Vehicle Speed
//			case "SPEED":
//				int speed = calculateSpeed(messages.get(key));
//				speedRepository.save(new SpeedMessage(new Date(), speed));
//				break;
//		}
//
//	}

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
		System.out.println("Load: " + a);
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
