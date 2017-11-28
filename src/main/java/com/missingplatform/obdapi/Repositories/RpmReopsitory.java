package com.missingplatform.obdapi.Repositories;

import com.missingplatform.obdapi.Models.RpmMessage;
import org.springframework.data.repository.CrudRepository;

public interface RpmReopsitory extends CrudRepository<RpmMessage, String> {
}
