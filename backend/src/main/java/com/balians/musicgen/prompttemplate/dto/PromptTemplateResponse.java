package com.balians.musicgen.prompttemplate.dto;

import com.balians.musicgen.common.enums.GenerationModel;
import java.time.Instant;

public record PromptTemplateResponse(
        String id,
        String projectId,
        String name,
        Boolean customMode,
        Boolean instrumental,
        GenerationModel model,
        String promptTemplate,
        String styleTemplate,
        String titleTemplate,
        Boolean active,
        Boolean isDefault,
        Instant createdAt,
        Instant updatedAt
) {
}
