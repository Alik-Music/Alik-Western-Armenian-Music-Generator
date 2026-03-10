package com.balians.musicgen.common.exception;

import org.springframework.http.HttpStatus;

public class ProviderIntegrationException extends BusinessException {

    public ProviderIntegrationException(HttpStatus status, String errorCode, String message) {
        super(status, errorCode, message);
    }
}
