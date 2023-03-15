package com.spring.rest.util;

import java.time.LocalDateTime;

public class MeasurementErrorResponse {
    private String message;
    private LocalDateTime localDateTime;

    public MeasurementErrorResponse(String message, LocalDateTime localDateTime) {
        this.message = message;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
