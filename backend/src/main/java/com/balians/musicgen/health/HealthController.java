package com.balians.musicgen.health;

import com.balians.musicgen.common.response.StandardSuccessResponse;
import com.balians.musicgen.config.AppProperties;
import java.time.Instant;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/health")
public class HealthController {

    private final AppProperties appProperties;

    @GetMapping
    public StandardSuccessResponse<Map<String, Object>> health() {
        return StandardSuccessResponse.ok(Map.of(
                "status", "UP",
                "application", appProperties.getName(),
                "timestamp", Instant.now()
        ));
    }
}
