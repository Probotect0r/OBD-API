package com.missingplatform.obdapi;

import com.missingplatform.obdapi.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateController {
	@Autowired
	private CreateService createService;

	@PostMapping("/rawmessage")
	public RawMessage createMessage(@RequestBody RawMessage rawMessage) {
		this.createService.processMessages(rawMessage);
		return rawMessage;
	}

	@GetMapping("/data/{driveId}")
	public List<ProcessedMessage> getDataByDriveId(@PathVariable String driveId) {
		System.out.println(driveId);
		return this.createService.findDataByDriveId(driveId);
	}

	@GetMapping("/drive/new")
	public Drive createDrive() {
		return this.createService.createDrive();
	}

	@GetMapping("/drive/recent")
	public List<Drive> recentDrives() {
		return this.createService.recentDrives();
	}

	@PutMapping("/drive/end/{driveId}")
	public Drive endDrive(@PathVariable String driveId) {
		return this.createService.endDrive(driveId);
	}
}