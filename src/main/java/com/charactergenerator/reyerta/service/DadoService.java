package com.charactergenerator.reyerta.service;

import com.charactergenerator.reyerta.domain.Dado;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Dado.
 */
public interface DadoService {

    /**
     * Save a dado.
     *
     * @param dado the entity to save
     * @return the persisted entity
     */
    Dado save(Dado dado);

    /**
     * Get all the dados.
     *
     * @return the list of entities
     */
    List<Dado> findAll();


    /**
     * Get the "id" dado.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Dado> findOne(Long id);

    /**
     * Delete the "id" dado.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
