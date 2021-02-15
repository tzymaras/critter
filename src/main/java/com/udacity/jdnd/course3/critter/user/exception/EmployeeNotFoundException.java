package com.udacity.jdnd.course3.critter.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
    private static final String EMPLOYEE_NOT_FOUND = "employee with id:%d not found";

    public EmployeeNotFoundException(Long employeeId) {
        super(String.format(EMPLOYEE_NOT_FOUND, employeeId));
    }
}
