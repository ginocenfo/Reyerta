package com.charactergenerator.reyerta.service.impl;

import com.charactergenerator.reyerta.service.ArmaService;
import com.charactergenerator.reyerta.domain.Arma;
import com.charactergenerator.reyerta.repository.ArmaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Arma.
 */
@Service
@Transactional
public class ArmaServiceImpl implements ArmaService {

    private final Logger log = LoggerFactory.getLogger(ArmaServiceImpl.class);

    private final ArmaRepository armaRepository;

    public ArmaServiceImpl(ArmaRepository armaRepository) {
        this.armaRepository = armaRepository;
    }

    /**
     * Save a arma.
     *
     * @param arma the entity to save
     * @return the persisted entity
     */
    @Override
    public Arma save(Arma arma) {
        log.debug("Request to save Arma : {}", arma);
        return armaRepository.save(arma);
    }

    /**
     * Get all the armas.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Arma> findAll() {
        log.debug("Request to get all Armas");
        return armaRepository.findAll();
    }


    /**
     * Get one arma by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Arma> findOne(Long id) {
        log.debug("Request to get Arma : {}", id);
        return armaRepository.findById(id);
    }

    /**
     * Delete the arma by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Arma : {}", id);        armaRepository.deleteById(id);
    }
}
