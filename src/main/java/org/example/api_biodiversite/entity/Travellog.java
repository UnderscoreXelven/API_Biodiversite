package org.example.api_biodiversite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api_biodiversite.utils.TravelMode;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Travellog {
    private Long travellogId;
    @ManyToOne
    @JoinColumn(name = "observationId")
    private Observation observation;
    private Double distanceKm;
    private TravelMode mode;
    private Double estimatedCo2Kg;
}
