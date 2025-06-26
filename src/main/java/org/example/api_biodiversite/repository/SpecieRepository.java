package org.example.api_biodiversite.repository;

import org.example.api_biodiversite.entity.Specie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, Long> {
}
