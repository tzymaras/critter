package com.udacity.jdnd.course3.critter.pet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PetNotFoundException extends RuntimeException {
    private static final String PET_NOT_FOUND = "pet with id:%d not found";

    public PetNotFoundException(long petId) {
        super(String.format(PET_NOT_FOUND, petId));
    }
}
