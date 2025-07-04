package org.example.api_biodiversite.dto.observation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api_biodiversite.entity.Observation;
import org.example.api_biodiversite.entity.Specie;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObservationReceiveDTO {
    private Long specieId;
    private String observerName;
    private String location;
    private Double latitude;
    private Double longitude;
    private LocalDate observationDate;
    private String comment;
    
    public Observation dtoToEntity(){
        Specie specie = Specie.builder().specieId(specieId).build();

        return Observation.builder()
                .specie(specie)
                .observerName(getObserverName())
                .localisation(getLocation())
                .latitude(getLatitude())
                .longitude(getLongitude())
                .observationDate(getObservationDate())
                .comment(getComment())
                .build();
    }
}
