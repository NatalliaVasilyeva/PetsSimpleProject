package com.leverx.nvasilyeva.pet.dto.response;

import com.leverx.nvasilyeva.pet.entity.PetType;
import lombok.Builder;

import java.time.LocalDate;

public class CatResponseDTO extends PetResponseDTO{
    private String color;

    @Builder(builderMethodName = "catResponseDTOBuilder")
    public CatResponseDTO(final long id,
                          final String name,
                          final int age,
                          final PetType petType,
                          final LocalDate birthday,
                          final OwnerResponseDTO owner,
                          final String color) {
        super(id, name, age, petType, birthday, owner);
        this.color = color;
    }
}
