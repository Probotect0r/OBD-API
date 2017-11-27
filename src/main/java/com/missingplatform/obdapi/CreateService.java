package com.missingplatform.obdapi;

import com.missingplatform.obdapi.Models.ProcessedMessage;
import com.missingplatform.obdapi.Models.RpmMessage;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateService {
	public CreateService() {
	}

	public void processMessage(RawMessage rawMessage) {
		// 1. Determine the Command (done)
		// 2. Determine the "response" (done)
		// 3. Calculate the value (done)
		// 4. Set up timestamp (done)
		// 5. Write to MongoDB

		String message = rawMessage.getRawMessage();
		String[] parts = message.split("\n");
		String command = parts[0].trim();
		String response = parts[1].trim();

		System.out.println("Command: " + command);
		System.out.println("Response: " + response);

		ProcessedMessage processedMessage;
		switch (command) {
			case "010c":
				int rpm = calculateRPM(response);
				processedMessage = new RpmMessage(new Date(), rpm);
				break;
		}

		// Write to mongodb
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
}
