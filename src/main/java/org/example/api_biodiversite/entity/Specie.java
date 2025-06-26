package org.example.api_biodiversite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api_biodiversite.dto.specie.SpecieResponseDTO;
import org.example.api_biodiversite.utils.Category;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Specie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specieId;
    private String commonName;
    private String scientificName;
    private Category category;

    public SpecieResponseDTO entityToDTO(){
        return SpecieResponseDTO.builder()
                .specieId(getSpecieId())
                .commonName(getCommonName())
                .scientificName(getScientificName())
                .category(getCategory().name())
                .build();
    }
}
