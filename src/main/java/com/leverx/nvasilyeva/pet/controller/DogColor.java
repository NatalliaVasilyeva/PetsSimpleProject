package com.leverx.nvasilyeva.pet.controller;

import com.leverx.nvasilyeva.pet.dto.request.DogCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.CatResponseDTO;
import com.leverx.nvasilyeva.pet.dto.response.DogResponseDTO;
import com.leverx.nvasilyeva.pet.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.leverx.nvasilyeva.pet.config.ControllerConstant.DOG_API;

@RestController
@RequestMapping(DOG_API)
public class DogColor {

    private DogService dogService;

    @Autowired
    public DogColor(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping
    public ResponseEntity<DogResponseDTO> create(@Valid @RequestBody DogCreateDTO dogCreateDTO) {
        DogResponseDTO dogResponseDTO = dogService.save(dogCreateDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dogResponseDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(dogResponseDTO);
    }

    @PostMapping("/createAll")
    public ResponseEntity<List<DogResponseDTO>> createAll(@Valid @RequestBody List<DogCreateDTO> dogs) {
        List<DogResponseDTO> dogResponseDTOs = dogService.saveAll(dogs);
        return ResponseEntity.status(HttpStatus.OK).body(dogResponseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DogResponseDTO> update(
            @PathVariable("id") Long dogId,
            @Valid @RequestBody DogCreateDTO dogCreateDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(dogService.update(dogId, dogCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DogResponseDTO> getById(@Valid @PathVariable("id") long dogId) {
        return ResponseEntity.status(HttpStatus.OK).body(dogService.findById(dogId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CatResponseDTO> delete(@PathVariable("id") Long dogId) {
        dogService.deleteById(dogId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DogResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(dogService.findAllDogs());
    }

    @GetMapping("/size")
    public ResponseEntity<List<DogResponseDTO>> getAllDogsBySize(
            @RequestParam(value = "size") String size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(dogService.findAllBySize(size));
    }
}
