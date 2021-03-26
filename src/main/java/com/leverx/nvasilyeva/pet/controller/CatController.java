package com.leverx.nvasilyeva.pet.controller;

import com.leverx.nvasilyeva.pet.dto.request.CatCreateDTO;
import com.leverx.nvasilyeva.pet.dto.response.CatResponseDTO;
import com.leverx.nvasilyeva.pet.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.leverx.nvasilyeva.pet.config.ControllerConstant.CAT_API;

@RestController
@RequestMapping(CAT_API)
public class CatController {

    private CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping
    public ResponseEntity<CatResponseDTO> create(@Valid @RequestBody CatCreateDTO catCreateDTO) {
        CatResponseDTO catResponseDTO = catService.save(catCreateDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(catResponseDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(catResponseDTO);
    }

    @PostMapping(value = "/createAll")
    public ResponseEntity<List<CatResponseDTO>> createAll(@Valid @RequestBody List<CatCreateDTO> cats) {
        List<CatResponseDTO> catResponseDTOs = catService.saveAll(cats);
        return ResponseEntity.status(HttpStatus.OK).body(catResponseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatResponseDTO> update(
            @PathVariable("id") Long catId,
            @Valid @RequestBody CatCreateDTO catCreateDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(catService.update(catId, catCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatResponseDTO> getById(@Valid @PathVariable("id") long catId) {
        return ResponseEntity.status(HttpStatus.OK).body(catService.findById(catId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CatResponseDTO> delete(@PathVariable("id") Long catId) {
        catService.deleteById(catId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CatResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(catService.findAllCats());
    }

    @GetMapping("/color")
    public ResponseEntity<List<CatResponseDTO>> getAllCatsByColor(
            @RequestParam(value = "color") String color
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(catService.findAllByColor(color));
    }

}
