package com.missingplatform.obdapi;

import com.missingplatform.obdapi.Models.LoadMessage;
import com.missingplatform.obdapi.Models.ProcessedMessage;
import com.missingplatform.obdapi.Models.RpmMessage;
import com.missingplatform.obdapi.Models.SpeedMessage;
import com.missingplatform.obdapi.Repositories.EngineLoadReopsitory;
import com.missingplatform.obdapi.Repositories.RpmReopsitory;
import com.missingplatform.obdapi.Repositories.SpeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateService {
	@Autowired
	RpmReopsitory rpmReopsitory;
	@Autowired
	EngineLoadReopsitory engineLoadReopsitory;
	@Autowired
	SpeedRepository speedRepository;

	public CreateService() {
	}

	public void processMessage(RawMessage rawMessage) {
		String message = rawMessage.getRawMessage();
		String[] parts = message.split("\n");
		String command = parts[0].trim();
		String response = parts[1].trim();
		// Note: first 4 bytes of response is 41 + PID (ex. 410C)

		System.out.println("Command: " + command);
		System.out.println("Response: " + response);

		switch (command) {
			// RPM
			case "010C":
				int rpm = calculateRPM(response);
				RpmMessage rpmMessage = new RpmMessage(new Date(), rpm);
				rpmReopsitory.save(rpmMessage);
				break;

			// Engine load
			case "0111":
				int load = calculateEngineLoad(response);
				engineLoadReopsitory.save(new LoadMessage(new Date(), load));
				break;

			// Vehicle Speed
			case "010D":
				int speed = calculateSpeed(response);
				speedRepository.save(new SpeedMessage(new Date(), speed));
				break;
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

	public int calculateThrottlePosition(String response) {
		return 0;
	}


}
