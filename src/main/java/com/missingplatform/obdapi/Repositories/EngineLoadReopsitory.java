package com.missingplatform.obdapi.Repositories;

import com.missingplatform.obdapi.Models.LoadMessage;
import com.missingplatform.obdapi.Models.RpmMessage;
import org.springframework.data.repository.CrudRepository;

public interface EngineLoadReopsitory extends CrudRepository<LoadMessage, String> {
}
