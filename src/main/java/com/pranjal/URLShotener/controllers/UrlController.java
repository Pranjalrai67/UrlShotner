package com.pranjal.URLShotener.controllers;

import com.pranjal.URLShotener.dto.RequestDto;
import com.pranjal.URLShotener.dto.ResponseDto;
import com.pranjal.URLShotener.entity.Url;
import com.pranjal.URLShotener.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;
    @PostMapping("urls")
    public ResponseEntity<ResponseDto> addUrl(@RequestBody RequestDto requestDto){
        ResponseDto response = urlService.createShortUrl(requestDto);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String originalUrl = urlService.getOriginalUrl(shortCode);
        return ResponseEntity
                .status(HttpStatus.FOUND)   // 302
                .location(URI.create(originalUrl))
                .build();
    }
    @DeleteMapping("/deleteAll")
    public String deleteAll(){
        String response = urlService.deleteAllPrevious();
        return  response;
    }

}
