package com.balians.musicgen.provider.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SunoGenerateRequest(
        String prompt,
        Boolean customMode,
        Boolean instrumental,
        String model,
        String style,
        String title,
        String callBackUrl
) {
}
