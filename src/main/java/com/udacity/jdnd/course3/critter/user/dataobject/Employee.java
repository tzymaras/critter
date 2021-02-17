package com.udacity.jdnd.course3.critter.user.dataobject;

import com.udacity.jdnd.course3.critter.schedule.dataobject.Schedule;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Nationalized
    @Column(length = 500)
    @Type(type = "nstring")
    private String name;

    @NonNull
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private Set<EmployeeSkill> skills;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Schedule> schedules;
}
