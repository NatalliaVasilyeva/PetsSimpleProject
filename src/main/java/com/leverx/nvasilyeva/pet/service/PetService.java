package com.leverx.nvasilyeva.pet.service;

import com.leverx.nvasilyeva.pet.dto.response.PetResponseDTO;

import java.util.List;

public interface PetService {
    PetResponseDTO findById(long petId);
    void deleteById(long petId);
    List<PetResponseDTO> findAllPets();
    void deleteAll();
}
