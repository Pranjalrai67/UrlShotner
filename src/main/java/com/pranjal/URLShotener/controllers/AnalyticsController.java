package com.pranjal.URLShotener.controllers;

import com.pranjal.URLShotener.dto.AnalyticsResponseDTO;
import com.pranjal.URLShotener.entity.Url;
import com.pranjal.URLShotener.services.AnalyticsService;
import com.pranjal.URLShotener.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<AnalyticsResponseDTO> getAnalytics(@PathVariable String shortCode){
        AnalyticsResponseDTO Ar = analyticsService.getAnalytics(shortCode);
        return ResponseEntity.ok(Ar);
    }


}
