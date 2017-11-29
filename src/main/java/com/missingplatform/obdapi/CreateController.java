package com.missingplatform.obdapi;

import com.missingplatform.obdapi.Models.LoadMessage;
import com.missingplatform.obdapi.Models.ProcessedMessage;
import com.missingplatform.obdapi.Models.RpmMessage;
import com.missingplatform.obdapi.Models.SpeedMessage;
import com.missingplatform.obdapi.Repositories.EngineLoadReopsitory;
import com.missingplatform.obdapi.Repositories.RpmReopsitory;
import com.missingplatform.obdapi.Repositories.SpeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateController {
	private CreateService createService;
	private RpmReopsitory rpmReopsitory;
	private EngineLoadReopsitory engineLoadReopsitory;
	private SpeedRepository speedRepository;


	@PostMapping("/rawmessage")
	public RawMessage createMessage(@RequestBody RawMessage message) {
		this.createService.processMessage(message);
		return message;
	}

	@GetMapping("/rpm")
	public List<RpmMessage> getAllRpmMessages() {
		List<RpmMessage> messages = rpmReopsitory.findTop10ByOrderByTimestampDesc();
		return messages;
	}

	@GetMapping("/rpm/latest")
	public RpmMessage getLatestRpmMessage() {
		RpmMessage message = rpmReopsitory.findTopByOrderByTimestampDesc();
		return message;
	}

	@GetMapping("/load")
	public List<LoadMessage> getAllLoadMessages() {
		List<LoadMessage> messages = engineLoadReopsitory.findTop10ByOrderByTimestampDesc();
		return messages;
	}

	@GetMapping("/load/latest")
	public LoadMessage getLatestLoadMessage() {
		LoadMessage message = engineLoadReopsitory.findTopByOrderByTimestampDesc();
		return message;
	}

	@GetMapping("/speed")
	public List<SpeedMessage> getAllSpeedMessages() {
		List<SpeedMessage> messages = speedRepository.findTop10ByOrderByTimestampDesc();
		return messages;
	}

	@GetMapping("/speed/latest")
	public SpeedMessage getLatestSpeedMessage() {
		SpeedMessage message = speedRepository.findTopByOrderByTimestampDesc();
		return message;
	}

	@Autowired
	public void setCreateService(CreateService createService) {
		this.createService = createService;
	}

	@Autowired
	public void setRpmReopsitory(RpmReopsitory rpmReopsitory) {
		this.rpmReopsitory = rpmReopsitory;
	}

	@Autowired
	public void setEngineLoadReopsitory(EngineLoadReopsitory engineLoadReopsitory) {
		this.engineLoadReopsitory = engineLoadReopsitory;
	}

	@Autowired
	public void setSpeedRepository(SpeedRepository speedRepository) {
		this.speedRepository = speedRepository;
	}
}