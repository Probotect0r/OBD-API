package com.missingplatform.obdapi.Repositories;

import com.missingplatform.obdapi.Models.SpeedMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpeedRepository extends CrudRepository<SpeedMessage, String> {
	SpeedMessage findTopByOrderByTimestampDesc();
	List<SpeedMessage> findTop10ByOrderByTimestampDesc();
}
