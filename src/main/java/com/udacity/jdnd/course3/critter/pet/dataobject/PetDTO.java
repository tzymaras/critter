package com.udacity.jdnd.course3.critter.pet.dataobject;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PetDTO {
    private long id;

    @NotNull
    private PetType type;

    @NotBlank
    private String name;

    @NotNull
    private long ownerId;
    private LocalDate birthDate;
    private String notes;
}
