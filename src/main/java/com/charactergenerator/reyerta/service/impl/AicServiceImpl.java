package com.charactergenerator.reyerta.service.impl;

import com.charactergenerator.reyerta.service.AicService;
import com.charactergenerator.reyerta.domain.Aic;
import com.charactergenerator.reyerta.repository.AicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Aic.
 */
@Service
@Transactional
public class AicServiceImpl implements AicService {

    private final Logger log = LoggerFactory.getLogger(AicServiceImpl.class);

    private final AicRepository aicRepository;

    public AicServiceImpl(AicRepository aicRepository) {
        this.aicRepository = aicRepository;
    }

    /**
     * Save a aic.
     *
     * @param aic the entity to save
     * @return the persisted entity
     */
    @Override
    public Aic save(Aic aic) {
        log.debug("Request to save Aic : {}", aic);
        return aicRepository.save(aic);
    }

    /**
     * Get all the aics.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Aic> findAll() {
        log.debug("Request to get all Aics");
        return aicRepository.findAll();
    }


    /**
     * Get one aic by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Aic> findOne(Long id) {
        log.debug("Request to get Aic : {}", id);
        return aicRepository.findById(id);
    }

    /**
     * Delete the aic by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Aic : {}", id);        aicRepository.deleteById(id);
    }
}
