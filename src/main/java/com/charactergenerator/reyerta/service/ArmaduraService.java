package com.charactergenerator.reyerta.service;

import com.charactergenerator.reyerta.domain.Armadura;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Armadura.
 */
public interface ArmaduraService {

    /**
     * Save a armadura.
     *
     * @param armadura the entity to save
     * @return the persisted entity
     */
    Armadura save(Armadura armadura);

    /**
     * Get all the armaduras.
     *
     * @return the list of entities
     */
    List<Armadura> findAll();


    /**
     * Get the "id" armadura.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Armadura> findOne(Long id);

    /**
     * Delete the "id" armadura.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
