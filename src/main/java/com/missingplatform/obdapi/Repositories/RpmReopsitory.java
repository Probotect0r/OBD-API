package com.missingplatform.obdapi.Repositories;

import com.missingplatform.obdapi.Models.RpmMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RpmReopsitory extends CrudRepository<RpmMessage, String> {
	RpmMessage findTopByOrderByTimestampDesc();
	List<RpmMessage> findTop10ByOrderByTimestampDesc();
}
