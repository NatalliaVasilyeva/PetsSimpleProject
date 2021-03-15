package com.leverx.nvasilyeva.pet.dto.mapper;

import com.leverx.nvasilyeva.pet.dto.request.CatCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.CatResponseDTO;
import com.leverx.nvasilyeva.pet.entity.Cat;

import java.util.List;
import java.util.stream.Collectors;

import static com.leverx.nvasilyeva.pet.dto.mapper.OwnerMapper.*;

public class CatMapper {

    public static Cat convertCatCreateDTOToCat(CatCreateDTO catDTO) {
        return Cat
                .catBuilder()
                .name(catDTO.getName())
                .age(catDTO.getAge())
                .birthday(catDTO.getBirthday())
                .color(catDTO.getColor())
                .build();
    }

    public static CatResponseDTO convertCatToCatResponseDTO(Cat cat) {
        return CatResponseDTO
                .catResponseDTOBuilder()
                .id(cat.getId())
                .name(cat.getName())
                .age(cat.getAge())
                .petType(cat.getPetType())
                .birthday(cat.getBirthday())
                .owner(convertOwnerToOwnerResponseDTOWithoutPets(cat.getOwner()))
                .color(cat.getColor())
                .build();
    }

    public static CatResponseDTO convertCatToCatResponseDTOWithoutOwner(Cat cat) {
        return CatResponseDTO
                .catResponseDTOBuilder()
                .id(cat.getId())
                .name(cat.getName())
                .age(cat.getAge())
                .petType(cat.getPetType())
                .birthday(cat.getBirthday())
                .color(cat.getColor())
                .build();
    }

    public static List<CatResponseDTO> convertListOfCatsToListOfCatResponseDTO(List<Cat> cats) {
        return cats
                .stream()
                .map(CatMapper::convertCatToCatResponseDTO)
                .collect(Collectors.toList());
    }

}
