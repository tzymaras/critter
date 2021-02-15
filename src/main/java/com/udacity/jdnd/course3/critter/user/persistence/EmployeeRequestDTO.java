package com.udacity.jdnd.course3.critter.user.persistence;

import com.udacity.jdnd.course3.critter.user.persistence.EmployeeSkill;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
public class EmployeeRequestDTO {
    @NotEmpty
    private Set<EmployeeSkill> skills;

    @NotEmpty
    private LocalDate date;

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
