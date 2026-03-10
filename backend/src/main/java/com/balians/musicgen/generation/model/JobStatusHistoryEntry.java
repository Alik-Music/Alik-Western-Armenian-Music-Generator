package com.balians.musicgen.generation.model;

import com.balians.musicgen.common.enums.InternalJobStatus;
import com.balians.musicgen.common.enums.ProviderJobStatus;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobStatusHistoryEntry {

    private InternalJobStatus internalStatus;
    private ProviderJobStatus providerStatus;
    private String message;
    private Instant changedAt;
}
