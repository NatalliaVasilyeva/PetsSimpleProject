package com.leverx.nvasilyeva.pet.controller;

import com.leverx.nvasilyeva.pet.dto.response.PetResponseDTO;
import com.leverx.nvasilyeva.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
public class PetController {

    private PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDTO> getById(@PathVariable("id") long petId){
        return ResponseEntity.status(HttpStatus.OK).body(petService.findById(petId));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PetResponseDTO> delete(@PathVariable("id") long petId) {
        petService.deleteById(petId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PetResponseDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(petService.findAllPets());
    }

    @DeleteMapping
    public ResponseEntity<PetResponseDTO> deleteAll() {
        petService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
