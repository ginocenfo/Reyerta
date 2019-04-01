package com.charactergenerator.reyerta.web.rest;

import com.charactergenerator.reyerta.ReyertaApp;

import com.charactergenerator.reyerta.domain.Personaje;
import com.charactergenerator.reyerta.repository.PersonajeRepository;
import com.charactergenerator.reyerta.service.PersonajeService;
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

import com.charactergenerator.reyerta.domain.enumeration.Alignment;
import com.charactergenerator.reyerta.domain.enumeration.Dexterity;
import com.charactergenerator.reyerta.domain.enumeration.Sizes;
import com.charactergenerator.reyerta.domain.enumeration.Races;
import com.charactergenerator.reyerta.domain.enumeration.CharacterClase;
/**
 * Test class for the PersonajeResource REST controller.
 *
 * @see PersonajeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReyertaApp.class)
public class PersonajeResourceIntTest {

    private static final String DEFAULT_OWNER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OWNER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CHARACTER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHARACTER_NAME = "BBBBBBBBBB";

    private static final Alignment DEFAULT_ALIGNMENT = Alignment.CAOTICO_BUENO;
    private static final Alignment UPDATED_ALIGNMENT = Alignment.CAOTICO_NEUTRAL;

    private static final String DEFAULT_RELIGION = "AAAAAAAAAA";
    private static final String UPDATED_RELIGION = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final Integer DEFAULT_REAL_AGE = 1;
    private static final Integer UPDATED_REAL_AGE = 2;

    private static final Integer DEFAULT_APPARENT_AGE = 1;
    private static final Integer UPDATED_APPARENT_AGE = 2;

    private static final Dexterity DEFAULT_DEXTERITY = Dexterity.DIESTRO;
    private static final Dexterity UPDATED_DEXTERITY = Dexterity.ZURDO;

    private static final String DEFAULT_ORIGIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORIGIN_NAME = "BBBBBBBBBB";

    private static final Sizes DEFAULT_SIZE = Sizes.GIGANTIC;
    private static final Sizes UPDATED_SIZE = Sizes.LARGE;

    private static final Double DEFAULT_HEIGHT = 1D;
    private static final Double UPDATED_HEIGHT = 2D;

    private static final Double DEFAULT_WEIGHT = 1D;
    private static final Double UPDATED_WEIGHT = 2D;

    private static final String DEFAULT_EYE_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_EYE_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_HAIR_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_HAIR_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_TEZ_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_TEZ_COLOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAX_HIT_POINTS = 1;
    private static final Integer UPDATED_MAX_HIT_POINTS = 2;

    private static final Integer DEFAULT_CURRENT_HIT_POINTS = 1;
    private static final Integer UPDATED_CURRENT_HIT_POINTS = 2;

    private static final Races DEFAULT_RACE = Races.ALARDYS;
    private static final Races UPDATED_RACE = Races.ALFIR_ELAK;

    private static final CharacterClase DEFAULT_CHARACTER_CLASS = CharacterClase.ASESINO;
    private static final CharacterClase UPDATED_CHARACTER_CLASS = CharacterClase.BARBARO;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PersonajeService personajeService;

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

    private MockMvc restPersonajeMockMvc;

    private Personaje personaje;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PersonajeResource personajeResource = new PersonajeResource(personajeService);
        this.restPersonajeMockMvc = MockMvcBuilders.standaloneSetup(personajeResource)
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
    public static Personaje createEntity(EntityManager em) {
        Personaje personaje = new Personaje()
            .ownerName(DEFAULT_OWNER_NAME)
            .characterName(DEFAULT_CHARACTER_NAME)
            .alignment(DEFAULT_ALIGNMENT)
            .religion(DEFAULT_RELIGION)
            .gender(DEFAULT_GENDER)
            .realAge(DEFAULT_REAL_AGE)
            .apparentAge(DEFAULT_APPARENT_AGE)
            .dexterity(DEFAULT_DEXTERITY)
            .originName(DEFAULT_ORIGIN_NAME)
            .size(DEFAULT_SIZE)
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .eyeColor(DEFAULT_EYE_COLOR)
            .hairColor(DEFAULT_HAIR_COLOR)
            .tezColor(DEFAULT_TEZ_COLOR)
            .maxHitPoints(DEFAULT_MAX_HIT_POINTS)
            .currentHitPoints(DEFAULT_CURRENT_HIT_POINTS)
            .race(DEFAULT_RACE)
            .characterClass(DEFAULT_CHARACTER_CLASS);
        return personaje;
    }

    @Before
    public void initTest() {
        personaje = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonaje() throws Exception {
        int databaseSizeBeforeCreate = personajeRepository.findAll().size();

        // Create the Personaje
        restPersonajeMockMvc.perform(post("/api/personajes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personaje)))
            .andExpect(status().isCreated());

        // Validate the Personaje in the database
        List<Personaje> personajeList = personajeRepository.findAll();
        assertThat(personajeList).hasSize(databaseSizeBeforeCreate + 1);
        Personaje testPersonaje = personajeList.get(personajeList.size() - 1);
        assertThat(testPersonaje.getOwnerName()).isEqualTo(DEFAULT_OWNER_NAME);
        assertThat(testPersonaje.getCharacterName()).isEqualTo(DEFAULT_CHARACTER_NAME);
        assertThat(testPersonaje.getAlignment()).isEqualTo(DEFAULT_ALIGNMENT);
        assertThat(testPersonaje.getReligion()).isEqualTo(DEFAULT_RELIGION);
        assertThat(testPersonaje.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testPersonaje.getRealAge()).isEqualTo(DEFAULT_REAL_AGE);
        assertThat(testPersonaje.getApparentAge()).isEqualTo(DEFAULT_APPARENT_AGE);
        assertThat(testPersonaje.getDexterity()).isEqualTo(DEFAULT_DEXTERITY);
        assertThat(testPersonaje.getOriginName()).isEqualTo(DEFAULT_ORIGIN_NAME);
        assertThat(testPersonaje.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testPersonaje.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testPersonaje.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testPersonaje.getEyeColor()).isEqualTo(DEFAULT_EYE_COLOR);
        assertThat(testPersonaje.getHairColor()).isEqualTo(DEFAULT_HAIR_COLOR);
        assertThat(testPersonaje.getTezColor()).isEqualTo(DEFAULT_TEZ_COLOR);
        assertThat(testPersonaje.getMaxHitPoints()).isEqualTo(DEFAULT_MAX_HIT_POINTS);
        assertThat(testPersonaje.getCurrentHitPoints()).isEqualTo(DEFAULT_CURRENT_HIT_POINTS);
        assertThat(testPersonaje.getRace()).isEqualTo(DEFAULT_RACE);
        assertThat(testPersonaje.getCharacterClass()).isEqualTo(DEFAULT_CHARACTER_CLASS);
    }

    @Test
    @Transactional
    public void createPersonajeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personajeRepository.findAll().size();

        // Create the Personaje with an existing ID
        personaje.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonajeMockMvc.perform(post("/api/personajes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personaje)))
            .andExpect(status().isBadRequest());

        // Validate the Personaje in the database
        List<Personaje> personajeList = personajeRepository.findAll();
        assertThat(personajeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPersonajes() throws Exception {
        // Initialize the database
        personajeRepository.saveAndFlush(personaje);

        // Get all the personajeList
        restPersonajeMockMvc.perform(get("/api/personajes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personaje.getId().intValue())))
            .andExpect(jsonPath("$.[*].ownerName").value(hasItem(DEFAULT_OWNER_NAME.toString())))
            .andExpect(jsonPath("$.[*].characterName").value(hasItem(DEFAULT_CHARACTER_NAME.toString())))
            .andExpect(jsonPath("$.[*].alignment").value(hasItem(DEFAULT_ALIGNMENT.toString())))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].realAge").value(hasItem(DEFAULT_REAL_AGE)))
            .andExpect(jsonPath("$.[*].apparentAge").value(hasItem(DEFAULT_APPARENT_AGE)))
            .andExpect(jsonPath("$.[*].dexterity").value(hasItem(DEFAULT_DEXTERITY.toString())))
            .andExpect(jsonPath("$.[*].originName").value(hasItem(DEFAULT_ORIGIN_NAME.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.toString())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].eyeColor").value(hasItem(DEFAULT_EYE_COLOR.toString())))
            .andExpect(jsonPath("$.[*].hairColor").value(hasItem(DEFAULT_HAIR_COLOR.toString())))
            .andExpect(jsonPath("$.[*].tezColor").value(hasItem(DEFAULT_TEZ_COLOR.toString())))
            .andExpect(jsonPath("$.[*].maxHitPoints").value(hasItem(DEFAULT_MAX_HIT_POINTS)))
            .andExpect(jsonPath("$.[*].currentHitPoints").value(hasItem(DEFAULT_CURRENT_HIT_POINTS)))
            .andExpect(jsonPath("$.[*].race").value(hasItem(DEFAULT_RACE.toString())))
            .andExpect(jsonPath("$.[*].characterClass").value(hasItem(DEFAULT_CHARACTER_CLASS.toString())));
    }
    
    @Test
    @Transactional
    public void getPersonaje() throws Exception {
        // Initialize the database
        personajeRepository.saveAndFlush(personaje);

        // Get the personaje
        restPersonajeMockMvc.perform(get("/api/personajes/{id}", personaje.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(personaje.getId().intValue()))
            .andExpect(jsonPath("$.ownerName").value(DEFAULT_OWNER_NAME.toString()))
            .andExpect(jsonPath("$.characterName").value(DEFAULT_CHARACTER_NAME.toString()))
            .andExpect(jsonPath("$.alignment").value(DEFAULT_ALIGNMENT.toString()))
            .andExpect(jsonPath("$.religion").value(DEFAULT_RELIGION.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.realAge").value(DEFAULT_REAL_AGE))
            .andExpect(jsonPath("$.apparentAge").value(DEFAULT_APPARENT_AGE))
            .andExpect(jsonPath("$.dexterity").value(DEFAULT_DEXTERITY.toString()))
            .andExpect(jsonPath("$.originName").value(DEFAULT_ORIGIN_NAME.toString()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.toString()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.eyeColor").value(DEFAULT_EYE_COLOR.toString()))
            .andExpect(jsonPath("$.hairColor").value(DEFAULT_HAIR_COLOR.toString()))
            .andExpect(jsonPath("$.tezColor").value(DEFAULT_TEZ_COLOR.toString()))
            .andExpect(jsonPath("$.maxHitPoints").value(DEFAULT_MAX_HIT_POINTS))
            .andExpect(jsonPath("$.currentHitPoints").value(DEFAULT_CURRENT_HIT_POINTS))
            .andExpect(jsonPath("$.race").value(DEFAULT_RACE.toString()))
            .andExpect(jsonPath("$.characterClass").value(DEFAULT_CHARACTER_CLASS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPersonaje() throws Exception {
        // Get the personaje
        restPersonajeMockMvc.perform(get("/api/personajes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonaje() throws Exception {
        // Initialize the database
        personajeService.save(personaje);

        int databaseSizeBeforeUpdate = personajeRepository.findAll().size();

        // Update the personaje
        Personaje updatedPersonaje = personajeRepository.findById(personaje.getId()).get();
        // Disconnect from session so that the updates on updatedPersonaje are not directly saved in db
        em.detach(updatedPersonaje);
        updatedPersonaje
            .ownerName(UPDATED_OWNER_NAME)
            .characterName(UPDATED_CHARACTER_NAME)
            .alignment(UPDATED_ALIGNMENT)
            .religion(UPDATED_RELIGION)
            .gender(UPDATED_GENDER)
            .realAge(UPDATED_REAL_AGE)
            .apparentAge(UPDATED_APPARENT_AGE)
            .dexterity(UPDATED_DEXTERITY)
            .originName(UPDATED_ORIGIN_NAME)
            .size(UPDATED_SIZE)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .eyeColor(UPDATED_EYE_COLOR)
            .hairColor(UPDATED_HAIR_COLOR)
            .tezColor(UPDATED_TEZ_COLOR)
            .maxHitPoints(UPDATED_MAX_HIT_POINTS)
            .currentHitPoints(UPDATED_CURRENT_HIT_POINTS)
            .race(UPDATED_RACE)
            .characterClass(UPDATED_CHARACTER_CLASS);

        restPersonajeMockMvc.perform(put("/api/personajes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPersonaje)))
            .andExpect(status().isOk());

        // Validate the Personaje in the database
        List<Personaje> personajeList = personajeRepository.findAll();
        assertThat(personajeList).hasSize(databaseSizeBeforeUpdate);
        Personaje testPersonaje = personajeList.get(personajeList.size() - 1);
        assertThat(testPersonaje.getOwnerName()).isEqualTo(UPDATED_OWNER_NAME);
        assertThat(testPersonaje.getCharacterName()).isEqualTo(UPDATED_CHARACTER_NAME);
        assertThat(testPersonaje.getAlignment()).isEqualTo(UPDATED_ALIGNMENT);
        assertThat(testPersonaje.getReligion()).isEqualTo(UPDATED_RELIGION);
        assertThat(testPersonaje.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testPersonaje.getRealAge()).isEqualTo(UPDATED_REAL_AGE);
        assertThat(testPersonaje.getApparentAge()).isEqualTo(UPDATED_APPARENT_AGE);
        assertThat(testPersonaje.getDexterity()).isEqualTo(UPDATED_DEXTERITY);
        assertThat(testPersonaje.getOriginName()).isEqualTo(UPDATED_ORIGIN_NAME);
        assertThat(testPersonaje.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testPersonaje.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testPersonaje.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testPersonaje.getEyeColor()).isEqualTo(UPDATED_EYE_COLOR);
        assertThat(testPersonaje.getHairColor()).isEqualTo(UPDATED_HAIR_COLOR);
        assertThat(testPersonaje.getTezColor()).isEqualTo(UPDATED_TEZ_COLOR);
        assertThat(testPersonaje.getMaxHitPoints()).isEqualTo(UPDATED_MAX_HIT_POINTS);
        assertThat(testPersonaje.getCurrentHitPoints()).isEqualTo(UPDATED_CURRENT_HIT_POINTS);
        assertThat(testPersonaje.getRace()).isEqualTo(UPDATED_RACE);
        assertThat(testPersonaje.getCharacterClass()).isEqualTo(UPDATED_CHARACTER_CLASS);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonaje() throws Exception {
        int databaseSizeBeforeUpdate = personajeRepository.findAll().size();

        // Create the Personaje

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonajeMockMvc.perform(put("/api/personajes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personaje)))
            .andExpect(status().isBadRequest());

        // Validate the Personaje in the database
        List<Personaje> personajeList = personajeRepository.findAll();
        assertThat(personajeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonaje() throws Exception {
        // Initialize the database
        personajeService.save(personaje);

        int databaseSizeBeforeDelete = personajeRepository.findAll().size();

        // Delete the personaje
        restPersonajeMockMvc.perform(delete("/api/personajes/{id}", personaje.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Personaje> personajeList = personajeRepository.findAll();
        assertThat(personajeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Personaje.class);
        Personaje personaje1 = new Personaje();
        personaje1.setId(1L);
        Personaje personaje2 = new Personaje();
        personaje2.setId(personaje1.getId());
        assertThat(personaje1).isEqualTo(personaje2);
        personaje2.setId(2L);
        assertThat(personaje1).isNotEqualTo(personaje2);
        personaje1.setId(null);
        assertThat(personaje1).isNotEqualTo(personaje2);
    }
}
