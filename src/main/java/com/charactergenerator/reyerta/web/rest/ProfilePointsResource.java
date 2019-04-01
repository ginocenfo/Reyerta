package com.charactergenerator.reyerta.web.rest;
import com.charactergenerator.reyerta.domain.ProfilePoints;
import com.charactergenerator.reyerta.service.ProfilePointsService;
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
 * REST controller for managing ProfilePoints.
 */
@RestController
@RequestMapping("/api")
public class ProfilePointsResource {

    private final Logger log = LoggerFactory.getLogger(ProfilePointsResource.class);

    private static final String ENTITY_NAME = "profilePoints";

    private final ProfilePointsService profilePointsService;

    public ProfilePointsResource(ProfilePointsService profilePointsService) {
        this.profilePointsService = profilePointsService;
    }

    /**
     * POST  /profile-points : Create a new profilePoints.
     *
     * @param profilePoints the profilePoints to create
     * @return the ResponseEntity with status 201 (Created) and with body the new profilePoints, or with status 400 (Bad Request) if the profilePoints has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/profile-points")
    public ResponseEntity<ProfilePoints> createProfilePoints(@RequestBody ProfilePoints profilePoints) throws URISyntaxException {
        log.debug("REST request to save ProfilePoints : {}", profilePoints);
        if (profilePoints.getId() != null) {
            throw new BadRequestAlertException("A new profilePoints cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfilePoints result = profilePointsService.save(profilePoints);
        return ResponseEntity.created(new URI("/api/profile-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /profile-points : Updates an existing profilePoints.
     *
     * @param profilePoints the profilePoints to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated profilePoints,
     * or with status 400 (Bad Request) if the profilePoints is not valid,
     * or with status 500 (Internal Server Error) if the profilePoints couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/profile-points")
    public ResponseEntity<ProfilePoints> updateProfilePoints(@RequestBody ProfilePoints profilePoints) throws URISyntaxException {
        log.debug("REST request to update ProfilePoints : {}", profilePoints);
        if (profilePoints.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfilePoints result = profilePointsService.save(profilePoints);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, profilePoints.getId().toString()))
            .body(result);
    }

    /**
     * GET  /profile-points : get all the profilePoints.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of profilePoints in body
     */
    @GetMapping("/profile-points")
    public List<ProfilePoints> getAllProfilePoints() {
        log.debug("REST request to get all ProfilePoints");
        return profilePointsService.findAll();
    }

    /**
     * GET  /profile-points/:id : get the "id" profilePoints.
     *
     * @param id the id of the profilePoints to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the profilePoints, or with status 404 (Not Found)
     */
    @GetMapping("/profile-points/{id}")
    public ResponseEntity<ProfilePoints> getProfilePoints(@PathVariable Long id) {
        log.debug("REST request to get ProfilePoints : {}", id);
        Optional<ProfilePoints> profilePoints = profilePointsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(profilePoints);
    }

    /**
     * DELETE  /profile-points/:id : delete the "id" profilePoints.
     *
     * @param id the id of the profilePoints to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/profile-points/{id}")
    public ResponseEntity<Void> deleteProfilePoints(@PathVariable Long id) {
        log.debug("REST request to delete ProfilePoints : {}", id);
        profilePointsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
