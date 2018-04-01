package com.missingplatform.obdapi.Repositories;

import com.missingplatform.obdapi.Models.Drive;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface DriveRepository extends CrudRepository<Drive, String> {
	List<Drive> findTop5ByOrderByStartDesc();
	List<Drive> findByStart(Date date);
	Drive findTopByOrderByStartDesc();
}
