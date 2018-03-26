package com.missingplatform.obdapi;

import com.missingplatform.obdapi.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreateController {
	@Autowired
	private CreateService createService;

	@PostMapping("/rawmessage")
	public RawMessage createMessage(@RequestBody RawMessage rawMessage) {
		this.createService.processMessages(rawMessage);
		return rawMessage;
	}

	@GetMapping("/drive/new")
	public Drive createDrive() {
		return this.createService.createDrive();
	}

	@PutMapping("/drive/end/:driveId")
	public Drive endDrive(@PathVariable String driveId) {
		return this.createService.endDrive(driveId);
	}
}