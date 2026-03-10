package com.balians.musicgen.provider.dto;

import com.fasterxml.jackson.databind.JsonNode;

public record SunoRecordInfoResponse(
        Integer code,
        String msg,
        JsonNode data
) {
}
