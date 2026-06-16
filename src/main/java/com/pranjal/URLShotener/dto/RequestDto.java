package com.pranjal.URLShotener.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data

public class RequestDto {
    @NotBlank
    @URL
    private String originalUrl;
    private String customAlias = null;
}
