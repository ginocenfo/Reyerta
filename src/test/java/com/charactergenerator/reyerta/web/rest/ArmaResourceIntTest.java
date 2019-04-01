package com.charactergenerator.reyerta.web.rest;

import com.charactergenerator.reyerta.ReyertaApp;

import com.charactergenerator.reyerta.domain.Arma;
import com.charactergenerator.reyerta.repository.ArmaRepository;
import com.charactergenerator.reyerta.service.ArmaService;
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

import com.charactergenerator.reyerta.domain.enumeration.DamageType;
import com.charactergenerator.reyerta.domain.enumeration.Special;
import com.charactergenerator.reyerta.domain.enumeration.IncrustacionesArma;
/**
 * Test class for the ArmaResource REST controller.
 *
 * @see ArmaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReyertaApp.class)
public class ArmaResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_PRIZE = 1D;
    private static final Double UPDATED_PRIZE = 2D;

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Boolean DEFAULT_IS_MASTERPIECE = false;
    private static final Boolean UPDATED_IS_MASTERPIECE = true;

    private static final Integer DEFAULT_ELITE_LEVEL = 1;
    private static final Integer UPDATED_ELITE_LEVEL = 2;

    private static final DamageType DEFAULT_DAMAGE_TYPE = DamageType.CORTANTE;
    private static final DamageType UPDATED_DAMAGE_TYPE = DamageType.PERFORANTE;

    private static final Special DEFAULT_SPECIAL = Special.NOLETAL;
    private static final Special UPDATED_SPECIAL = Special.LETAL;

    private static final IncrustacionesArma DEFAULT_INCRUSTACION = IncrustacionesArma.AMENAZANTE;
    private static final IncrustacionesArma UPDATED_INCRUSTACION = IncrustacionesArma.ANULANTE_MENOR;

    @Autowired
    private ArmaRepository armaRepository;

    @Autowired
    private ArmaService armaService;

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

    private MockMvc restArmaMockMvc;

    private Arma arma;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ArmaResource armaResource = new ArmaResource(armaService);
        this.restArmaMockMvc = MockMvcBuilders.standaloneSetup(armaResource)
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
    public static Arma createEntity(EntityManager em) {
        Arma arma = new Arma()
            .type(DEFAULT_TYPE)
            .name(DEFAULT_NAME)
            .prize(DEFAULT_PRIZE)
            .level(DEFAULT_LEVEL)
            .isMasterpiece(DEFAULT_IS_MASTERPIECE)
            .eliteLevel(DEFAULT_ELITE_LEVEL)
            .damageType(DEFAULT_DAMAGE_TYPE)
            .special(DEFAULT_SPECIAL)
            .incrustacion(DEFAULT_INCRUSTACION);
        return arma;
    }

    @Before
    public void initTest() {
        arma = createEntity(em);
    }

    @Test
    @Transactional
    public void createArma() throws Exception {
        int databaseSizeBeforeCreate = armaRepository.findAll().size();

        // Create the Arma
        restArmaMockMvc.perform(post("/api/armas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(arma)))
            .andExpect(status().isCreated());

        // Validate the Arma in the database
        List<Arma> armaList = armaRepository.findAll();
        assertThat(armaList).hasSize(databaseSizeBeforeCreate + 1);
        Arma testArma = armaList.get(armaList.size() - 1);
        assertThat(testArma.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testArma.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testArma.getPrize()).isEqualTo(DEFAULT_PRIZE);
        assertThat(testArma.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testArma.isIsMasterpiece()).isEqualTo(DEFAULT_IS_MASTERPIECE);
        assertThat(testArma.getEliteLevel()).isEqualTo(DEFAULT_ELITE_LEVEL);
        assertThat(testArma.getDamageType()).isEqualTo(DEFAULT_DAMAGE_TYPE);
        assertThat(testArma.getSpecial()).isEqualTo(DEFAULT_SPECIAL);
        assertThat(testArma.getIncrustacion()).isEqualTo(DEFAULT_INCRUSTACION);
    }

    @Test
    @Transactional
    public void createArmaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = armaRepository.findAll().size();

        // Create the Arma with an existing ID
        arma.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArmaMockMvc.perform(post("/api/armas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(arma)))
            .andExpect(status().isBadRequest());

        // Validate the Arma in the database
        List<Arma> armaList = armaRepository.findAll();
        assertThat(armaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllArmas() throws Exception {
        // Initialize the database
        armaRepository.saveAndFlush(arma);

        // Get all the armaList
        restArmaMockMvc.perform(get("/api/armas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(arma.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].prize").value(hasItem(DEFAULT_PRIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].isMasterpiece").value(hasItem(DEFAULT_IS_MASTERPIECE.booleanValue())))
            .andExpect(jsonPath("$.[*].eliteLevel").value(hasItem(DEFAULT_ELITE_LEVEL)))
            .andExpect(jsonPath("$.[*].damageType").value(hasItem(DEFAULT_DAMAGE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].special").value(hasItem(DEFAULT_SPECIAL.toString())))
            .andExpect(jsonPath("$.[*].incrustacion").value(hasItem(DEFAULT_INCRUSTACION.toString())));
    }
    
    @Test
    @Transactional
    public void getArma() throws Exception {
        // Initialize the database
        armaRepository.saveAndFlush(arma);

        // Get the arma
        restArmaMockMvc.perform(get("/api/armas/{id}", arma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(arma.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.prize").value(DEFAULT_PRIZE.doubleValue()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.isMasterpiece").value(DEFAULT_IS_MASTERPIECE.booleanValue()))
            .andExpect(jsonPath("$.eliteLevel").value(DEFAULT_ELITE_LEVEL))
            .andExpect(jsonPath("$.damageType").value(DEFAULT_DAMAGE_TYPE.toString()))
            .andExpect(jsonPath("$.special").value(DEFAULT_SPECIAL.toString()))
            .andExpect(jsonPath("$.incrustacion").value(DEFAULT_INCRUSTACION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingArma() throws Exception {
        // Get the arma
        restArmaMockMvc.perform(get("/api/armas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArma() throws Exception {
        // Initialize the database
        armaService.save(arma);

        int databaseSizeBeforeUpdate = armaRepository.findAll().size();

        // Update the arma
        Arma updatedArma = armaRepository.findById(arma.getId()).get();
        // Disconnect from session so that the updates on updatedArma are not directly saved in db
        em.detach(updatedArma);
        updatedArma
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .prize(UPDATED_PRIZE)
            .level(UPDATED_LEVEL)
            .isMasterpiece(UPDATED_IS_MASTERPIECE)
            .eliteLevel(UPDATED_ELITE_LEVEL)
            .damageType(UPDATED_DAMAGE_TYPE)
            .special(UPDATED_SPECIAL)
            .incrustacion(UPDATED_INCRUSTACION);

        restArmaMockMvc.perform(put("/api/armas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedArma)))
            .andExpect(status().isOk());

        // Validate the Arma in the database
        List<Arma> armaList = armaRepository.findAll();
        assertThat(armaList).hasSize(databaseSizeBeforeUpdate);
        Arma testArma = armaList.get(armaList.size() - 1);
        assertThat(testArma.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testArma.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testArma.getPrize()).isEqualTo(UPDATED_PRIZE);
        assertThat(testArma.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testArma.isIsMasterpiece()).isEqualTo(UPDATED_IS_MASTERPIECE);
        assertThat(testArma.getEliteLevel()).isEqualTo(UPDATED_ELITE_LEVEL);
        assertThat(testArma.getDamageType()).isEqualTo(UPDATED_DAMAGE_TYPE);
        assertThat(testArma.getSpecial()).isEqualTo(UPDATED_SPECIAL);
        assertThat(testArma.getIncrustacion()).isEqualTo(UPDATED_INCRUSTACION);
    }

    @Test
    @Transactional
    public void updateNonExistingArma() throws Exception {
        int databaseSizeBeforeUpdate = armaRepository.findAll().size();

        // Create the Arma

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArmaMockMvc.perform(put("/api/armas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(arma)))
            .andExpect(status().isBadRequest());

        // Validate the Arma in the database
        List<Arma> armaList = armaRepository.findAll();
        assertThat(armaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArma() throws Exception {
        // Initialize the database
        armaService.save(arma);

        int databaseSizeBeforeDelete = armaRepository.findAll().size();

        // Delete the arma
        restArmaMockMvc.perform(delete("/api/armas/{id}", arma.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Arma> armaList = armaRepository.findAll();
        assertThat(armaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Arma.class);
        Arma arma1 = new Arma();
        arma1.setId(1L);
        Arma arma2 = new Arma();
        arma2.setId(arma1.getId());
        assertThat(arma1).isEqualTo(arma2);
        arma2.setId(2L);
        assertThat(arma1).isNotEqualTo(arma2);
        arma1.setId(null);
        assertThat(arma1).isNotEqualTo(arma2);
    }
}
