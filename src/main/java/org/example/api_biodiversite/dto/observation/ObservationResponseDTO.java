package org.example.api_biodiversite.dto.observation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api_biodiversite.dto.specie.SpecieResponseDTO;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObservationResponseDTO {
    private Long observationId;
    private String observerName;
    private String location;
    private Double latitude;
    private Double longitude;
    private LocalDate observationDate;
    private String comment;
    private SpecieResponseDTO specie;
}
