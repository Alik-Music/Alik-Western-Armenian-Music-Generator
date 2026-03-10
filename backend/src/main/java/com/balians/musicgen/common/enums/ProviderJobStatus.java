package com.balians.musicgen.common.enums;

public enum ProviderJobStatus {
    NOT_SUBMITTED,
    PENDING,
    TEXT_SUCCESS,
    FIRST_SUCCESS,
    SUCCESS,
    CREATE_TASK_FAILED,
    GENERATE_AUDIO_FAILED,
    CALLBACK_EXCEPTION,
    SENSITIVE_WORD_ERROR
}
