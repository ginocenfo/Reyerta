package com.charactergenerator.reyerta.service.impl;

import com.charactergenerator.reyerta.service.ProfilePointsService;
import com.charactergenerator.reyerta.domain.ProfilePoints;
import com.charactergenerator.reyerta.repository.ProfilePointsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing ProfilePoints.
 */
@Service
@Transactional
public class ProfilePointsServiceImpl implements ProfilePointsService {

    private final Logger log = LoggerFactory.getLogger(ProfilePointsServiceImpl.class);

    private final ProfilePointsRepository profilePointsRepository;

    public ProfilePointsServiceImpl(ProfilePointsRepository profilePointsRepository) {
        this.profilePointsRepository = profilePointsRepository;
    }

    /**
     * Save a profilePoints.
     *
     * @param profilePoints the entity to save
     * @return the persisted entity
     */
    @Override
    public ProfilePoints save(ProfilePoints profilePoints) {
        log.debug("Request to save ProfilePoints : {}", profilePoints);
        return profilePointsRepository.save(profilePoints);
    }

    /**
     * Get all the profilePoints.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProfilePoints> findAll() {
        log.debug("Request to get all ProfilePoints");
        return profilePointsRepository.findAll();
    }


    /**
     * Get one profilePoints by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProfilePoints> findOne(Long id) {
        log.debug("Request to get ProfilePoints : {}", id);
        return profilePointsRepository.findById(id);
    }

    /**
     * Delete the profilePoints by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProfilePoints : {}", id);        profilePointsRepository.deleteById(id);
    }
}
