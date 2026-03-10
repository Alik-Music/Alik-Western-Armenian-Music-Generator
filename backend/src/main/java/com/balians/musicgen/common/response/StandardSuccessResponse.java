package com.balians.musicgen.common.response;

import java.time.Instant;

public record StandardSuccessResponse<T>(
        boolean success,
        Instant timestamp,
        T data
) {
    public static <T> StandardSuccessResponse<T> ok(T data) {
        return new StandardSuccessResponse<>(true, Instant.now(), data);
    }
}
