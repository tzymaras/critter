package com.udacity.jdnd.course3.critter.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ScheduleNotPossibleException extends RuntimeException {
    public ScheduleNotPossibleException(String message) {
        super(message);
    }
}
