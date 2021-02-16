package com.udacity.jdnd.course3.critter.user.dataobject;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRequestDTO {
    @NotEmpty
    private Set<EmployeeSkill> skills;

    @NotNull
    private LocalDate date;
}
