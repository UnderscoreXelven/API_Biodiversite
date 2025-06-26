package org.example.api_biodiversite.dto.travellog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api_biodiversite.utils.TravelMode;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravellogSummaryResponseDTO {
    private double totalKm;
    private double totalEmissionKg;
    private Map<String, Double> byMode;
}