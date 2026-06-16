package com.pranjal.URLShotener.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(ShortCodeNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ShortCodeNotFoundException ex){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExist(AlreadyExistsException ex){
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    @ExceptionHandler(TimeShouldBeAfterNowException.class)
    public ResponseEntity<String> handleTimeBefore(TimeShouldBeAfterNowException ex){
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
    @ExceptionHandler(UrlExpiredException.class)
    public ResponseEntity<String> handleExpiredUrl(UrlExpiredException ex){
        return  ResponseEntity.status(HttpStatus.GONE).body(ex.getMessage());
    }

}
