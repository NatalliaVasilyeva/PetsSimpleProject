package com.leverx.nvasilyeva.pet.dto.mapper;

import com.leverx.nvasilyeva.pet.dto.request.DogCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.DogResponseDTO;
import com.leverx.nvasilyeva.pet.entity.Dog;
import com.leverx.nvasilyeva.pet.entity.Size;

import java.util.List;
import java.util.stream.Collectors;

import static com.leverx.nvasilyeva.pet.dto.mapper.OwnerMapper.convertOwnerToOwnerResponseDTOWithoutPets;

public class DogMapper {

    public static Dog convertDogCreateDTOToDog(DogCreateDTO dogDTO) {
        return Dog
                .dogBuilder()
                .name(dogDTO.getName())
                .age(dogDTO.getAge())
                .birthday(dogDTO.getBirthday())
                .size(Size.valueOf(dogDTO.getSize()))
                .build();
    }

    public static DogResponseDTO convertDogToDogResponseDTO(Dog dog) {
        return DogResponseDTO
                .dogResponseDTOBuilder()
                .id(dog.getId())
                .name(dog.getName())
                .age(dog.getAge())
                .petType(dog.getPetType())
                .birthday(dog.getBirthday())
                .owner(convertOwnerToOwnerResponseDTOWithoutPets(dog.getOwner()))
                .size(dog.getSize())
                .build();
    }

    public static DogResponseDTO convertDogToDogResponseDTOWithoutOwner(Dog dog) {
        return DogResponseDTO
                .dogResponseDTOBuilder()
                .id(dog.getId())
                .name(dog.getName())
                .age(dog.getAge())
                .petType(dog.getPetType())
                .birthday(dog.getBirthday())
                .size(dog.getSize())
                .build();
    }

    public static List<DogResponseDTO> convertListOfDogsToListOfDogsResponseDTO(List<Dog> dogs) {
        return dogs
                .stream()
                .map(DogMapper::convertDogToDogResponseDTO)
                .collect(Collectors.toList());
    }

}
