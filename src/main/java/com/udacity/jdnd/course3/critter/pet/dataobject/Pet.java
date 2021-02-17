package com.udacity.jdnd.course3.critter.pet.dataobject;

import com.udacity.jdnd.course3.critter.schedule.dataobject.Schedule;
import com.udacity.jdnd.course3.critter.user.dataobject.Customer;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private PetType type;

    @NonNull
    @Nationalized
    @Column(length = 500)
    @Type(type = "nstring")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer owner;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    @PastOrPresent
    private LocalDate birthDate;

    @Nationalized
    @Type(type = "ntext")
    @Column(length = 1500)
    private String notes;
}
