package com.charactergenerator.reyerta.service.impl;

import com.charactergenerator.reyerta.service.DadoService;
import com.charactergenerator.reyerta.domain.Dado;
import com.charactergenerator.reyerta.repository.DadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Dado.
 */
@Service
@Transactional
public class DadoServiceImpl implements DadoService {

    private final Logger log = LoggerFactory.getLogger(DadoServiceImpl.class);

    private final DadoRepository dadoRepository;

    public DadoServiceImpl(DadoRepository dadoRepository) {
        this.dadoRepository = dadoRepository;
    }

    /**
     * Save a dado.
     *
     * @param dado the entity to save
     * @return the persisted entity
     */
    @Override
    public Dado save(Dado dado) {
        log.debug("Request to save Dado : {}", dado);
        return dadoRepository.save(dado);
    }

    /**
     * Get all the dados.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Dado> findAll() {
        log.debug("Request to get all Dados");
        return dadoRepository.findAll();
    }


    /**
     * Get one dado by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Dado> findOne(Long id) {
        log.debug("Request to get Dado : {}", id);
        return dadoRepository.findById(id);
    }

    /**
     * Delete the dado by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dado : {}", id);        dadoRepository.deleteById(id);
    }
}
