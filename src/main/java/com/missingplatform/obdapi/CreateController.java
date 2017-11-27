package com.missingplatform.obdapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rawmessage")
public class CreateController {
	private CreateService createService;

	@PostMapping
	public RawMessage createMessage(@RequestBody RawMessage message) {
		this.createService.processMessage(message);
		return message;
	}

	@Autowired
	public void setCreateService(CreateService createService) {
		this.createService = createService;
	}
}
