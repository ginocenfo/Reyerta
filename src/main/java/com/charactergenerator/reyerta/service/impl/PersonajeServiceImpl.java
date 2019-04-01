package com.charactergenerator.reyerta.service.impl;

import com.charactergenerator.reyerta.service.PersonajeService;
import com.charactergenerator.reyerta.domain.Personaje;
import com.charactergenerator.reyerta.repository.PersonajeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Personaje.
 */
@Service
@Transactional
public class PersonajeServiceImpl implements PersonajeService {

    private final Logger log = LoggerFactory.getLogger(PersonajeServiceImpl.class);

    private final PersonajeRepository personajeRepository;

    public PersonajeServiceImpl(PersonajeRepository personajeRepository) {
        this.personajeRepository = personajeRepository;
    }

    /**
     * Save a personaje.
     *
     * @param personaje the entity to save
     * @return the persisted entity
     */
    @Override
    public Personaje save(Personaje personaje) {
        log.debug("Request to save Personaje : {}", personaje);
        return personajeRepository.save(personaje);
    }

    /**
     * Get all the personajes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Personaje> findAll() {
        log.debug("Request to get all Personajes");
        return personajeRepository.findAll();
    }


    /**
     * Get one personaje by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Personaje> findOne(Long id) {
        log.debug("Request to get Personaje : {}", id);
        return personajeRepository.findById(id);
    }

    /**
     * Delete the personaje by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Personaje : {}", id);        personajeRepository.deleteById(id);
    }
}
