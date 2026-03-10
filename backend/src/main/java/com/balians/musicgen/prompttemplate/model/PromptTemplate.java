package com.balians.musicgen.prompttemplate.model;

import com.balians.musicgen.common.audit.AuditableDocument;
import com.balians.musicgen.common.enums.GenerationModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "prompt_templates")
@CompoundIndexes({
        @CompoundIndex(name = "idx_prompt_templates_project_default", def = "{'projectId': 1, 'isDefault': 1}")
})
public class PromptTemplate extends AuditableDocument {

    @Id
    private String id;

    @Indexed(name = "idx_prompt_templates_project_id")
    private String projectId;

    private String name;
    private Boolean customMode;
    private Boolean instrumental;
    private GenerationModel model;
    private String promptTemplate;
    private String styleTemplate;
    private String titleTemplate;

    @Indexed(name = "idx_prompt_templates_active")
    private Boolean active;

    @Indexed(name = "idx_prompt_templates_is_default")
    private Boolean isDefault;
}
