package com.balians.musicgen.schedule.repository;

import com.balians.musicgen.schedule.model.ScheduleRun;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleRunRepository extends MongoRepository<ScheduleRun, String> {

    List<ScheduleRun> findByRunDate(LocalDate runDate);
}
