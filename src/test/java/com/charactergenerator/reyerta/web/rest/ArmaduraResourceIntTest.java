package com.charactergenerator.reyerta.web.rest;

import com.charactergenerator.reyerta.ReyertaApp;

import com.charactergenerator.reyerta.domain.Armadura;
import com.charactergenerator.reyerta.repository.ArmaduraRepository;
import com.charactergenerator.reyerta.service.ArmaduraService;
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

import com.charactergenerator.reyerta.domain.enumeration.IncrustacionesArmadura;
/**
 * Test class for the ArmaduraResource REST controller.
 *
 * @see ArmaduraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReyertaApp.class)
public class ArmaduraResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_EVASION_BONUS = 1;
    private static final Integer UPDATED_EVASION_BONUS = 2;

    private static final Integer DEFAULT_MAX_DEX = 1;
    private static final Integer UPDATED_MAX_DEX = 2;

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_UC = 1;
    private static final Integer UPDATED_UC = 2;

    private static final Integer DEFAULT_PPA = 1;
    private static final Integer UPDATED_PPA = 2;

    private static final Integer DEFAULT_CAST_FAIL_CHANCE = 1;
    private static final Integer UPDATED_CAST_FAIL_CHANCE = 2;

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final IncrustacionesArmadura DEFAULT_INCRUSTACION_ARMADURA = IncrustacionesArmadura.ACORAZADA_MENOR;
    private static final IncrustacionesArmadura UPDATED_INCRUSTACION_ARMADURA = IncrustacionesArmadura.ATRAYENTE_MENOR;

    @Autowired
    private ArmaduraRepository armaduraRepository;

    @Autowired
    private ArmaduraService armaduraService;

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

    private MockMvc restArmaduraMockMvc;

    private Armadura armadura;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ArmaduraResource armaduraResource = new ArmaduraResource(armaduraService);
        this.restArmaduraMockMvc = MockMvcBuilders.standaloneSetup(armaduraResource)
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
    public static Armadura createEntity(EntityManager em) {
        Armadura armadura = new Armadura()
            .type(DEFAULT_TYPE)
            .name(DEFAULT_NAME)
            .evasionBonus(DEFAULT_EVASION_BONUS)
            .maxDex(DEFAULT_MAX_DEX)
            .level(DEFAULT_LEVEL)
            .uc(DEFAULT_UC)
            .ppa(DEFAULT_PPA)
            .castFailChance(DEFAULT_CAST_FAIL_CHANCE)
            .price(DEFAULT_PRICE)
            .incrustacionArmadura(DEFAULT_INCRUSTACION_ARMADURA);
        return armadura;
    }

    @Before
    public void initTest() {
        armadura = createEntity(em);
    }

    @Test
    @Transactional
    public void createArmadura() throws Exception {
        int databaseSizeBeforeCreate = armaduraRepository.findAll().size();

        // Create the Armadura
        restArmaduraMockMvc.perform(post("/api/armaduras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(armadura)))
            .andExpect(status().isCreated());

        // Validate the Armadura in the database
        List<Armadura> armaduraList = armaduraRepository.findAll();
        assertThat(armaduraList).hasSize(databaseSizeBeforeCreate + 1);
        Armadura testArmadura = armaduraList.get(armaduraList.size() - 1);
        assertThat(testArmadura.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testArmadura.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testArmadura.getEvasionBonus()).isEqualTo(DEFAULT_EVASION_BONUS);
        assertThat(testArmadura.getMaxDex()).isEqualTo(DEFAULT_MAX_DEX);
        assertThat(testArmadura.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testArmadura.getUc()).isEqualTo(DEFAULT_UC);
        assertThat(testArmadura.getPpa()).isEqualTo(DEFAULT_PPA);
        assertThat(testArmadura.getCastFailChance()).isEqualTo(DEFAULT_CAST_FAIL_CHANCE);
        assertThat(testArmadura.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testArmadura.getIncrustacionArmadura()).isEqualTo(DEFAULT_INCRUSTACION_ARMADURA);
    }

    @Test
    @Transactional
    public void createArmaduraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = armaduraRepository.findAll().size();

        // Create the Armadura with an existing ID
        armadura.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArmaduraMockMvc.perform(post("/api/armaduras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(armadura)))
            .andExpect(status().isBadRequest());

        // Validate the Armadura in the database
        List<Armadura> armaduraList = armaduraRepository.findAll();
        assertThat(armaduraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllArmaduras() throws Exception {
        // Initialize the database
        armaduraRepository.saveAndFlush(armadura);

        // Get all the armaduraList
        restArmaduraMockMvc.perform(get("/api/armaduras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(armadura.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].evasionBonus").value(hasItem(DEFAULT_EVASION_BONUS)))
            .andExpect(jsonPath("$.[*].maxDex").value(hasItem(DEFAULT_MAX_DEX)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].uc").value(hasItem(DEFAULT_UC)))
            .andExpect(jsonPath("$.[*].ppa").value(hasItem(DEFAULT_PPA)))
            .andExpect(jsonPath("$.[*].castFailChance").value(hasItem(DEFAULT_CAST_FAIL_CHANCE)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].incrustacionArmadura").value(hasItem(DEFAULT_INCRUSTACION_ARMADURA.toString())));
    }
    
    @Test
    @Transactional
    public void getArmadura() throws Exception {
        // Initialize the database
        armaduraRepository.saveAndFlush(armadura);

        // Get the armadura
        restArmaduraMockMvc.perform(get("/api/armaduras/{id}", armadura.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(armadura.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.evasionBonus").value(DEFAULT_EVASION_BONUS))
            .andExpect(jsonPath("$.maxDex").value(DEFAULT_MAX_DEX))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.uc").value(DEFAULT_UC))
            .andExpect(jsonPath("$.ppa").value(DEFAULT_PPA))
            .andExpect(jsonPath("$.castFailChance").value(DEFAULT_CAST_FAIL_CHANCE))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.incrustacionArmadura").value(DEFAULT_INCRUSTACION_ARMADURA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingArmadura() throws Exception {
        // Get the armadura
        restArmaduraMockMvc.perform(get("/api/armaduras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArmadura() throws Exception {
        // Initialize the database
        armaduraService.save(armadura);

        int databaseSizeBeforeUpdate = armaduraRepository.findAll().size();

        // Update the armadura
        Armadura updatedArmadura = armaduraRepository.findById(armadura.getId()).get();
        // Disconnect from session so that the updates on updatedArmadura are not directly saved in db
        em.detach(updatedArmadura);
        updatedArmadura
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .evasionBonus(UPDATED_EVASION_BONUS)
            .maxDex(UPDATED_MAX_DEX)
            .level(UPDATED_LEVEL)
            .uc(UPDATED_UC)
            .ppa(UPDATED_PPA)
            .castFailChance(UPDATED_CAST_FAIL_CHANCE)
            .price(UPDATED_PRICE)
            .incrustacionArmadura(UPDATED_INCRUSTACION_ARMADURA);

        restArmaduraMockMvc.perform(put("/api/armaduras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedArmadura)))
            .andExpect(status().isOk());

        // Validate the Armadura in the database
        List<Armadura> armaduraList = armaduraRepository.findAll();
        assertThat(armaduraList).hasSize(databaseSizeBeforeUpdate);
        Armadura testArmadura = armaduraList.get(armaduraList.size() - 1);
        assertThat(testArmadura.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testArmadura.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testArmadura.getEvasionBonus()).isEqualTo(UPDATED_EVASION_BONUS);
        assertThat(testArmadura.getMaxDex()).isEqualTo(UPDATED_MAX_DEX);
        assertThat(testArmadura.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testArmadura.getUc()).isEqualTo(UPDATED_UC);
        assertThat(testArmadura.getPpa()).isEqualTo(UPDATED_PPA);
        assertThat(testArmadura.getCastFailChance()).isEqualTo(UPDATED_CAST_FAIL_CHANCE);
        assertThat(testArmadura.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testArmadura.getIncrustacionArmadura()).isEqualTo(UPDATED_INCRUSTACION_ARMADURA);
    }

    @Test
    @Transactional
    public void updateNonExistingArmadura() throws Exception {
        int databaseSizeBeforeUpdate = armaduraRepository.findAll().size();

        // Create the Armadura

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArmaduraMockMvc.perform(put("/api/armaduras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(armadura)))
            .andExpect(status().isBadRequest());

        // Validate the Armadura in the database
        List<Armadura> armaduraList = armaduraRepository.findAll();
        assertThat(armaduraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArmadura() throws Exception {
        // Initialize the database
        armaduraService.save(armadura);

        int databaseSizeBeforeDelete = armaduraRepository.findAll().size();

        // Delete the armadura
        restArmaduraMockMvc.perform(delete("/api/armaduras/{id}", armadura.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Armadura> armaduraList = armaduraRepository.findAll();
        assertThat(armaduraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Armadura.class);
        Armadura armadura1 = new Armadura();
        armadura1.setId(1L);
        Armadura armadura2 = new Armadura();
        armadura2.setId(armadura1.getId());
        assertThat(armadura1).isEqualTo(armadura2);
        armadura2.setId(2L);
        assertThat(armadura1).isNotEqualTo(armadura2);
        armadura1.setId(null);
        assertThat(armadura1).isNotEqualTo(armadura2);
    }
}
