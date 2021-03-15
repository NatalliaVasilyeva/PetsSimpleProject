package com.leverx.nvasilyeva.pet.dto.mapper;

import com.leverx.nvasilyeva.pet.dto.request.OwnerCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.OwnerResponseDTO;
import com.leverx.nvasilyeva.pet.entity.Owner;

import java.util.List;
import java.util.stream.Collectors;

import static com.leverx.nvasilyeva.pet.dto.mapper.PetMapper.convertListOfPetsToListOfPetResponseDTO;

public class OwnerMapper {

    public static Owner convertOwnerCreateDTOToOwner(OwnerCreateDTO ownerDTO) {
        return Owner
                .builder()
                .username(ownerDTO.getUsername())
                .email(ownerDTO.getEmail())
                .birthdate(ownerDTO.getBirthdate())
                .build();
    }

    public static OwnerResponseDTO convertOwnerToOwnerResponseDTO(Owner owner) {
        return OwnerResponseDTO
                .builder()
                .id(owner.getId())
                .username(owner.getUsername())
                .email(owner.getEmail())
                .birthdate(owner.getBirthdate())
                .role(owner.getRole())
                .pets(convertListOfPetsToListOfPetResponseDTO(owner.getPets()))
                .build();
    }

    public static OwnerResponseDTO convertOwnerToOwnerResponseDTOWithoutPets(Owner owner) {
        return OwnerResponseDTO
                .builder()
                .id(owner.getId())
                .username(owner.getUsername())
                .email(owner.getEmail())
                .birthdate(owner.getBirthdate())
                .role(owner.getRole())
                .build();
    }

    public static List<OwnerResponseDTO> convertListOfOwnersToListOfOwnerResponseDTO(List<Owner> owners) {
        return owners
                .stream()
                .map(OwnerMapper::convertOwnerToOwnerResponseDTO)
                .collect(Collectors.toList());
    }
}
