package com.leverx.nvasilyeva.pet.dto.response;
import com.leverx.nvasilyeva.pet.entity.PetType;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class PetResponseDTO {
    private long id;

    private String name;

    private int age;

    private PetType petType;

    private LocalDate birthday;

    private OwnerResponseDTO owner;
}
