package org.example.api_biodiversite.dto.travellog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api_biodiversite.entity.Travellog;
import org.example.api_biodiversite.utils.TravelMode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravellogReceiveDTO {
    private Double distanceKm;
    private String mode;
    private Double estimatedCo2Kg;

    public Travellog dtoToEntity(){
        return Travellog.builder()
                .distanceKm(getDistanceKm())
                .mode(TravelMode.valueOf(getMode().toUpperCase()))
                .estimatedCo2Kg(getEstimatedCo2Kg())
                .build();
    }
}
