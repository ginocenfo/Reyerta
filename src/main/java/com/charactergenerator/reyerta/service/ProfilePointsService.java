package com.charactergenerator.reyerta.service;

import com.charactergenerator.reyerta.domain.ProfilePoints;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ProfilePoints.
 */
public interface ProfilePointsService {

    /**
     * Save a profilePoints.
     *
     * @param profilePoints the entity to save
     * @return the persisted entity
     */
    ProfilePoints save(ProfilePoints profilePoints);

    /**
     * Get all the profilePoints.
     *
     * @return the list of entities
     */
    List<ProfilePoints> findAll();


    /**
     * Get the "id" profilePoints.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProfilePoints> findOne(Long id);

    /**
     * Delete the "id" profilePoints.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
