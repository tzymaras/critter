package com.udacity.jdnd.course3.critter.schedule.dataobject;

import com.udacity.jdnd.course3.critter.pet.dataobject.Pet;
import com.udacity.jdnd.course3.critter.user.dataobject.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    private List<Employee> employees;

    @ManyToMany
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private Set<EmployeeSkill> activities;
}
