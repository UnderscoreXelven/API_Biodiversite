package org.example.api_biodiversite.controller;

import org.example.api_biodiversite.dto.specie.SpecieReceiveDTO;
import org.example.api_biodiversite.dto.specie.SpecieResponseDTO;
import org.example.api_biodiversite.service.SpecieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/species")
public class SpecieController {
    private final SpecieService specieService;

    public SpecieController(SpecieService specieService) {
        this.specieService = specieService;
    }

    //Get all species
    @GetMapping
    public ResponseEntity<List<SpecieResponseDTO>> getAllSpecie(){
        return ResponseEntity.ok(specieService.getAll());
    }

    //Get one specific species by id
    @PutMapping("/{id}")
    public ResponseEntity<SpecieResponseDTO> updateSpecie(@RequestBody SpecieReceiveDTO specieReceiveDTO, @PathVariable("id") Long id){
        return ResponseEntity.ok(specieService.update(specieReceiveDTO, id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<SpecieResponseDTO> getSpecieById(@PathVariable("id") Long id){
        return ResponseEntity.ok(specieService.getById(id));
    }

    //Create a new species
    @PostMapping
    public ResponseEntity<SpecieResponseDTO> addSpecie(@RequestBody SpecieReceiveDTO specieReceiveDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(specieService.save(specieReceiveDTO));
    }
}
