package com.balians.musicgen.schedule.model;

import com.balians.musicgen.common.audit.AuditableDocument;
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
@Document(collection = "schedule_definitions")
public class ScheduleDefinition extends AuditableDocument {

    @Id
    private String id;

    @Indexed(name = "idx_schedule_definitions_project_id")
    private String projectId;

    private String templateId;
    private String name;
    private String timezone;
    private String cronExpression;
    private Boolean enabled;
}
