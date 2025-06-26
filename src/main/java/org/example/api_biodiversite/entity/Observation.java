package org.example.api_biodiversite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api_biodiversite.dto.observation.ObservationResponseDTO;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long observationId;
    @ManyToOne
    @JoinColumn(name = "specieId")
    private Specie specie;
    private String observerName;
    private String localisation;
    private Double latitude;
    private Double longitude;
    private LocalDate observationDate;
    private String comment;

    public ObservationResponseDTO entityToDTO(){
        return ObservationResponseDTO.builder()
                .observationId(getObservationId())
                .observerName(getObserverName())
                .location(getLocalisation())
                .latitude(getLatitude())
                .longitude(getLongitude())
                .observationDate(getObservationDate())
                .comment(getComment())
                .specie(getSpecie().entityToDTO())
                .build();
    }
}
