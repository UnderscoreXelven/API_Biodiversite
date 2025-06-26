package org.example.api_biodiversite.dto.travellog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravellogReceiveDTO {
    private Double distanceKm;
    private String mode;
    private Double estimatedCo2Kg;
}
