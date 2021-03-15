package com.leverx.nvasilyeva.pet.service.impl;

import com.leverx.nvasilyeva.pet.dto.response.PetResponseDTO;
import com.leverx.nvasilyeva.pet.exception.NoSuchElementFoundException;
import com.leverx.nvasilyeva.pet.repository.PetRepository;
import com.leverx.nvasilyeva.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.leverx.nvasilyeva.pet.dto.mapper.PetMapper.convertListOfPetsToListOfPetResponseDTO;
import static com.leverx.nvasilyeva.pet.dto.mapper.PetMapper.convertPetToPetResponseDTO;

@Service
public class PetServiceImpl implements PetService {
    private PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public PetResponseDTO findById(long petId) {
        return convertPetToPetResponseDTO(
                petRepository.findById(petId)
                        .orElseThrow(() -> new NoSuchElementFoundException("No such pet")));

    }

    @Override
    public void deleteById(long petId) {
        if (!petRepository.existsById(petId)) {
            throw new NoSuchElementFoundException("No such pet");
        }
        petRepository.deleteById(petId);
    }

    @Override
    public List<PetResponseDTO> findAllPets() {

        return convertListOfPetsToListOfPetResponseDTO(petRepository.findAll());
    }

    @Override
    public void deleteAll() {
        petRepository.deleteAll();

    }
}
