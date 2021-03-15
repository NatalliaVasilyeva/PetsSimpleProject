package com.leverx.nvasilyeva.pet.dto.mapper;

import com.leverx.nvasilyeva.pet.dto.request.PetCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.PetResponseDTO;
import com.leverx.nvasilyeva.pet.entity.Pet;

import java.util.List;
import java.util.stream.Collectors;

import static com.leverx.nvasilyeva.pet.dto.mapper.OwnerMapper.convertOwnerToOwnerResponseDTOWithoutPets;


public class PetMapper {

    public static Pet convertPetCreateDTOToPet(PetCreateDTO petDTO) {
        return Pet
                .builder()
                .name(petDTO.getName())
                .age(petDTO.getAge())
                .birthday(petDTO.getBirthday())
                .build();
    }

    public static PetResponseDTO convertPetToPetResponseDTO(Pet pet) {
        return PetResponseDTO
                .builder()
                .id(pet.getId())
                .name(pet.getName())
                .age(pet.getAge())
                .petType(pet.getPetType())
                .birthday(pet.getBirthday())
                .owner(convertOwnerToOwnerResponseDTOWithoutPets(pet.getOwner()))
                .build();
    }

    public static PetResponseDTO convertPetToPetResponseDTOWithoutOwner(Pet pet) {
        return PetResponseDTO
                .builder()
                .id(pet.getId())
                .name(pet.getName())
                .age(pet.getAge())
                .petType(pet.getPetType())
                .birthday(pet.getBirthday())
                .build();
    }

    public static List<PetResponseDTO> convertListOfPetsToListOfPetResponseDTO(List<Pet> pets) {
        return pets
                .stream()
                .map(PetMapper::convertPetToPetResponseDTO)
                .collect(Collectors.toList());
    }
}
