package com.leverx.nvasilyeva.pet.service.impl;

import com.leverx.nvasilyeva.pet.dto.mapper.CatMapper;
import com.leverx.nvasilyeva.pet.dto.request.CatCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.CatResponseDTO;
import com.leverx.nvasilyeva.pet.entity.Cat;
import com.leverx.nvasilyeva.pet.entity.PetType;
import com.leverx.nvasilyeva.pet.exception.NoSuchElementFoundException;
import com.leverx.nvasilyeva.pet.repository.CatRepository;
import com.leverx.nvasilyeva.pet.repository.OwnerRepository;
import com.leverx.nvasilyeva.pet.service.CatService;
import com.leverx.nvasilyeva.pet.utils.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatServiceImpl implements CatService {

    private CatRepository catRepository;
    private OwnerRepository ownerRepository;
    private DataValidator validator;

    @Autowired
    public CatServiceImpl(CatRepository catRepository, OwnerRepository ownerRepository, DataValidator validator) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
        this.validator = validator;
    }

    @Override
    public CatResponseDTO save(CatCreateDTO catCreateDTO) {
        Cat cat = saveOrUpdateCatUtil(catCreateDTO);

        return CatMapper.convertCatToCatResponseDTO(catRepository.save(cat));
    }

    @Override
    public List<CatResponseDTO> saveAll(List<CatCreateDTO> catCreateDTOList) {

        List<Cat> cats = catCreateDTOList
                .stream()
                .map(this::saveOrUpdateCatUtil)
                .collect(Collectors.toList());


        return CatMapper.convertListOfCatsToListOfCatResponseDTO(catRepository.saveAll(cats));

    }

    @Override
    public CatResponseDTO update(long id, CatCreateDTO catCreateDTO) {
        Cat cat;

        if (!catRepository.existsById(id)) {
            throw new NoSuchElementFoundException("Cat with such id doesn't exist");
        } else {
            cat = saveOrUpdateCatUtil(catCreateDTO);
            cat.setId(findById(id).getId());
        }

        return CatMapper.convertCatToCatResponseDTO(catRepository.save(cat));
    }

    @Override
    public CatResponseDTO findById(long id) {
        return CatMapper.convertCatToCatResponseDTO(
                catRepository
                        .findById(id)
                        .orElseThrow(() -> new NoSuchElementFoundException("Cat with such id doesn't exist")));
    }

    @Override
    public void deleteById(long id) {

        if (!catRepository.existsById(id)) {
            throw new NoSuchElementFoundException("Cat with such id doesn't exist");
        }
        catRepository.deleteById(id);
    }

    @Override
    public List<CatResponseDTO> findAllCats() {
        return CatMapper.convertListOfCatsToListOfCatResponseDTO(catRepository.findAll());
    }

    @Override
    public List<CatResponseDTO> findAllByColor(String color) {
        return CatMapper.convertListOfCatsToListOfCatResponseDTO(catRepository.findAllByColor(color));
    }

    private Cat saveOrUpdateCatUtil(CatCreateDTO catCreateDTO) {
        Cat cat = CatMapper.convertCatCreateDTOToCat(catCreateDTO);
        cat.setOwner(ownerRepository.findById(catCreateDTO.getOwnerId()).orElse(null));
        if (validator.isCatType(catCreateDTO.getPetType())) {
            cat.setPetType(PetType.CAT);
        } else {
            throw new IllegalArgumentException("This not a cat. Please select needed category or change type of pet ");
        }
        return cat;
    }
}
