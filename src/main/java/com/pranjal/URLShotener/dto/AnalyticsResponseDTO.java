package com.pranjal.URLShotener.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnalyticsResponseDTO {
    private String originalUrl;
    private String shortCode;

    private LocalDateTime createdAt;

    private int clickCount;
    private LocalDateTime expiresAt;
    private LocalDateTime lastAccessed;
}
