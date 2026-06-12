package com.pranjal.URLShotener.services;

import com.pranjal.URLShotener.dto.AnalyticsResponseDTO;
import com.pranjal.URLShotener.entity.Url;
import com.pranjal.URLShotener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final UrlService urlService;
    public AnalyticsResponseDTO getAnalytics(String shortCode){
        Url url = urlService.getCompleteUrl(shortCode);
        AnalyticsResponseDTO ar = new AnalyticsResponseDTO();
        ar.setLastAccessed(url.getLastAccessed());
        ar.setCreatedAt(url.getCreatedAt());
        ar.setClickCount(url.getClickCount());
        ar.setShortCode(url.getShortCode());
        ar.setOriginalUrl(url.getOriginalUrl());
        return ar;

    }

}
