package com.jjj.cashlesstips.dto;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class ApiError {
    private final String message;
    private final OffsetDateTime dateOccurred;
}
