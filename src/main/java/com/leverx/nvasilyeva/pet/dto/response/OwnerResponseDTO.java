package com.leverx.nvasilyeva.pet.dto.response;

import com.leverx.nvasilyeva.pet.entity.Role;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OwnerResponseDTO {

    private long id;

    private String username;

    private String email;

    private LocalDate birthdate;

    private Role role;

    private List<PetResponseDTO> pets;

}
