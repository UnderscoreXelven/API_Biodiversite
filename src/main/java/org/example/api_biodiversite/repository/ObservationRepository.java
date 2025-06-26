package org.example.api_biodiversite.repository;

import org.example.api_biodiversite.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {
    //Filtre par specieId
    List<Observation> findBySpecie_SpecieId(Long specieId);

    //Filtre par observerName
    List<Observation> findByObserverNameContainingIgnoreCase(String observerName);

    //Filtre par date entre startDate et endDate
    List<Observation> findByObservationDateBetween(LocalDate startDate, LocalDate endDate);

    //Filtre par specieId ET observerName
    List<Observation> findBySpecie_SpecieIdAndObserverNameContainingIgnoreCase(Long specieId, String observerName);

    //Filtre par specieId ET plage de dates
    List<Observation> findBySpecie_SpecieIdAndObservationDateBetween(Long specieId, LocalDate startDate, LocalDate endDate);

    //Filtre par observerName ET plage de dates
    List<Observation> findByObserverNameContainingIgnoreCaseAndObservationDateBetween(String observerName, LocalDate startDate, LocalDate endDate);

    //Filtre par specieId ET observerName ET plage de dates
    List<Observation> findBySpecie_SpecieIdAndObserverNameContainingIgnoreCaseAndObservationDateBetween(Long specieId, String observerName, LocalDate startDate, LocalDate endDate);
}
