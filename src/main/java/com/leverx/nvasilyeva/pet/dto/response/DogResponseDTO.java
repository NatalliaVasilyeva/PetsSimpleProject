package com.leverx.nvasilyeva.pet.dto.response;

import com.leverx.nvasilyeva.pet.entity.PetType;
import com.leverx.nvasilyeva.pet.entity.Size;
import lombok.Builder;

import java.time.LocalDate;

public class DogResponseDTO extends PetResponseDTO{

    private Size size;

    @Builder(builderMethodName = "dogResponseDTOBuilder")
    public DogResponseDTO(final long id,
                          final String name,
                          final int age,
                          final PetType petType,
                          final LocalDate birthday,
                          final OwnerResponseDTO owner,
                          final Size size) {
        super(id, name, age, petType, birthday, owner);
        this.size = size;
    }
}
