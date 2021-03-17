package com.leverx.nvasilyeva.pet.service;

import com.leverx.nvasilyeva.pet.dto.request.DogCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.DogResponseDTO;

import java.util.List;

public interface DogService {
   DogResponseDTO save(DogCreateDTO dogCreateDTO);
    List<DogResponseDTO> saveAll(List<DogCreateDTO> dogCreateDTOList);
    DogResponseDTO update(long id, DogCreateDTO dogCreateDTO);
    DogResponseDTO findById(long id);
    void deleteById(long id);
    List<DogResponseDTO> findAllDogs();
    List<DogResponseDTO> findAllBySize (String size);
}
