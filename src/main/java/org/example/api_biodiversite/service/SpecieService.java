package org.example.api_biodiversite.service;

import org.example.api_biodiversite.dto.specie.SpecieReceiveDTO;
import org.example.api_biodiversite.dto.specie.SpecieResponseDTO;
import org.example.api_biodiversite.entity.Specie;
import org.example.api_biodiversite.exception.NotFoundException;
import org.example.api_biodiversite.repository.SpecieRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecieService extends BaseService<Specie, Long, SpecieResponseDTO, SpecieReceiveDTO> {
    private final SpecieRepository specieRepository;

    public SpecieService(SpecieRepository specieRepository) {
        this.specieRepository = specieRepository;
    }

    @Override
    protected JpaRepository<Specie, Long> getRepository() {
        return specieRepository;
    }

    @Override
    protected SpecieResponseDTO toResponseDTO(Specie entity) {
        return entity.entityToDTO();
    }

    @Override
    protected Specie toEntity(SpecieReceiveDTO dto) {
        return dto.dtoToEntity();
    }

    public SpecieResponseDTO update(SpecieReceiveDTO specieReceiveDTO, Long specieId) {
        Specie specieFound = specieRepository.findById(specieId).orElseThrow(NotFoundException::new);
        Specie specieUpdated = specieReceiveDTO.dtoToEntity();

        specieFound.setCommonName(specieUpdated.getCommonName());
        specieFound.setScientificName(specieUpdated.getScientificName());
        specieFound.setCategory(specieUpdated.getCategory());

        Specie saved = specieRepository.save(specieFound);
        return saved.entityToDTO();
    }

}

