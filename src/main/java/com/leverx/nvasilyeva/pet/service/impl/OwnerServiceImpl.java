package com.leverx.nvasilyeva.pet.service.impl;


import com.leverx.nvasilyeva.pet.dto.request.OwnerCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.OwnerResponseDTO;
import com.leverx.nvasilyeva.pet.dto.response.PetResponseDTO;
import com.leverx.nvasilyeva.pet.entity.Owner;
import com.leverx.nvasilyeva.pet.entity.PetType;
import com.leverx.nvasilyeva.pet.entity.Role;
import com.leverx.nvasilyeva.pet.exception.NoSuchElementFoundException;
import com.leverx.nvasilyeva.pet.repository.OwnerRepository;
import com.leverx.nvasilyeva.pet.repository.PetRepository;
import com.leverx.nvasilyeva.pet.service.OwnerService;
import com.leverx.nvasilyeva.pet.utils.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.leverx.nvasilyeva.pet.dto.mapper.OwnerMapper.*;
import static com.leverx.nvasilyeva.pet.dto.mapper.PetMapper.convertListOfPetsToListOfPetResponseDTO;
import static java.util.Objects.nonNull;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final PasswordEncoder passwordEncoder;
    private final DataValidator dataValidator;
    private final OwnerRepository ownerRepository;
    private final DataValidator validator;
    private final PetRepository petRepository;


    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository,
                            DataValidator validator,
                            PetRepository petRepository,
                            PasswordEncoder passwordEncoder, DataValidator dataValidator) {
        this.ownerRepository = ownerRepository;
        this.validator = validator;
        this.petRepository = petRepository;
        this.passwordEncoder = passwordEncoder;
        this.dataValidator = dataValidator;
    }

    @Override
    public OwnerResponseDTO findById(final long ownerId) {
        return convertOwnerToOwnerResponseDTO(ownerRepository
                .findById(ownerId)
                .orElseThrow(() -> new NoSuchElementFoundException("No such owner")));

    }

    @Override
    public OwnerResponseDTO save(OwnerCreateDTO ownerCreateDTO) {

        validator.validateOwnerPassword(ownerCreateDTO);
        validateOwnersByEmails(ownerCreateDTO);
        Owner owner=createOrUpdateOwner(ownerCreateDTO);
        return convertOwnerToOwnerResponseDTOWithoutPets(ownerRepository.save(owner));
    }

    @Override
    public List<OwnerResponseDTO> saveAll(List<OwnerCreateDTO> owners) {
        return owners
                .stream()
                .map(this::save)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteById(final long ownerId) {

        validateOwnersById(ownerId);
        petRepository.deleteReferencesToOwner(ownerId);
        ownerRepository.deleteById(ownerId);
    }

    @Override
    public OwnerResponseDTO update(long ownerId, OwnerCreateDTO ownerCreateDTO) {
        validateOwnersById(ownerId);
        Owner owner=createOrUpdateOwner(ownerCreateDTO);
        return convertOwnerToOwnerResponseDTOWithoutPets(ownerRepository.save(owner));
    }

    @Override
    public List<OwnerResponseDTO> findAllOwners() {
        return convertListOfOwnersToListOfOwnerResponseDTO(ownerRepository.findAll());
    }

    @Override
    public void deleteAll() {
        ownerRepository.deleteAll();
    }

    @Override
    public List<PetResponseDTO> getAllOwnerPets(long ownerId) {
        validateOwnersById(ownerId);
        return convertListOfPetsToListOfPetResponseDTO(ownerRepository.getOne(ownerId).getPets());
    }

    @Override
    public List<PetResponseDTO> getAllOwnerPetsByPetType(long ownerId, PetType petType) {
        validateOwnersById(ownerId);
        if (nonNull(petType)&&validator.isCorrectPetType(petType.name())) {
            return convertListOfPetsToListOfPetResponseDTO(petRepository.findAllByOrderByOwnerIdAndPetType(ownerId, petType));
        };

        return convertListOfPetsToListOfPetResponseDTO(ownerRepository.getOne(ownerId).getPets());
    }


    private void validateOwnersByEmails(OwnerCreateDTO ownerDTO) {
        if (ownerRepository.isExistByEmail(ownerDTO.getEmail())) {
            throw new IllegalArgumentException("User exist with this email. Please, use other email");
        }
    }

    private void validateOwnersById(final long ownerId) {
        if (!ownerRepository.existsById(ownerId)) {
            throw new NoSuchElementFoundException("Owner with such id is not founded");
        }
    }

    private String encodePassword(OwnerCreateDTO owner) {
        return passwordEncoder.encode(owner.getPassword());
    }

    private Owner createOrUpdateOwner(OwnerCreateDTO ownerCreateDTO) {
        Owner owner = convertOwnerCreateDTOToOwner(ownerCreateDTO);
        owner.setPassword(encodePassword(ownerCreateDTO));

        String role = ownerCreateDTO.getRole();
        if (dataValidator.isCorrectRole(role) && !role.toUpperCase(Locale.ROOT).equals("ADMIN")) {
            owner.setRole(Role.valueOf(role));
        } else {
            owner.setRole(Role.USER);
        }
        return owner;
    }
}
