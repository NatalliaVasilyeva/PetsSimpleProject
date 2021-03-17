package com.leverx.nvasilyeva.pet.service;

import com.leverx.nvasilyeva.pet.dto.request.CatCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.CatResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CatService {
    CatResponseDTO save(CatCreateDTO catCreateDTO);
    List<CatResponseDTO> saveAll(List<CatCreateDTO> catCreateDTOList);
    CatResponseDTO update(long id, CatCreateDTO catCreateDTO);
    CatResponseDTO findById(long id);
    void deleteById(long id);
    List<CatResponseDTO> findAllCats();
    List<CatResponseDTO> findAllByColor (String color);
}
