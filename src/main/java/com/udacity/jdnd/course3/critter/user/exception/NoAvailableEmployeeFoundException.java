package com.udacity.jdnd.course3.critter.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoAvailableEmployeeFoundException extends RuntimeException {
    private static final String NO_EMPLOYEE_AVAILABLE = "no available employee found";

    public NoAvailableEmployeeFoundException() {
        super(NO_EMPLOYEE_AVAILABLE);
    }
}
