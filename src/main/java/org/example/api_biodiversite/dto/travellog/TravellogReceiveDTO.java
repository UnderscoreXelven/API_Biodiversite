package org.example.api_biodiversite.dto.travellog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api_biodiversite.entity.Observation;
import org.example.api_biodiversite.entity.Travellog;
import org.example.api_biodiversite.utils.TravelMode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravellogReceiveDTO {
    private Long observationId;
    private Double distanceKm;
    private String mode;
    private Double estimatedCo2Kg;

    public Travellog dtoToEntity(){
        Observation observation = Observation.builder().observationId(observationId).build();
        return Travellog.builder()
                .observation(observation)
                .distanceKm(getDistanceKm())
                .mode(TravelMode.valueOf(getMode().toUpperCase()))
                .estimatedCo2Kg(getEstimatedCo2Kg())
                .build();
    }
}
