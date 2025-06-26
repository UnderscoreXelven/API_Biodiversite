package org.example.api_biodiversite.service;

import org.example.api_biodiversite.dto.observation.ObservationReceiveDTO;
import org.example.api_biodiversite.dto.observation.ObservationResponseDTO;
import org.example.api_biodiversite.entity.Observation;
import org.example.api_biodiversite.exception.NotFoundException;
import org.example.api_biodiversite.repository.ObservationRepository;
import org.example.api_biodiversite.repository.SpecieRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ObservationService extends BaseService<Observation, Long, ObservationResponseDTO, ObservationReceiveDTO> {

    private final ObservationRepository observationRepository;

    public ObservationService(ObservationRepository observationRepository, SpecieRepository specieRepository) {
        this.observationRepository = observationRepository;
    }

    @Override
    protected JpaRepository<Observation, Long> getRepository() {
        return observationRepository;
    }

    @Override
    protected ObservationResponseDTO toResponseDTO(Observation entity) {
        return entity.entityToDTO();
    }

    @Override
    protected Observation toEntity(ObservationReceiveDTO dto) {
        return dto.dtoToEntity();
    }

    public ObservationResponseDTO update(ObservationReceiveDTO dto, Long observationId) {
        Observation observationFound = observationRepository.findById(observationId).orElseThrow(NotFoundException::new);
        Observation observationUpdated = dto.dtoToEntity();

        observationFound.setSpecie(observationUpdated.getSpecie());
        observationFound.setObserverName(observationUpdated.getObserverName());
        observationFound.setLocalisation(observationUpdated.getLocalisation());
        observationFound.setLatitude(observationUpdated.getLatitude());
        observationFound.setLongitude(observationUpdated.getLongitude());
        observationFound.setObservationDate(observationUpdated.getObservationDate());
        observationFound.setComment(observationUpdated.getComment());

        return observationRepository.save(observationFound).entityToDTO();
    }
}
