package com.leverx.nvasilyeva.pet.service;

import com.leverx.nvasilyeva.pet.dto.request.OwnerCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.OwnerResponseDTO;
import com.leverx.nvasilyeva.pet.dto.response.PetResponseDTO;
import com.leverx.nvasilyeva.pet.entity.Owner;

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
    Owner findByUsername(String username);
    Owner findByUsernameAndPassword(String username, String password);

}
