package com.leverx.nvasilyeva.pet.utils;

import com.leverx.nvasilyeva.pet.dto.request.OwnerCreateDTO;
import com.leverx.nvasilyeva.pet.entity.Pet;
import com.leverx.nvasilyeva.pet.entity.PetType;
import com.leverx.nvasilyeva.pet.entity.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.util.Objects.nonNull;

@Service
public class DataValidator {

    public void validateOwnerPassword(OwnerCreateDTO ownerDTO) {
        if (StringUtils.isBlank(ownerDTO.getPassword()) ||
                StringUtils.isBlank(ownerDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Password should be not null");
        }
        if (!ownerDTO.getPassword().equals(ownerDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords should be equals");
        }
    }

    public boolean validateCollectionOfPets(final List<? extends Pet> pets) {
        return nonNull(pets) && pets.size() != 0;
    }

    public boolean isCorrectPetType(final String petType) {
        return Arrays.stream(PetType.values()).anyMatch((t) -> t.name().equals(petType.toUpperCase(Locale.ROOT)));
    }

    public boolean isCorrectRole(final String role) {
        return Arrays.stream(Role.values()).anyMatch((t) -> t.name().equals(role.toUpperCase(Locale.ROOT)));
    }

}
