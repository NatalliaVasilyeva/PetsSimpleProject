package com.leverx.nvasilyeva.pet.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dogs")
@PrimaryKeyJoinColumn(name = "dog_id")
@Data
@NoArgsConstructor
public class Dog extends Pet {

    @Enumerated(EnumType.STRING)
    private Size size;

    @Builder(builderMethodName = "dogBuilder")
    public Dog(final long id,
               final String name,
               final int age,
               final PetType petType,
               final LocalDate birthday,
               final Owner owner,
               final Size size) {
        super(id, name, age, petType, birthday, owner);
        this.size = size;
    }

}
