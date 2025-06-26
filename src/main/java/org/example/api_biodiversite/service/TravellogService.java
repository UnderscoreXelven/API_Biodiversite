package org.example.api_biodiversite.service;

import org.example.api_biodiversite.dto.travellog.TravellogReceiveDTO;
import org.example.api_biodiversite.dto.travellog.TravellogResponseDTO;
import org.example.api_biodiversite.entity.Travellog;
import org.example.api_biodiversite.exception.NotFoundException;
import org.example.api_biodiversite.repository.TravellogRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TravellogService extends BaseService<Travellog, Long, TravellogResponseDTO, TravellogReceiveDTO> {
    private final TravellogRepository travellogRepository;

    public TravellogService(TravellogRepository travellogRepository) {
        this.travellogRepository = travellogRepository;
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

    public TravellogResponseDTO update(TravellogReceiveDTO travellogReceiveDTO, Long travellogId){
        Travellog travellogFound = travellogRepository.findById(travellogId).orElseThrow(NotFoundException::new);
        Travellog travellogUpdated = travellogReceiveDTO.dtoToEntity();

        travellogFound.setMode(travellogUpdated.getMode());
        travellogFound.setDistanceKm(travellogUpdated.getDistanceKm());
        travellogFound.setEstimatedCo2Kg(travellogUpdated.getEstimatedCo2Kg());
        travellogFound.setObservation(travellogUpdated.getObservation());

        return travellogRepository.save(travellogFound).entityToDTO();
    }
}
