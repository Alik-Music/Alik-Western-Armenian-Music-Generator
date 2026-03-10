package com.balians.musicgen.provider.client;

import com.balians.musicgen.common.exception.ProviderIntegrationException;
import com.balians.musicgen.provider.dto.SunoCreditResponse;
import com.balians.musicgen.provider.dto.SunoGenerateRequest;
import com.balians.musicgen.provider.dto.SunoGenerateResponse;
import com.balians.musicgen.provider.dto.SunoRecordInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SunoClient {

    private final RestClient sunoRestClient;

    public SunoGenerateResponse submitGeneration(SunoGenerateRequest request) {
        try {
            SunoGenerateResponse response = sunoRestClient.post()
                    .uri("/api/v1/generate")
                    .body(request)
                    .retrieve()
                    .body(SunoGenerateResponse.class);

            if (response == null) {
                throw new ProviderIntegrationException(HttpStatus.BAD_GATEWAY, "PROVIDER_EMPTY_RESPONSE",
                        "Provider returned an empty response");
            }

            return response;
        } catch (RestClientResponseException ex) {
            throw mapHttpError(ex);
        } catch (ResourceAccessException ex) {
            throw new ProviderIntegrationException(HttpStatus.SERVICE_UNAVAILABLE, "PROVIDER_UNAVAILABLE",
                    "Could not reach provider");
        }
    }

    public SunoCreditResponse getCredits() {
        try {
            return sunoRestClient.get()
                    .uri("/api/v1/get-credits")
                    .retrieve()
                    .body(SunoCreditResponse.class);
        } catch (RestClientResponseException ex) {
            throw mapHttpError(ex);
        } catch (ResourceAccessException ex) {
            throw new ProviderIntegrationException(HttpStatus.SERVICE_UNAVAILABLE, "PROVIDER_UNAVAILABLE",
                    "Could not reach provider");
        }
    }

    public SunoRecordInfoResponse getRecordInfo(String taskId) {
        try {
            return sunoRestClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/api/v1/generate/record-info")
                            .queryParam("taskId", taskId)
                            .build())
                    .retrieve()
                    .body(SunoRecordInfoResponse.class);
        } catch (RestClientResponseException ex) {
            throw mapHttpError(ex);
        } catch (ResourceAccessException ex) {
            throw new ProviderIntegrationException(HttpStatus.SERVICE_UNAVAILABLE, "PROVIDER_UNAVAILABLE",
                    "Could not reach provider");
        }
    }

    private ProviderIntegrationException mapHttpError(RestClientResponseException ex) {
        HttpStatus status = HttpStatus.resolve(ex.getStatusCode().value());
        HttpStatus safeStatus = status == null ? HttpStatus.BAD_GATEWAY : status;

        if (safeStatus == HttpStatus.UNAUTHORIZED || safeStatus == HttpStatus.FORBIDDEN) {
            return new ProviderIntegrationException(HttpStatus.BAD_GATEWAY, "PROVIDER_UNAUTHORIZED",
                    "Provider authentication failed");
        }
        if (safeStatus.is4xxClientError()) {
            return new ProviderIntegrationException(HttpStatus.BAD_GATEWAY, "PROVIDER_CLIENT_ERROR",
                    "Provider rejected the request");
        }
        if (safeStatus.is5xxServerError()) {
            return new ProviderIntegrationException(HttpStatus.BAD_GATEWAY, "PROVIDER_SERVER_ERROR",
                    "Provider failed while processing the request");
        }

        log.warn("Unexpected provider HTTP status: {}", ex.getStatusCode());
        return new ProviderIntegrationException(HttpStatus.BAD_GATEWAY, "PROVIDER_HTTP_ERROR",
                "Unexpected provider HTTP error");
    }
}
