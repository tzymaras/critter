package com.udacity.jdnd.course3.critter.user.dataobject;

import com.udacity.jdnd.course3.critter.pet.dataobject.Pet;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @Nationalized
    @Type(type = "nstring")
    @Column(length = 500)
    private String name;

    private String phoneNumber;

    @Nationalized
    @Type(type = "ntext")
    @Column(length = 1500)
    private String notes;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private List<Pet> pets;

    public boolean hasPets() {
        return null != this.pets && !this.pets.isEmpty();
    }
}
