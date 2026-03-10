package com.balians.musicgen.provider.dto;

public record SunoGenerateResponse(
        Integer code,
        String msg,
        SunoGenerateResponseData data
) {
}
