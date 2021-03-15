package com.leverx.nvasilyeva.pet.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cats")
@PrimaryKeyJoinColumn(name = "cat_id")
@Data
@NoArgsConstructor
public class Cat extends Pet {

    private String color;

    @Builder(builderMethodName = "catBuilder")
    public Cat(final long id,
               final String name,
               final int age,
               final PetType petType,
               final LocalDate birthday,
               final Owner owner,
               final String color) {
        super(id, name, age, petType, birthday, owner);
        this.color = color;
    }


}
