package org.example.api_biodiversite.service;

import org.example.api_biodiversite.dto.travellog.TravellogReceiveDTO;
import org.example.api_biodiversite.dto.travellog.TravellogResponseDTO;
import org.example.api_biodiversite.entity.Travellog;
import org.example.api_biodiversite.exception.NotFoundException;
import org.example.api_biodiversite.repository.ObservationRepository;
import org.example.api_biodiversite.repository.TravellogRepository;
import org.example.api_biodiversite.utils.TravelMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravellogService extends BaseService<Travellog, Long, TravellogResponseDTO, TravellogReceiveDTO> {
    private final TravellogRepository travellogRepository;
    private final ObservationRepository observationRepository;

    public TravellogService(TravellogRepository travellogRepository, ObservationRepository observationRepository) {
        this.travellogRepository = travellogRepository;
        this.observationRepository = observationRepository;
    }

    @Override
    protected JpaRepository<Travellog, Long> getRepository() {
        return travellogRepository;
    }

    @Override
    protected TravellogResponseDTO toResponseDTO(Travellog entity) {
        return entity.entityToDTO();
    }

    @Override
    protected Travellog toEntity(TravellogReceiveDTO dto) {
        return dto.dtoToEntity();
    }

    @Override
    public TravellogResponseDTO save(TravellogReceiveDTO dto){
        Travellog travellog = dto.dtoToEntity();
        //Calc estimated CO2
        travellog.setEstimatedCo2Kg(calcCO2(travellog.getMode(), travellog.getDistanceKm()));

        travellog.setObservation(observationRepository.findById(travellog.getObservation().getObservationId()).orElseThrow(NotFoundException::new));
        return travellogRepository.save(travellog).entityToDTO();
    }

    public TravellogResponseDTO update(TravellogReceiveDTO travellogReceiveDTO, Long travellogId){
        Travellog travellogFound = travellogRepository.findById(travellogId).orElseThrow(NotFoundException::new);
        Travellog travellogUpdated = travellogReceiveDTO.dtoToEntity();

        travellogFound.setMode(travellogUpdated.getMode());
        travellogFound.setDistanceKm(travellogUpdated.getDistanceKm());

        travellogFound.setEstimatedCo2Kg(calcCO2(travellogFound.getMode(), travellogFound.getDistanceKm()));

        travellogFound.setObservation(travellogUpdated.getObservation());

        return travellogRepository.save(travellogFound).entityToDTO();
    }

    public List<TravellogResponseDTO> getAllByObservationId(Long observationId){
        return travellogRepository.findAllByObservation_ObservationId(observationId).stream().map(Travellog::entityToDTO).toList();
    }

    //Calculates CO2 emissions based on the mode of transport and distance
    public Double calcCO2(TravelMode mode, Double distanceKm){
        double co2 = 0.0;
        switch(mode){
            case WALKING, BIKE -> co2 = 0.0;
            case TRAIN -> co2 = 0.03 * distanceKm;
            case BUS -> co2 =  0.11 * distanceKm;
            case CAR -> co2 = 0.22 * distanceKm;
            case PLANE -> co2 =  0.259 * distanceKm;
            default -> throw new IllegalArgumentException("Mode de transport inconnu : " + mode);
        }
        return co2;
    }
}
