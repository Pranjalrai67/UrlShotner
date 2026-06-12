package com.pranjal.URLShotener.Exceptions;

public class ShortCodeNotFoundException extends RuntimeException {
    public ShortCodeNotFoundException(String mess){
        super(mess);
    }
}
