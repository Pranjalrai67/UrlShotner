package com.pranjal.URLShotener.dto;


import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class ResponseDto {

    @URL
    private String originalUrl;

    private String shortCode;

}
