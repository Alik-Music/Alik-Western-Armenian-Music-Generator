package com.balians.musicgen.generation.dto;

import com.balians.musicgen.common.enums.InternalJobStatus;
import com.balians.musicgen.common.enums.ProviderJobStatus;
import java.time.Instant;

public record GenerationStatusHistoryResponse(
        InternalJobStatus internalStatus,
        ProviderJobStatus providerStatus,
        String message,
        Instant changedAt
) {
}
