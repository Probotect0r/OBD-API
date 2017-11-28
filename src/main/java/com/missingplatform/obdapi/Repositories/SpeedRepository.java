package com.missingplatform.obdapi.Repositories;

import com.missingplatform.obdapi.Models.SpeedMessage;
import org.springframework.data.repository.CrudRepository;

public interface SpeedRepository extends CrudRepository<SpeedMessage, String> {
}
