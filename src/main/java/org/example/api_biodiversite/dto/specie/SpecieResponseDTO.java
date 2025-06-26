package org.example.api_biodiversite.dto.specie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecieResponseDTO {
    private Long specieId;
    private String commonName;
    private String scientificName;
    private String category;
}
