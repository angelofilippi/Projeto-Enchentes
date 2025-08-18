package com.api.enchentes.common.exception;

import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

public record ApiError(String message, int status, String error, String timestamp) {
    public static ApiError of(String msg, HttpStatus status) {
        return new ApiError(msg, status.value(), status.getReasonPhrase(), OffsetDateTime.now().toString());
    }
}
