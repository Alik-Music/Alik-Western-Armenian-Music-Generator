package com.balians.musicgen.prompttemplate.dto;

import com.balians.musicgen.common.enums.GenerationModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdatePromptTemplateRequest(
        @NotBlank(message = "name is required")
        @Size(max = 120, message = "name must be at most 120 characters")
        String name,

        @NotNull(message = "customMode is required")
        Boolean customMode,

        @NotNull(message = "instrumental is required")
        Boolean instrumental,

        @NotNull(message = "model is required")
        GenerationModel model,

        @NotBlank(message = "promptTemplate is required")
        @Size(max = 2000, message = "promptTemplate must be at most 2000 characters")
        String promptTemplate,

        @Size(max = 500, message = "styleTemplate must be at most 500 characters")
        String styleTemplate,

        @Size(max = 255, message = "titleTemplate must be at most 255 characters")
        String titleTemplate,

        @NotNull(message = "active is required")
        Boolean active,

        @NotNull(message = "isDefault is required")
        Boolean isDefault
) {
}
