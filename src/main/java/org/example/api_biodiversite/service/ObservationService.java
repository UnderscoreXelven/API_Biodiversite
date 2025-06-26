package org.example.api_biodiversite.service;

import org.example.api_biodiversite.dto.observation.ObservationReceiveDTO;
import org.example.api_biodiversite.dto.observation.ObservationResponseDTO;
import org.example.api_biodiversite.entity.Observation;
import org.example.api_biodiversite.entity.Specie;
import org.example.api_biodiversite.exception.NotFoundException;
import org.example.api_biodiversite.repository.ObservationRepository;
import org.example.api_biodiversite.repository.SpecieRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ObservationService extends BaseService<Observation, Long, ObservationResponseDTO, ObservationReceiveDTO> {

    private final ObservationRepository observationRepository;
    private final SpecieRepository specieRepository;

    public ObservationService(ObservationRepository observationRepository, SpecieRepository specieRepository) {
        this.observationRepository = observationRepository;
        this.specieRepository = specieRepository;
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

    @Override
    public ObservationResponseDTO save(ObservationReceiveDTO dto){
      Specie specie = specieRepository.findById(dto.getSpecieId()).orElseThrow(NotFoundException::new);
      Observation observation = dto.dtoToEntity();
      observation.setSpecie(specie);
      return observationRepository.save(observation).entityToDTO();
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

    public List<ObservationResponseDTO> getAllWithFilters(Long specieId, String observerName, LocalDate startDate, LocalDate endDate) {
        boolean hasSpecie = specieId != null;
        boolean hasObserver = observerName != null && !observerName.isBlank();
        boolean hasDateRange = startDate != null && endDate != null;

        // Crée une clé string en concaténant les filtres actifs
        String key = (hasSpecie ? "S" : "") + (hasObserver ? "O" : "") + (hasDateRange ? "D" : "");

        List<Observation> observations;

        switch (key) {
            case "SOD": // Specie + Observer + Date range
                observations = observationRepository.findBySpecie_SpecieIdAndObserverNameContainingIgnoreCaseAndObservationDateBetween(specieId, observerName, startDate, endDate);
                break;
            case "SO": // Specie + Observer
                observations = observationRepository.findBySpecie_SpecieIdAndObserverNameContainingIgnoreCase(specieId, observerName);
                break;
            case "SD": // Specie + Date range
                observations = observationRepository.findBySpecie_SpecieIdAndObservationDateBetween(specieId, startDate, endDate);
                break;
            case "OD": // Observer + Date range
                observations = observationRepository.findByObserverNameContainingIgnoreCaseAndObservationDateBetween(observerName, startDate, endDate);
                break;
            case "S": // Specie seul
                observations = observationRepository.findBySpecie_SpecieId(specieId);
                break;
            case "O": // Observer seul
                observations = observationRepository.findByObserverNameContainingIgnoreCase(observerName);
                break;
            case "D": // Date range seul
                observations = observationRepository.findByObservationDateBetween(startDate, endDate);
                break;
            default: // aucun filtre
                observations = observationRepository.findAll();
        }

        return observations.stream().map(Observation::entityToDTO).toList();
    }

}
