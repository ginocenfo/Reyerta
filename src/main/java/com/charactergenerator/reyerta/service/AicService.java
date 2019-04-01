package com.charactergenerator.reyerta.service;

import com.charactergenerator.reyerta.domain.Aic;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Aic.
 */
public interface AicService {

    /**
     * Save a aic.
     *
     * @param aic the entity to save
     * @return the persisted entity
     */
    Aic save(Aic aic);

    /**
     * Get all the aics.
     *
     * @return the list of entities
     */
    List<Aic> findAll();


    /**
     * Get the "id" aic.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Aic> findOne(Long id);

    /**
     * Delete the "id" aic.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
