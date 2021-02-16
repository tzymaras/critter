package com.udacity.jdnd.course3.critter.pet.dataobject;

import com.udacity.jdnd.course3.critter.user.dataobject.Customer;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

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

    @NotNull
    @Nationalized
    @Column(length = 500)
    @Type(type = "nstring")
    private String name;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer owner;

    @PastOrPresent
    private LocalDate birthDate;

    @Nationalized
    @Type(type = "ntext")
    @Column(length = 1500)
    private String notes;
}
