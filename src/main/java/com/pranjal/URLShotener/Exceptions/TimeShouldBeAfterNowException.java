package com.pranjal.URLShotener.Exceptions;

public class TimeShouldBeAfterNowException extends RuntimeException {
    public TimeShouldBeAfterNowException(String message) {
        super(message);
    }
}
