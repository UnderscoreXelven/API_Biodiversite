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

    @GetMapping
    public ResponseEntity<List<SpecieResponseDTO>> getAllSpecie(){
        return ResponseEntity.ok(specieService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecieResponseDTO> getSpecieById(@PathVariable("id") Long id){
        return ResponseEntity.ok(specieService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SpecieResponseDTO> addSpecie(@RequestBody SpecieReceiveDTO specieReceiveDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(specieService.save(specieReceiveDTO));
    }
}
