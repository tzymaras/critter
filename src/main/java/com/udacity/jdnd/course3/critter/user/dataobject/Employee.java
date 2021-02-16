package com.udacity.jdnd.course3.critter.user.dataobject;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @NotBlank
    @Nationalized
    @Column(length = 500)
    @Type(type = "nstring")
    private String name;

    @NotNull
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    @NonNull
    private Set<EmployeeSkill> skills;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;
}
