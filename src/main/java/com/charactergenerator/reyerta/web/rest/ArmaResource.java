package com.charactergenerator.reyerta.web.rest;
import com.charactergenerator.reyerta.domain.Arma;
import com.charactergenerator.reyerta.service.ArmaService;
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
 * REST controller for managing Arma.
 */
@RestController
@RequestMapping("/api")
public class ArmaResource {

    private final Logger log = LoggerFactory.getLogger(ArmaResource.class);

    private static final String ENTITY_NAME = "arma";

    private final ArmaService armaService;

    public ArmaResource(ArmaService armaService) {
        this.armaService = armaService;
    }

    /**
     * POST  /armas : Create a new arma.
     *
     * @param arma the arma to create
     * @return the ResponseEntity with status 201 (Created) and with body the new arma, or with status 400 (Bad Request) if the arma has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/armas")
    public ResponseEntity<Arma> createArma(@RequestBody Arma arma) throws URISyntaxException {
        log.debug("REST request to save Arma : {}", arma);
        if (arma.getId() != null) {
            throw new BadRequestAlertException("A new arma cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Arma result = armaService.save(arma);
        return ResponseEntity.created(new URI("/api/armas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /armas : Updates an existing arma.
     *
     * @param arma the arma to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated arma,
     * or with status 400 (Bad Request) if the arma is not valid,
     * or with status 500 (Internal Server Error) if the arma couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/armas")
    public ResponseEntity<Arma> updateArma(@RequestBody Arma arma) throws URISyntaxException {
        log.debug("REST request to update Arma : {}", arma);
        if (arma.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Arma result = armaService.save(arma);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, arma.getId().toString()))
            .body(result);
    }

    /**
     * GET  /armas : get all the armas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of armas in body
     */
    @GetMapping("/armas")
    public List<Arma> getAllArmas() {
        log.debug("REST request to get all Armas");
        return armaService.findAll();
    }

    /**
     * GET  /armas/:id : get the "id" arma.
     *
     * @param id the id of the arma to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the arma, or with status 404 (Not Found)
     */
    @GetMapping("/armas/{id}")
    public ResponseEntity<Arma> getArma(@PathVariable Long id) {
        log.debug("REST request to get Arma : {}", id);
        Optional<Arma> arma = armaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(arma);
    }

    /**
     * DELETE  /armas/:id : delete the "id" arma.
     *
     * @param id the id of the arma to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/armas/{id}")
    public ResponseEntity<Void> deleteArma(@PathVariable Long id) {
        log.debug("REST request to delete Arma : {}", id);
        armaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
