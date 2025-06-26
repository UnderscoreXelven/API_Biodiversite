package org.example.api_biodiversite.dto.specie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api_biodiversite.entity.Specie;
import org.example.api_biodiversite.utils.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecieReceiveDTO {
    private String commonName;
    private String scientificName;
    private String category;

    public Specie dtoToEntity(){
        return Specie.builder()
                .commonName(getCommonName())
                .scientificName(getScientificName())
                .category(Category.valueOf(getCategory().toUpperCase()))
                .build();
    }
}
