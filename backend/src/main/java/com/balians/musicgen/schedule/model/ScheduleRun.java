package com.balians.musicgen.schedule.model;

import com.balians.musicgen.common.enums.ScheduleRunStatus;
import java.time.Instant;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "schedule_runs")
public class ScheduleRun {

    @Id
    private String id;

    @Indexed(name = "idx_schedule_runs_definition_id")
    private String scheduleDefinitionId;

    private String generationJobId;

    @Indexed(name = "idx_schedule_runs_run_date")
    private LocalDate runDate;

    private ScheduleRunStatus status;
    private Instant startedAt;
    private Instant finishedAt;
    private String errorMessage;
}
