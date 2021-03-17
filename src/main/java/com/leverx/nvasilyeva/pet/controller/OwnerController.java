package com.leverx.nvasilyeva.pet.controller;

import com.leverx.nvasilyeva.pet.dto.request.OwnerCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.CatResponseDTO;
import com.leverx.nvasilyeva.pet.dto.response.DogResponseDTO;
import com.leverx.nvasilyeva.pet.dto.response.OwnerResponseDTO;
import com.leverx.nvasilyeva.pet.dto.response.PetResponseDTO;
import com.leverx.nvasilyeva.pet.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<OwnerResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.findAllOwners());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        ownerService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<List<OwnerResponseDTO>> createAll(@Valid @RequestBody List<OwnerCreateDTO> owners) {
        List<OwnerResponseDTO> ownerResponseDTOs = ownerService.saveAll(owners);
        return ResponseEntity.status(HttpStatus.OK).body(ownerResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> getById(@Valid @PathVariable("id") long ownerId) {
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.findById(ownerId));
    }

    @PostMapping
    public ResponseEntity<OwnerResponseDTO> create(@Valid @RequestBody OwnerCreateDTO ownerCreateDTO) {
        OwnerResponseDTO ownerResponseDTO = ownerService.save(ownerCreateDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ownerResponseDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(ownerResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> delete(@PathVariable("id") Long ownerId) {
        ownerService.deleteById(ownerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> update(
            @PathVariable("id") Long ownerId,
            @Valid @RequestBody OwnerCreateDTO ownerCreateDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(ownerService.update(ownerId, ownerCreateDTO));
    }

    @GetMapping("/{id}/pets")
    public ResponseEntity<List<PetResponseDTO>> getAllOwnerPets(
            @PathVariable("id") Long ownerId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.getAllOwnerPets(ownerId));
    }

    @GetMapping("/{id}/type/pets")
    public ResponseEntity<List<PetResponseDTO>> getAllOwnerPetsByType(
            @PathVariable("id") Long ownerId,
            @RequestParam(value = "type", required = false) String type
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.getAllOwnerPetsByPetType(ownerId, type));
    }

    @GetMapping("/{id}/dogs")
    public ResponseEntity<List<DogResponseDTO>> getAllOwnerDogs(
            @PathVariable("id") Long ownerId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.getAllOwnerDogs(ownerId));
    }

    @GetMapping("/{id}/cats")
    public ResponseEntity<List<CatResponseDTO>> getAllOwnerCats(
            @PathVariable("id") Long ownerId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.getAllOwnerCats(ownerId));
    }

}
