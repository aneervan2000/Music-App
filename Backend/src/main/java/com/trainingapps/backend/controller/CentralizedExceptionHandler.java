package com.trainingapps.backend.controller;

import com.trainingapps.backend.exception.NoTrackFoundException;
import com.trainingapps.backend.exception.TrackAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(TrackAlreadyExistsException.class)
    public String trackAlreadyExists(TrackAlreadyExistsException e) {
        String msg = e.getMessage();
        return msg;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoTrackFoundException.class)
    public String noTrackFound(NoTrackFoundException e) {
        String msg = e.getMessage();
        return msg;
    }
}
