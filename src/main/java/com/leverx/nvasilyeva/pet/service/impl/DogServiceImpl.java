package com.leverx.nvasilyeva.pet.service.impl;

import com.leverx.nvasilyeva.pet.dto.mapper.DogMapper;
import com.leverx.nvasilyeva.pet.dto.request.DogCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.DogResponseDTO;
import com.leverx.nvasilyeva.pet.entity.Dog;
import com.leverx.nvasilyeva.pet.entity.PetType;
import com.leverx.nvasilyeva.pet.entity.Size;
import com.leverx.nvasilyeva.pet.exception.NoSuchElementFoundException;
import com.leverx.nvasilyeva.pet.repository.DogRepository;
import com.leverx.nvasilyeva.pet.repository.OwnerRepository;
import com.leverx.nvasilyeva.pet.repository.PetRepository;
import com.leverx.nvasilyeva.pet.service.DogService;
import com.leverx.nvasilyeva.pet.utils.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogServiceImpl implements DogService {

    private DogRepository dogRepository;
    private OwnerRepository ownerRepository;
    private PetRepository petRepository;
    private DataValidator validator;

    @Autowired
    public DogServiceImpl(DogRepository dogRepository, OwnerRepository ownerRepository, PetRepository petRepository, DataValidator validator) {
        this.dogRepository = dogRepository;
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.validator = validator;
    }

    @Override
    public DogResponseDTO save(DogCreateDTO dogCreateDTO) {
        Dog dog = saveOrUpdateDogUtil(dogCreateDTO);

        return DogMapper.convertDogToDogResponseDTO(dogRepository.save(dog));
    }

    @Override
    public List<DogResponseDTO> saveAll(List<DogCreateDTO> dogCreateDTOList) {

        List<Dog> dogs = dogCreateDTOList
                .stream()
                .map(this::saveOrUpdateDogUtil)
                .collect(Collectors.toList());


        return DogMapper.convertListOfDogsToListOfDogsResponseDTO(dogRepository.saveAll(dogs));
    }

    @Override
    public DogResponseDTO update(long id, DogCreateDTO dogCreateDTO) {

        Dog dog;

        if (!dogRepository.existsById(id)) {
            throw new NoSuchElementFoundException("Dog with such id doesn't exist");
        } else {
            dog = saveOrUpdateDogUtil(dogCreateDTO);
            dog.setId(findById(id).getId());
        }

        return DogMapper.convertDogToDogResponseDTO(dogRepository.save(dog));
    }

    @Override
    public DogResponseDTO findById(long id) {

        return DogMapper.convertDogToDogResponseDTO(
                dogRepository
                        .findById(id)
                        .orElseThrow(() -> new NoSuchElementFoundException("Dog with such id doesn't exist")));
    }

    @Override
    public void deleteById(long id) {

        if (!dogRepository.existsById(id)) {
            throw new NoSuchElementFoundException("Dog with such id doesn't exist");
        }
        dogRepository.deleteById(id);
    }

    @Override
    public List<DogResponseDTO> findAllDogs() {

        return DogMapper.convertListOfDogsToListOfDogsResponseDTO(dogRepository.findAll());
    }

    @Override
    public List<DogResponseDTO> findAllBySize(String size) {
        if (!validator.isCorrectSize(size)) {
            throw new IllegalArgumentException("Size is not correct. Please choose small, medium or big");
        }
        return DogMapper.convertListOfDogsToListOfDogsResponseDTO(dogRepository.findAllBySize(Size.valueOf(size)));
    }

    private Dog saveOrUpdateDogUtil(DogCreateDTO dogCreateDTO) {
        Dog dog = DogMapper.convertDogCreateDTOToDog(dogCreateDTO);
        dog.setOwner(ownerRepository.findById(dogCreateDTO.getOwnerId()).orElse(null));
        if (validator.isDogType(dogCreateDTO.getPetType())) {
            dog.setPetType(PetType.DOG);
        } else {
            throw new IllegalArgumentException("This not a dog. Please select needed category or change type of pet ");
        }
        return dog;
    }
}
