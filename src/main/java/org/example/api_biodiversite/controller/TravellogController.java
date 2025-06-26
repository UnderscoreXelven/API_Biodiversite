package org.example.api_biodiversite.controller;

import org.example.api_biodiversite.dto.travellog.TravellogReceiveDTO;
import org.example.api_biodiversite.dto.travellog.TravellogResponseDTO;
import org.example.api_biodiversite.dto.travellog.TravellogSummaryResponseDTO;
import org.example.api_biodiversite.service.TravellogService;
import org.example.api_biodiversite.utils.TravelMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/travel-logs")
public class TravellogController {
    private final TravellogService travellogService;

    public TravellogController(TravellogService travellogService) {
        this.travellogService = travellogService;
    }

    //Get all travel logs
    @GetMapping
    public ResponseEntity<List<TravellogResponseDTO>> getAllTravellog(){
        return ResponseEntity.ok(travellogService.getAll());
    }

    //Get one specific travel log by id
    @GetMapping("/stats/{idObservation}")
    public ResponseEntity<TravellogSummaryResponseDTO> getStatsByObservation(@PathVariable("idObservation") Long idObservation){
        List<TravellogResponseDTO> logs = travellogService.getAllByObservationId(idObservation);
        double distanceKm = logs.stream().mapToDouble(TravellogResponseDTO::getDistanceKm).sum();
        double estimatedCo2Kg = logs.stream().mapToDouble(TravellogResponseDTO::getEstimatedCo2Kg).sum();

        TravellogSummaryResponseDTO summary = TravellogSummaryResponseDTO.builder()
                .totalKm(distanceKm)
                .totalEmissionKg(estimatedCo2Kg)
                .byMode(
                        logs.stream().collect(Collectors.toMap(TravellogResponseDTO::getMode, TravellogResponseDTO::getEstimatedCo2Kg))
                )
                .build();
        return ResponseEntity.ok(summary);
    }

    //Create a new travel log
    @PostMapping
    public ResponseEntity<TravellogResponseDTO> addTravellog(@RequestBody TravellogReceiveDTO travellogReceiveDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(travellogService.save(travellogReceiveDTO));
    }
}
