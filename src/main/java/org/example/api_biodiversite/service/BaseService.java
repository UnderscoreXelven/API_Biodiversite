package org.example.api_biodiversite.service;

import org.example.api_biodiversite.dto.observation.ObservationResponseDTO;
import org.example.api_biodiversite.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseService<T, ID, R, D> {
    protected abstract JpaRepository<T, ID> getRepository();

    //Return a specific ResponseDTO for a given entity
    protected abstract R toResponseDTO(T entity);

    //Return a specific Entity for a given ResponseDTO
    protected abstract T toEntity(D dto);

    public R save(D dto){
        T entity = toEntity(dto);
        T saved = getRepository().save(entity);
        return toResponseDTO(saved);
    }

    public void delete(ID id){
        getRepository().deleteById(id);
    }

    public R getById(ID id){
        T entity = getRepository().findById(id).orElseThrow(NotFoundException::new);
        return toResponseDTO(entity);
    }

    public List<R> getAll(){
        return getRepository().findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }
}

