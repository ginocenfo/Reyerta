package com.charactergenerator.reyerta.web.rest;

import com.charactergenerator.reyerta.ReyertaApp;

import com.charactergenerator.reyerta.domain.Aic;
import com.charactergenerator.reyerta.repository.AicRepository;
import com.charactergenerator.reyerta.service.AicService;
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

/**
 * Test class for the AicResource REST controller.
 *
 * @see AicResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReyertaApp.class)
public class AicResourceIntTest {

    private static final Integer DEFAULT_PROB = 1;
    private static final Integer UPDATED_PROB = 2;

    private static final Integer DEFAULT_MULT = 1;
    private static final Integer UPDATED_MULT = 2;

    @Autowired
    private AicRepository aicRepository;

    @Autowired
    private AicService aicService;

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

    private MockMvc restAicMockMvc;

    private Aic aic;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AicResource aicResource = new AicResource(aicService);
        this.restAicMockMvc = MockMvcBuilders.standaloneSetup(aicResource)
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
    public static Aic createEntity(EntityManager em) {
        Aic aic = new Aic()
            .prob(DEFAULT_PROB)
            .mult(DEFAULT_MULT);
        return aic;
    }

    @Before
    public void initTest() {
        aic = createEntity(em);
    }

    @Test
    @Transactional
    public void createAic() throws Exception {
        int databaseSizeBeforeCreate = aicRepository.findAll().size();

        // Create the Aic
        restAicMockMvc.perform(post("/api/aics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aic)))
            .andExpect(status().isCreated());

        // Validate the Aic in the database
        List<Aic> aicList = aicRepository.findAll();
        assertThat(aicList).hasSize(databaseSizeBeforeCreate + 1);
        Aic testAic = aicList.get(aicList.size() - 1);
        assertThat(testAic.getProb()).isEqualTo(DEFAULT_PROB);
        assertThat(testAic.getMult()).isEqualTo(DEFAULT_MULT);
    }

    @Test
    @Transactional
    public void createAicWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aicRepository.findAll().size();

        // Create the Aic with an existing ID
        aic.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAicMockMvc.perform(post("/api/aics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aic)))
            .andExpect(status().isBadRequest());

        // Validate the Aic in the database
        List<Aic> aicList = aicRepository.findAll();
        assertThat(aicList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAics() throws Exception {
        // Initialize the database
        aicRepository.saveAndFlush(aic);

        // Get all the aicList
        restAicMockMvc.perform(get("/api/aics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aic.getId().intValue())))
            .andExpect(jsonPath("$.[*].prob").value(hasItem(DEFAULT_PROB)))
            .andExpect(jsonPath("$.[*].mult").value(hasItem(DEFAULT_MULT)));
    }
    
    @Test
    @Transactional
    public void getAic() throws Exception {
        // Initialize the database
        aicRepository.saveAndFlush(aic);

        // Get the aic
        restAicMockMvc.perform(get("/api/aics/{id}", aic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(aic.getId().intValue()))
            .andExpect(jsonPath("$.prob").value(DEFAULT_PROB))
            .andExpect(jsonPath("$.mult").value(DEFAULT_MULT));
    }

    @Test
    @Transactional
    public void getNonExistingAic() throws Exception {
        // Get the aic
        restAicMockMvc.perform(get("/api/aics/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAic() throws Exception {
        // Initialize the database
        aicService.save(aic);

        int databaseSizeBeforeUpdate = aicRepository.findAll().size();

        // Update the aic
        Aic updatedAic = aicRepository.findById(aic.getId()).get();
        // Disconnect from session so that the updates on updatedAic are not directly saved in db
        em.detach(updatedAic);
        updatedAic
            .prob(UPDATED_PROB)
            .mult(UPDATED_MULT);

        restAicMockMvc.perform(put("/api/aics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAic)))
            .andExpect(status().isOk());

        // Validate the Aic in the database
        List<Aic> aicList = aicRepository.findAll();
        assertThat(aicList).hasSize(databaseSizeBeforeUpdate);
        Aic testAic = aicList.get(aicList.size() - 1);
        assertThat(testAic.getProb()).isEqualTo(UPDATED_PROB);
        assertThat(testAic.getMult()).isEqualTo(UPDATED_MULT);
    }

    @Test
    @Transactional
    public void updateNonExistingAic() throws Exception {
        int databaseSizeBeforeUpdate = aicRepository.findAll().size();

        // Create the Aic

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAicMockMvc.perform(put("/api/aics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aic)))
            .andExpect(status().isBadRequest());

        // Validate the Aic in the database
        List<Aic> aicList = aicRepository.findAll();
        assertThat(aicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAic() throws Exception {
        // Initialize the database
        aicService.save(aic);

        int databaseSizeBeforeDelete = aicRepository.findAll().size();

        // Delete the aic
        restAicMockMvc.perform(delete("/api/aics/{id}", aic.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Aic> aicList = aicRepository.findAll();
        assertThat(aicList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Aic.class);
        Aic aic1 = new Aic();
        aic1.setId(1L);
        Aic aic2 = new Aic();
        aic2.setId(aic1.getId());
        assertThat(aic1).isEqualTo(aic2);
        aic2.setId(2L);
        assertThat(aic1).isNotEqualTo(aic2);
        aic1.setId(null);
        assertThat(aic1).isNotEqualTo(aic2);
    }
}
