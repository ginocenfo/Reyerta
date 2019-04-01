package com.charactergenerator.reyerta.service.impl;

import com.charactergenerator.reyerta.service.ArmaduraService;
import com.charactergenerator.reyerta.domain.Armadura;
import com.charactergenerator.reyerta.repository.ArmaduraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Armadura.
 */
@Service
@Transactional
public class ArmaduraServiceImpl implements ArmaduraService {

    private final Logger log = LoggerFactory.getLogger(ArmaduraServiceImpl.class);

    private final ArmaduraRepository armaduraRepository;

    public ArmaduraServiceImpl(ArmaduraRepository armaduraRepository) {
        this.armaduraRepository = armaduraRepository;
    }

    /**
     * Save a armadura.
     *
     * @param armadura the entity to save
     * @return the persisted entity
     */
    @Override
    public Armadura save(Armadura armadura) {
        log.debug("Request to save Armadura : {}", armadura);
        return armaduraRepository.save(armadura);
    }

    /**
     * Get all the armaduras.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Armadura> findAll() {
        log.debug("Request to get all Armaduras");
        return armaduraRepository.findAll();
    }


    /**
     * Get one armadura by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Armadura> findOne(Long id) {
        log.debug("Request to get Armadura : {}", id);
        return armaduraRepository.findById(id);
    }

    /**
     * Delete the armadura by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Armadura : {}", id);        armaduraRepository.deleteById(id);
    }
}
