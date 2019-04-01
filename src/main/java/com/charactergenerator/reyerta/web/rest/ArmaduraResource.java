package com.charactergenerator.reyerta.web.rest;
import com.charactergenerator.reyerta.domain.Armadura;
import com.charactergenerator.reyerta.service.ArmaduraService;
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
 * REST controller for managing Armadura.
 */
@RestController
@RequestMapping("/api")
public class ArmaduraResource {

    private final Logger log = LoggerFactory.getLogger(ArmaduraResource.class);

    private static final String ENTITY_NAME = "armadura";

    private final ArmaduraService armaduraService;

    public ArmaduraResource(ArmaduraService armaduraService) {
        this.armaduraService = armaduraService;
    }

    /**
     * POST  /armaduras : Create a new armadura.
     *
     * @param armadura the armadura to create
     * @return the ResponseEntity with status 201 (Created) and with body the new armadura, or with status 400 (Bad Request) if the armadura has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/armaduras")
    public ResponseEntity<Armadura> createArmadura(@RequestBody Armadura armadura) throws URISyntaxException {
        log.debug("REST request to save Armadura : {}", armadura);
        if (armadura.getId() != null) {
            throw new BadRequestAlertException("A new armadura cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Armadura result = armaduraService.save(armadura);
        return ResponseEntity.created(new URI("/api/armaduras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /armaduras : Updates an existing armadura.
     *
     * @param armadura the armadura to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated armadura,
     * or with status 400 (Bad Request) if the armadura is not valid,
     * or with status 500 (Internal Server Error) if the armadura couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/armaduras")
    public ResponseEntity<Armadura> updateArmadura(@RequestBody Armadura armadura) throws URISyntaxException {
        log.debug("REST request to update Armadura : {}", armadura);
        if (armadura.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Armadura result = armaduraService.save(armadura);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, armadura.getId().toString()))
            .body(result);
    }

    /**
     * GET  /armaduras : get all the armaduras.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of armaduras in body
     */
    @GetMapping("/armaduras")
    public List<Armadura> getAllArmaduras() {
        log.debug("REST request to get all Armaduras");
        return armaduraService.findAll();
    }

    /**
     * GET  /armaduras/:id : get the "id" armadura.
     *
     * @param id the id of the armadura to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the armadura, or with status 404 (Not Found)
     */
    @GetMapping("/armaduras/{id}")
    public ResponseEntity<Armadura> getArmadura(@PathVariable Long id) {
        log.debug("REST request to get Armadura : {}", id);
        Optional<Armadura> armadura = armaduraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(armadura);
    }

    /**
     * DELETE  /armaduras/:id : delete the "id" armadura.
     *
     * @param id the id of the armadura to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/armaduras/{id}")
    public ResponseEntity<Void> deleteArmadura(@PathVariable Long id) {
        log.debug("REST request to delete Armadura : {}", id);
        armaduraService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
