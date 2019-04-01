package com.charactergenerator.reyerta.web.rest;
import com.charactergenerator.reyerta.domain.Aic;
import com.charactergenerator.reyerta.service.AicService;
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
 * REST controller for managing Aic.
 */
@RestController
@RequestMapping("/api")
public class AicResource {

    private final Logger log = LoggerFactory.getLogger(AicResource.class);

    private static final String ENTITY_NAME = "aic";

    private final AicService aicService;

    public AicResource(AicService aicService) {
        this.aicService = aicService;
    }

    /**
     * POST  /aics : Create a new aic.
     *
     * @param aic the aic to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aic, or with status 400 (Bad Request) if the aic has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/aics")
    public ResponseEntity<Aic> createAic(@RequestBody Aic aic) throws URISyntaxException {
        log.debug("REST request to save Aic : {}", aic);
        if (aic.getId() != null) {
            throw new BadRequestAlertException("A new aic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Aic result = aicService.save(aic);
        return ResponseEntity.created(new URI("/api/aics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /aics : Updates an existing aic.
     *
     * @param aic the aic to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aic,
     * or with status 400 (Bad Request) if the aic is not valid,
     * or with status 500 (Internal Server Error) if the aic couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/aics")
    public ResponseEntity<Aic> updateAic(@RequestBody Aic aic) throws URISyntaxException {
        log.debug("REST request to update Aic : {}", aic);
        if (aic.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Aic result = aicService.save(aic);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, aic.getId().toString()))
            .body(result);
    }

    /**
     * GET  /aics : get all the aics.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of aics in body
     */
    @GetMapping("/aics")
    public List<Aic> getAllAics() {
        log.debug("REST request to get all Aics");
        return aicService.findAll();
    }

    /**
     * GET  /aics/:id : get the "id" aic.
     *
     * @param id the id of the aic to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aic, or with status 404 (Not Found)
     */
    @GetMapping("/aics/{id}")
    public ResponseEntity<Aic> getAic(@PathVariable Long id) {
        log.debug("REST request to get Aic : {}", id);
        Optional<Aic> aic = aicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(aic);
    }

    /**
     * DELETE  /aics/:id : delete the "id" aic.
     *
     * @param id the id of the aic to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/aics/{id}")
    public ResponseEntity<Void> deleteAic(@PathVariable Long id) {
        log.debug("REST request to delete Aic : {}", id);
        aicService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
