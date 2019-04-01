package com.charactergenerator.reyerta.web.rest;

import com.charactergenerator.reyerta.ReyertaApp;

import com.charactergenerator.reyerta.domain.ProfilePoints;
import com.charactergenerator.reyerta.repository.ProfilePointsRepository;
import com.charactergenerator.reyerta.service.ProfilePointsService;
import com.charactergenerator.reyerta.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.charactergenerator.reyerta.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.charactergenerator.reyerta.domain.enumeration.Profile;
/**
 * Test class for the ProfilePointsResource REST controller.
 *
 * @see ProfilePointsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReyertaApp.class)
public class ProfilePointsResourceIntTest {

    private static final Integer DEFAULT_POINTS = 1;
    private static final Integer UPDATED_POINTS = 2;

    private static final Profile DEFAULT_PERFIL = Profile.CARISMA;
    private static final Profile UPDATED_PERFIL = Profile.CONSTITUCION;

    @Autowired
    private ProfilePointsRepository profilePointsRepository;

    @Autowired
    private ProfilePointsService profilePointsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restProfilePointsMockMvc;

    private ProfilePoints profilePoints;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfilePointsResource profilePointsResource = new ProfilePointsResource(profilePointsService);
        this.restProfilePointsMockMvc = MockMvcBuilders.standaloneSetup(profilePointsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfilePoints createEntity(EntityManager em) {
        ProfilePoints profilePoints = new ProfilePoints()
            .points(DEFAULT_POINTS)
            .perfil(DEFAULT_PERFIL);
        return profilePoints;
    }

    @Before
    public void initTest() {
        profilePoints = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfilePoints() throws Exception {
        int databaseSizeBeforeCreate = profilePointsRepository.findAll().size();

        // Create the ProfilePoints
        restProfilePointsMockMvc.perform(post("/api/profile-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilePoints)))
            .andExpect(status().isCreated());

        // Validate the ProfilePoints in the database
        List<ProfilePoints> profilePointsList = profilePointsRepository.findAll();
        assertThat(profilePointsList).hasSize(databaseSizeBeforeCreate + 1);
        ProfilePoints testProfilePoints = profilePointsList.get(profilePointsList.size() - 1);
        assertThat(testProfilePoints.getPoints()).isEqualTo(DEFAULT_POINTS);
        assertThat(testProfilePoints.getPerfil()).isEqualTo(DEFAULT_PERFIL);
    }

    @Test
    @Transactional
    public void createProfilePointsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profilePointsRepository.findAll().size();

        // Create the ProfilePoints with an existing ID
        profilePoints.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfilePointsMockMvc.perform(post("/api/profile-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilePoints)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilePoints in the database
        List<ProfilePoints> profilePointsList = profilePointsRepository.findAll();
        assertThat(profilePointsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProfilePoints() throws Exception {
        // Initialize the database
        profilePointsRepository.saveAndFlush(profilePoints);

        // Get all the profilePointsList
        restProfilePointsMockMvc.perform(get("/api/profile-points?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profilePoints.getId().intValue())))
            .andExpect(jsonPath("$.[*].points").value(hasItem(DEFAULT_POINTS)))
            .andExpect(jsonPath("$.[*].perfil").value(hasItem(DEFAULT_PERFIL.toString())));
    }
    
    @Test
    @Transactional
    public void getProfilePoints() throws Exception {
        // Initialize the database
        profilePointsRepository.saveAndFlush(profilePoints);

        // Get the profilePoints
        restProfilePointsMockMvc.perform(get("/api/profile-points/{id}", profilePoints.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(profilePoints.getId().intValue()))
            .andExpect(jsonPath("$.points").value(DEFAULT_POINTS))
            .andExpect(jsonPath("$.perfil").value(DEFAULT_PERFIL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProfilePoints() throws Exception {
        // Get the profilePoints
        restProfilePointsMockMvc.perform(get("/api/profile-points/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfilePoints() throws Exception {
        // Initialize the database
        profilePointsService.save(profilePoints);

        int databaseSizeBeforeUpdate = profilePointsRepository.findAll().size();

        // Update the profilePoints
        ProfilePoints updatedProfilePoints = profilePointsRepository.findById(profilePoints.getId()).get();
        // Disconnect from session so that the updates on updatedProfilePoints are not directly saved in db
        em.detach(updatedProfilePoints);
        updatedProfilePoints
            .points(UPDATED_POINTS)
            .perfil(UPDATED_PERFIL);

        restProfilePointsMockMvc.perform(put("/api/profile-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProfilePoints)))
            .andExpect(status().isOk());

        // Validate the ProfilePoints in the database
        List<ProfilePoints> profilePointsList = profilePointsRepository.findAll();
        assertThat(profilePointsList).hasSize(databaseSizeBeforeUpdate);
        ProfilePoints testProfilePoints = profilePointsList.get(profilePointsList.size() - 1);
        assertThat(testProfilePoints.getPoints()).isEqualTo(UPDATED_POINTS);
        assertThat(testProfilePoints.getPerfil()).isEqualTo(UPDATED_PERFIL);
    }

    @Test
    @Transactional
    public void updateNonExistingProfilePoints() throws Exception {
        int databaseSizeBeforeUpdate = profilePointsRepository.findAll().size();

        // Create the ProfilePoints

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfilePointsMockMvc.perform(put("/api/profile-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilePoints)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilePoints in the database
        List<ProfilePoints> profilePointsList = profilePointsRepository.findAll();
        assertThat(profilePointsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfilePoints() throws Exception {
        // Initialize the database
        profilePointsService.save(profilePoints);

        int databaseSizeBeforeDelete = profilePointsRepository.findAll().size();

        // Delete the profilePoints
        restProfilePointsMockMvc.perform(delete("/api/profile-points/{id}", profilePoints.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProfilePoints> profilePointsList = profilePointsRepository.findAll();
        assertThat(profilePointsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfilePoints.class);
        ProfilePoints profilePoints1 = new ProfilePoints();
        profilePoints1.setId(1L);
        ProfilePoints profilePoints2 = new ProfilePoints();
        profilePoints2.setId(profilePoints1.getId());
        assertThat(profilePoints1).isEqualTo(profilePoints2);
        profilePoints2.setId(2L);
        assertThat(profilePoints1).isNotEqualTo(profilePoints2);
        profilePoints1.setId(null);
        assertThat(profilePoints1).isNotEqualTo(profilePoints2);
    }
}
