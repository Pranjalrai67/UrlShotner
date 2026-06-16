package com.pranjal.URLShotener.services;

import com.pranjal.URLShotener.Exceptions.AlreadyExistsException;
import com.pranjal.URLShotener.Exceptions.ShortCodeNotFoundException;
import com.pranjal.URLShotener.Exceptions.TimeShouldBeAfterNowException;
import com.pranjal.URLShotener.Exceptions.UrlExpiredException;
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

        if (url.getExpiresAt() != null &&
                url.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new UrlExpiredException("This URL has expired");
        }
        url.setLastAccessed(LocalDateTime.now());
        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);
        return url.getOriginalUrl();
    }
    // For Testing Purpose made this
//    public String deleteAllPrevious(){
//        urlRepository.deleteAll();
//        return "All values are deleted";
//    }

    public ResponseDto createShortUrl(RequestDto requestDto){
        Url actual= new Url();
        actual.setOriginalUrl(requestDto.getOriginalUrl());

        if (requestDto.getExpiresAt() != null &&
                requestDto.getExpiresAt().isBefore(LocalDateTime.now())) {

            throw new TimeShouldBeAfterNowException(
                    "Expiration time must be in the future");
        }

        actual.setExpiresAt(requestDto.getExpiresAt());

        String shortCode;
        if(requestDto.getCustomAlias() != null && !requestDto.getCustomAlias().isBlank()){
            if(checkShortCode(requestDto.getCustomAlias())){
                throw new AlreadyExistsException("This short Code already Exists");
            }
            shortCode = requestDto.getCustomAlias();
        }else{

            //Creating New Short Code
            shortCode = ShortCodeGenerator.createShortCode(6);

            //Checking Weather That Code Already Exists or Not
            while(checkShortCode(shortCode)) {
                shortCode = ShortCodeGenerator.createShortCode(6);
            }
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
