package org.example.api_biodiversite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api_biodiversite.dto.travellog.TravellogResponseDTO;
import org.example.api_biodiversite.utils.TravelMode;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Travellog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travellogId;
    @ManyToOne
    @JoinColumn(name = "observationId")
    private Observation observation;
    private Double distanceKm;
    private TravelMode mode;
    private Double estimatedCo2Kg;

    public TravellogResponseDTO entityToDTO(){
        return TravellogResponseDTO.builder()
                .travellogId(getTravellogId())
                .distanceKm(getDistanceKm())
                .mode(getMode().name())
                .estimatedCo2Kg(getEstimatedCo2Kg())
                .build();
    }
}
