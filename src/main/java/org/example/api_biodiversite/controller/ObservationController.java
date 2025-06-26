package org.example.api_biodiversite.controller;

import org.example.api_biodiversite.dto.observation.ObservationReceiveDTO;
import org.example.api_biodiversite.dto.observation.ObservationResponseDTO;
import org.example.api_biodiversite.service.ObservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/observations")
public class ObservationController {
    private final ObservationService observationService;

    public ObservationController(ObservationService observationService) {
        this.observationService = observationService;
    }

    //Get all observations with possible filters
    @GetMapping
    public ResponseEntity<List<ObservationResponseDTO>> getAllObservations(
            @RequestParam(required = false) Long specieId,
            @RequestParam(required = false) String observerName,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate
    ) {
        List<ObservationResponseDTO> observations = observationService.getAllWithFilters(specieId, observerName, startDate, endDate);
        return ResponseEntity.ok(observations);
    }

    //Get one specific observation by id
    @GetMapping("/{id}")
    public ResponseEntity<ObservationResponseDTO> getObservationById(@PathVariable("id") Long observationId){
        return ResponseEntity.ok(observationService.getById(observationId));
    }

    //Create a new observation
    @PostMapping
    public ResponseEntity<ObservationResponseDTO> saveObservation(@RequestBody ObservationReceiveDTO observationDTO){
        return ResponseEntity.ok(observationService.save(observationDTO));
    }


}
