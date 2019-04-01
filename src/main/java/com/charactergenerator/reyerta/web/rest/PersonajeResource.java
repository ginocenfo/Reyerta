package com.charactergenerator.reyerta.web.rest;
import com.charactergenerator.reyerta.domain.Personaje;
import com.charactergenerator.reyerta.service.PersonajeService;
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
 * REST controller for managing Personaje.
 */
@RestController
@RequestMapping("/api")
public class PersonajeResource {

    private final Logger log = LoggerFactory.getLogger(PersonajeResource.class);

    private static final String ENTITY_NAME = "personaje";

    private final PersonajeService personajeService;

    public PersonajeResource(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    /**
     * POST  /personajes : Create a new personaje.
     *
     * @param personaje the personaje to create
     * @return the ResponseEntity with status 201 (Created) and with body the new personaje, or with status 400 (Bad Request) if the personaje has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/personajes")
    public ResponseEntity<Personaje> createPersonaje(@RequestBody Personaje personaje) throws URISyntaxException {
        log.debug("REST request to save Personaje : {}", personaje);
        if (personaje.getId() != null) {
            throw new BadRequestAlertException("A new personaje cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Personaje result = personajeService.save(personaje);
        return ResponseEntity.created(new URI("/api/personajes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /personajes : Updates an existing personaje.
     *
     * @param personaje the personaje to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated personaje,
     * or with status 400 (Bad Request) if the personaje is not valid,
     * or with status 500 (Internal Server Error) if the personaje couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/personajes")
    public ResponseEntity<Personaje> updatePersonaje(@RequestBody Personaje personaje) throws URISyntaxException {
        log.debug("REST request to update Personaje : {}", personaje);
        if (personaje.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Personaje result = personajeService.save(personaje);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, personaje.getId().toString()))
            .body(result);
    }

    /**
     * GET  /personajes : get all the personajes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of personajes in body
     */
    @GetMapping("/personajes")
    public List<Personaje> getAllPersonajes() {
        log.debug("REST request to get all Personajes");
        return personajeService.findAll();
    }

    /**
     * GET  /personajes/:id : get the "id" personaje.
     *
     * @param id the id of the personaje to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the personaje, or with status 404 (Not Found)
     */
    @GetMapping("/personajes/{id}")
    public ResponseEntity<Personaje> getPersonaje(@PathVariable Long id) {
        log.debug("REST request to get Personaje : {}", id);
        Optional<Personaje> personaje = personajeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personaje);
    }

    /**
     * DELETE  /personajes/:id : delete the "id" personaje.
     *
     * @param id the id of the personaje to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/personajes/{id}")
    public ResponseEntity<Void> deletePersonaje(@PathVariable Long id) {
        log.debug("REST request to delete Personaje : {}", id);
        personajeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
