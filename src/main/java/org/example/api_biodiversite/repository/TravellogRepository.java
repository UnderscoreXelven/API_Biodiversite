package org.example.api_biodiversite.repository;

import org.example.api_biodiversite.entity.Travellog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellogRepository extends JpaRepository<Travellog, Long> {
    public List<Travellog> findAllByObservation_ObservationId(Long observationId);
}
