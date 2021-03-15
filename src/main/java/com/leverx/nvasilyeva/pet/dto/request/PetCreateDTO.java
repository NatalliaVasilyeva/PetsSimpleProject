package com.leverx.nvasilyeva.pet.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class PetCreateDTO {

    @NotNull
    @Length(min=2, max = 30)
    private String name;

    private int age;

    private String petType;

    @DateTimeFormat(pattern = "DD-MM-YYYY")
    private LocalDate birthday;

    @Positive
    private long ownerId;
}
