package com.leverx.nvasilyeva.pet.service;

import com.leverx.nvasilyeva.pet.dto.request.OwnerCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.CatResponseDTO;
import com.leverx.nvasilyeva.pet.dto.response.DogResponseDTO;
import com.leverx.nvasilyeva.pet.dto.response.OwnerResponseDTO;
import com.leverx.nvasilyeva.pet.dto.response.PetResponseDTO;

import java.util.List;

public interface OwnerService {
    OwnerResponseDTO findById(long ownerId);
    OwnerResponseDTO save(OwnerCreateDTO owner);
    List<OwnerResponseDTO> saveAll(List<OwnerCreateDTO> owners);
    void deleteById(long ownerId);
    OwnerResponseDTO update(long id, OwnerCreateDTO owner);
    List<OwnerResponseDTO> findAllOwners();
    void deleteAll();
    List<PetResponseDTO> getAllOwnerPets(long ownerId);
    List<PetResponseDTO> getAllOwnerPetsByPetType(long ownerId, String petType);
    List<PetResponseDTO> getAllOwnerDogs(long ownerId);
    List<PetResponseDTO> getAllOwnerCats(long ownerId);

}
