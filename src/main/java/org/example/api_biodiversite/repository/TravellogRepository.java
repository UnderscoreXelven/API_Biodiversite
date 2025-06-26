package org.example.api_biodiversite.repository;

import org.example.api_biodiversite.entity.Travellog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravellogRepository extends JpaRepository<Travellog, Long> {
}
