package com.missingplatform.obdapi.Repositories;

import com.missingplatform.obdapi.Models.LoadMessage;
import com.missingplatform.obdapi.Models.RpmMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EngineLoadReopsitory extends CrudRepository<LoadMessage, String> {
	LoadMessage findTopByOrderByTimestampDesc();
	List<LoadMessage> findTop10ByOrderByTimestampDesc();
}
