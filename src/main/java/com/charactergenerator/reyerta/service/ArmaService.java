package com.charactergenerator.reyerta.service;

import com.charactergenerator.reyerta.domain.Arma;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Arma.
 */
public interface ArmaService {

    /**
     * Save a arma.
     *
     * @param arma the entity to save
     * @return the persisted entity
     */
    Arma save(Arma arma);

    /**
     * Get all the armas.
     *
     * @return the list of entities
     */
    List<Arma> findAll();


    /**
     * Get the "id" arma.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Arma> findOne(Long id);

    /**
     * Delete the "id" arma.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
