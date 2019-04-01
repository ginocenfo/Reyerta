package com.charactergenerator.reyerta.web.rest;
import com.charactergenerator.reyerta.domain.Dado;
import com.charactergenerator.reyerta.service.DadoService;
import com.charactergenerator.reyerta.web.rest.errors.BadRequestAlertException;
import com.charactergenerator.reyerta.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Dado.
 */
@RestController
@RequestMapping("/api")
public class DadoResource {

    private final Logger log = LoggerFactory.getLogger(DadoResource.class);

    private static final String ENTITY_NAME = "dado";

    private final DadoService dadoService;

    public DadoResource(DadoService dadoService) {
        this.dadoService = dadoService;
    }

    /**
     * POST  /dados : Create a new dado.
     *
     * @param dado the dado to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dado, or with status 400 (Bad Request) if the dado has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dados")
    public ResponseEntity<Dado> createDado(@RequestBody Dado dado) throws URISyntaxException {
        log.debug("REST request to save Dado : {}", dado);
        if (dado.getId() != null) {
            throw new BadRequestAlertException("A new dado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Dado result = dadoService.save(dado);
        return ResponseEntity.created(new URI("/api/dados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dados : Updates an existing dado.
     *
     * @param dado the dado to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dado,
     * or with status 400 (Bad Request) if the dado is not valid,
     * or with status 500 (Internal Server Error) if the dado couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dados")
    public ResponseEntity<Dado> updateDado(@RequestBody Dado dado) throws URISyntaxException {
        log.debug("REST request to update Dado : {}", dado);
        if (dado.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Dado result = dadoService.save(dado);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dado.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dados : get all the dados.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of dados in body
     */
    @GetMapping("/dados")
    public List<Dado> getAllDados() {
        log.debug("REST request to get all Dados");
        return dadoService.findAll();
    }

    /**
     * GET  /dados/:id : get the "id" dado.
     *
     * @param id the id of the dado to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dado, or with status 404 (Not Found)
     */
    @GetMapping("/dados/{id}")
    public ResponseEntity<Dado> getDado(@PathVariable Long id) {
        log.debug("REST request to get Dado : {}", id);
        Optional<Dado> dado = dadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dado);
    }

    /**
     * DELETE  /dados/:id : delete the "id" dado.
     *
     * @param id the id of the dado to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dados/{id}")
    public ResponseEntity<Void> deleteDado(@PathVariable Long id) {
        log.debug("REST request to delete Dado : {}", id);
        dadoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
