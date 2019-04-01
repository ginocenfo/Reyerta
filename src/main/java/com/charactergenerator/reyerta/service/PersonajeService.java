package com.charactergenerator.reyerta.service;

import com.charactergenerator.reyerta.domain.Personaje;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Personaje.
 */
public interface PersonajeService {

    /**
     * Save a personaje.
     *
     * @param personaje the entity to save
     * @return the persisted entity
     */
    Personaje save(Personaje personaje);

    /**
     * Get all the personajes.
     *
     * @return the list of entities
     */
    List<Personaje> findAll();


    /**
     * Get the "id" personaje.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Personaje> findOne(Long id);

    /**
     * Delete the "id" personaje.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
