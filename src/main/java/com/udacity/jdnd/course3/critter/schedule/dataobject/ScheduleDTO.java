package com.udacity.jdnd.course3.critter.schedule.dataobject;

import com.udacity.jdnd.course3.critter.user.dataobject.EmployeeSkill;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleDTO {
    private long id;

    @NotEmpty
    private List<Long> employeeIds;

    @NotEmpty
    private List<Long> petIds;

    @NotNull
    private LocalDate date;

    @NotEmpty
    private Set<EmployeeSkill> activities;
}
