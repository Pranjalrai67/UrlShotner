package com.pranjal.URLShotener.services;

import com.pranjal.URLShotener.Exceptions.ShortCodeNotFoundException;
import com.pranjal.URLShotener.dto.RequestDto;
import com.pranjal.URLShotener.dto.ResponseDto;
import com.pranjal.URLShotener.entity.Url;
import com.pranjal.URLShotener.repository.UrlRepository;
import com.pranjal.URLShotener.utils.ShortCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;

    public  boolean checkShortCode(String shortCode){

        return urlRepository.existsByShortCode(shortCode);
    }

    public String getOriginalUrl(String shortCode){
        Url url= urlRepository.findByShortCode(shortCode).orElseThrow(()->new ShortCodeNotFoundException("" +
                "ShortCode Not Found."));
        url.setLastAccessed(LocalDateTime.now());
        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);
        return url.getOriginalUrl();
    }

    public String deleteAllPrevious(){
        urlRepository.deleteAll();
        return "All values are deleted";
    }

    public ResponseDto createShortUrl(RequestDto requestDto){
        Url actual= new Url();
        actual.setOriginalUrl(requestDto.getOriginalUrl());

        //Creating New Short Code
        String shortCode = ShortCodeGenerator.createShortCode(6);


        //Checking Weather That Code Already Exists or Not
        while(checkShortCode(shortCode)) {
            shortCode = ShortCodeGenerator.createShortCode(6);
        }
        actual.setCreatedAt(LocalDateTime.now());
        actual.setShortCode(shortCode);
        actual.setClickCount(0);
        urlRepository.save(actual);

        //Giving Bakc Response
        ResponseDto response = new ResponseDto();
        response.setOriginalUrl(actual.getOriginalUrl());
        response.setShortCode(actual.getShortCode());
        return response;
    }
    public Url getCompleteUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new ShortCodeNotFoundException("Invalid ShortCode for Analytics"));

        return url;
    }

}
