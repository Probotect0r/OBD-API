package com.missingplatform.obdapi.Repositories;

import com.missingplatform.obdapi.Models.ProcessedMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProcessedMessageRepository extends CrudRepository<ProcessedMessage, String> {
	List<ProcessedMessage> findByDriveId(String driveId);
	ProcessedMessage findTopByOrderByTimestampDesc();
}