package com.charactergenerator.reyerta.web.rest;

import com.charactergenerator.reyerta.ReyertaApp;

import com.charactergenerator.reyerta.domain.Skill;
import com.charactergenerator.reyerta.repository.SkillRepository;
import com.charactergenerator.reyerta.service.SkillService;
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
 * Test class for the SkillResource REST controller.
 *
 * @see SkillResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReyertaApp.class)
public class SkillResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Profile DEFAULT_RELATED_PROFILE = Profile.CARISMA;
    private static final Profile UPDATED_RELATED_PROFILE = Profile.CONSTITUCION;

    private static final Integer DEFAULT_GRADE = 1;
    private static final Integer UPDATED_GRADE = 2;

    private static final Integer DEFAULT_MAGIC_OBJECT = 1;
    private static final Integer UPDATED_MAGIC_OBJECT = 2;

    private static final Integer DEFAULT_OTHER = 1;
    private static final Integer UPDATED_OTHER = 2;

    private static final Integer DEFAULT_PENALIZER = 1;
    private static final Integer UPDATED_PENALIZER = 2;

    private static final Boolean DEFAULT_IS_TRAINED = false;
    private static final Boolean UPDATED_IS_TRAINED = true;

    private static final Boolean DEFAULT_IS_EXPERTISE = false;
    private static final Boolean UPDATED_IS_EXPERTISE = true;

    private static final Boolean DEFAULT_IS_PENALIZED = false;
    private static final Boolean UPDATED_IS_PENALIZED = true;

    private static final Boolean DEFAULT_IS_PENALIZED_BY_LANGUAGE = false;
    private static final Boolean UPDATED_IS_PENALIZED_BY_LANGUAGE = true;

    private static final Integer DEFAULT_GIRSO_PIECES = 1;
    private static final Integer UPDATED_GIRSO_PIECES = 2;

    private static final Integer DEFAULT_PLATINUM_PIECES = 1;
    private static final Integer UPDATED_PLATINUM_PIECES = 2;

    private static final Integer DEFAULT_GOLD_PIECES = 1;
    private static final Integer UPDATED_GOLD_PIECES = 2;

    private static final Integer DEFAULT_SILVER_PIECES = 1;
    private static final Integer UPDATED_SILVER_PIECES = 2;

    private static final Integer DEFAULT_COPPER_PIECES = 1;
    private static final Integer UPDATED_COPPER_PIECES = 2;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillService skillService;

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

    private MockMvc restSkillMockMvc;

    private Skill skill;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SkillResource skillResource = new SkillResource(skillService);
        this.restSkillMockMvc = MockMvcBuilders.standaloneSetup(skillResource)
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
    public static Skill createEntity(EntityManager em) {
        Skill skill = new Skill()
            .name(DEFAULT_NAME)
            .relatedProfile(DEFAULT_RELATED_PROFILE)
            .grade(DEFAULT_GRADE)
            .magicObject(DEFAULT_MAGIC_OBJECT)
            .other(DEFAULT_OTHER)
            .penalizer(DEFAULT_PENALIZER)
            .isTrained(DEFAULT_IS_TRAINED)
            .isExpertise(DEFAULT_IS_EXPERTISE)
            .isPenalized(DEFAULT_IS_PENALIZED)
            .isPenalizedByLanguage(DEFAULT_IS_PENALIZED_BY_LANGUAGE)
            .girsoPieces(DEFAULT_GIRSO_PIECES)
            .platinumPieces(DEFAULT_PLATINUM_PIECES)
            .goldPieces(DEFAULT_GOLD_PIECES)
            .silverPieces(DEFAULT_SILVER_PIECES)
            .copperPieces(DEFAULT_COPPER_PIECES);
        return skill;
    }

    @Before
    public void initTest() {
        skill = createEntity(em);
    }

    @Test
    @Transactional
    public void createSkill() throws Exception {
        int databaseSizeBeforeCreate = skillRepository.findAll().size();

        // Create the Skill
        restSkillMockMvc.perform(post("/api/skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skill)))
            .andExpect(status().isCreated());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeCreate + 1);
        Skill testSkill = skillList.get(skillList.size() - 1);
        assertThat(testSkill.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSkill.getRelatedProfile()).isEqualTo(DEFAULT_RELATED_PROFILE);
        assertThat(testSkill.getGrade()).isEqualTo(DEFAULT_GRADE);
        assertThat(testSkill.getMagicObject()).isEqualTo(DEFAULT_MAGIC_OBJECT);
        assertThat(testSkill.getOther()).isEqualTo(DEFAULT_OTHER);
        assertThat(testSkill.getPenalizer()).isEqualTo(DEFAULT_PENALIZER);
        assertThat(testSkill.isIsTrained()).isEqualTo(DEFAULT_IS_TRAINED);
        assertThat(testSkill.isIsExpertise()).isEqualTo(DEFAULT_IS_EXPERTISE);
        assertThat(testSkill.isIsPenalized()).isEqualTo(DEFAULT_IS_PENALIZED);
        assertThat(testSkill.isIsPenalizedByLanguage()).isEqualTo(DEFAULT_IS_PENALIZED_BY_LANGUAGE);
        assertThat(testSkill.getGirsoPieces()).isEqualTo(DEFAULT_GIRSO_PIECES);
        assertThat(testSkill.getPlatinumPieces()).isEqualTo(DEFAULT_PLATINUM_PIECES);
        assertThat(testSkill.getGoldPieces()).isEqualTo(DEFAULT_GOLD_PIECES);
        assertThat(testSkill.getSilverPieces()).isEqualTo(DEFAULT_SILVER_PIECES);
        assertThat(testSkill.getCopperPieces()).isEqualTo(DEFAULT_COPPER_PIECES);
    }

    @Test
    @Transactional
    public void createSkillWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = skillRepository.findAll().size();

        // Create the Skill with an existing ID
        skill.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSkillMockMvc.perform(post("/api/skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skill)))
            .andExpect(status().isBadRequest());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSkills() throws Exception {
        // Initialize the database
        skillRepository.saveAndFlush(skill);

        // Get all the skillList
        restSkillMockMvc.perform(get("/api/skills?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(skill.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].relatedProfile").value(hasItem(DEFAULT_RELATED_PROFILE.toString())))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].magicObject").value(hasItem(DEFAULT_MAGIC_OBJECT)))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER)))
            .andExpect(jsonPath("$.[*].penalizer").value(hasItem(DEFAULT_PENALIZER)))
            .andExpect(jsonPath("$.[*].isTrained").value(hasItem(DEFAULT_IS_TRAINED.booleanValue())))
            .andExpect(jsonPath("$.[*].isExpertise").value(hasItem(DEFAULT_IS_EXPERTISE.booleanValue())))
            .andExpect(jsonPath("$.[*].isPenalized").value(hasItem(DEFAULT_IS_PENALIZED.booleanValue())))
            .andExpect(jsonPath("$.[*].isPenalizedByLanguage").value(hasItem(DEFAULT_IS_PENALIZED_BY_LANGUAGE.booleanValue())))
            .andExpect(jsonPath("$.[*].girsoPieces").value(hasItem(DEFAULT_GIRSO_PIECES)))
            .andExpect(jsonPath("$.[*].platinumPieces").value(hasItem(DEFAULT_PLATINUM_PIECES)))
            .andExpect(jsonPath("$.[*].goldPieces").value(hasItem(DEFAULT_GOLD_PIECES)))
            .andExpect(jsonPath("$.[*].silverPieces").value(hasItem(DEFAULT_SILVER_PIECES)))
            .andExpect(jsonPath("$.[*].copperPieces").value(hasItem(DEFAULT_COPPER_PIECES)));
    }
    
    @Test
    @Transactional
    public void getSkill() throws Exception {
        // Initialize the database
        skillRepository.saveAndFlush(skill);

        // Get the skill
        restSkillMockMvc.perform(get("/api/skills/{id}", skill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(skill.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.relatedProfile").value(DEFAULT_RELATED_PROFILE.toString()))
            .andExpect(jsonPath("$.grade").value(DEFAULT_GRADE))
            .andExpect(jsonPath("$.magicObject").value(DEFAULT_MAGIC_OBJECT))
            .andExpect(jsonPath("$.other").value(DEFAULT_OTHER))
            .andExpect(jsonPath("$.penalizer").value(DEFAULT_PENALIZER))
            .andExpect(jsonPath("$.isTrained").value(DEFAULT_IS_TRAINED.booleanValue()))
            .andExpect(jsonPath("$.isExpertise").value(DEFAULT_IS_EXPERTISE.booleanValue()))
            .andExpect(jsonPath("$.isPenalized").value(DEFAULT_IS_PENALIZED.booleanValue()))
            .andExpect(jsonPath("$.isPenalizedByLanguage").value(DEFAULT_IS_PENALIZED_BY_LANGUAGE.booleanValue()))
            .andExpect(jsonPath("$.girsoPieces").value(DEFAULT_GIRSO_PIECES))
            .andExpect(jsonPath("$.platinumPieces").value(DEFAULT_PLATINUM_PIECES))
            .andExpect(jsonPath("$.goldPieces").value(DEFAULT_GOLD_PIECES))
            .andExpect(jsonPath("$.silverPieces").value(DEFAULT_SILVER_PIECES))
            .andExpect(jsonPath("$.copperPieces").value(DEFAULT_COPPER_PIECES));
    }

    @Test
    @Transactional
    public void getNonExistingSkill() throws Exception {
        // Get the skill
        restSkillMockMvc.perform(get("/api/skills/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSkill() throws Exception {
        // Initialize the database
        skillService.save(skill);

        int databaseSizeBeforeUpdate = skillRepository.findAll().size();

        // Update the skill
        Skill updatedSkill = skillRepository.findById(skill.getId()).get();
        // Disconnect from session so that the updates on updatedSkill are not directly saved in db
        em.detach(updatedSkill);
        updatedSkill
            .name(UPDATED_NAME)
            .relatedProfile(UPDATED_RELATED_PROFILE)
            .grade(UPDATED_GRADE)
            .magicObject(UPDATED_MAGIC_OBJECT)
            .other(UPDATED_OTHER)
            .penalizer(UPDATED_PENALIZER)
            .isTrained(UPDATED_IS_TRAINED)
            .isExpertise(UPDATED_IS_EXPERTISE)
            .isPenalized(UPDATED_IS_PENALIZED)
            .isPenalizedByLanguage(UPDATED_IS_PENALIZED_BY_LANGUAGE)
            .girsoPieces(UPDATED_GIRSO_PIECES)
            .platinumPieces(UPDATED_PLATINUM_PIECES)
            .goldPieces(UPDATED_GOLD_PIECES)
            .silverPieces(UPDATED_SILVER_PIECES)
            .copperPieces(UPDATED_COPPER_PIECES);

        restSkillMockMvc.perform(put("/api/skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSkill)))
            .andExpect(status().isOk());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeUpdate);
        Skill testSkill = skillList.get(skillList.size() - 1);
        assertThat(testSkill.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSkill.getRelatedProfile()).isEqualTo(UPDATED_RELATED_PROFILE);
        assertThat(testSkill.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testSkill.getMagicObject()).isEqualTo(UPDATED_MAGIC_OBJECT);
        assertThat(testSkill.getOther()).isEqualTo(UPDATED_OTHER);
        assertThat(testSkill.getPenalizer()).isEqualTo(UPDATED_PENALIZER);
        assertThat(testSkill.isIsTrained()).isEqualTo(UPDATED_IS_TRAINED);
        assertThat(testSkill.isIsExpertise()).isEqualTo(UPDATED_IS_EXPERTISE);
        assertThat(testSkill.isIsPenalized()).isEqualTo(UPDATED_IS_PENALIZED);
        assertThat(testSkill.isIsPenalizedByLanguage()).isEqualTo(UPDATED_IS_PENALIZED_BY_LANGUAGE);
        assertThat(testSkill.getGirsoPieces()).isEqualTo(UPDATED_GIRSO_PIECES);
        assertThat(testSkill.getPlatinumPieces()).isEqualTo(UPDATED_PLATINUM_PIECES);
        assertThat(testSkill.getGoldPieces()).isEqualTo(UPDATED_GOLD_PIECES);
        assertThat(testSkill.getSilverPieces()).isEqualTo(UPDATED_SILVER_PIECES);
        assertThat(testSkill.getCopperPieces()).isEqualTo(UPDATED_COPPER_PIECES);
    }

    @Test
    @Transactional
    public void updateNonExistingSkill() throws Exception {
        int databaseSizeBeforeUpdate = skillRepository.findAll().size();

        // Create the Skill

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSkillMockMvc.perform(put("/api/skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skill)))
            .andExpect(status().isBadRequest());

        // Validate the Skill in the database
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSkill() throws Exception {
        // Initialize the database
        skillService.save(skill);

        int databaseSizeBeforeDelete = skillRepository.findAll().size();

        // Delete the skill
        restSkillMockMvc.perform(delete("/api/skills/{id}", skill.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Skill> skillList = skillRepository.findAll();
        assertThat(skillList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Skill.class);
        Skill skill1 = new Skill();
        skill1.setId(1L);
        Skill skill2 = new Skill();
        skill2.setId(skill1.getId());
        assertThat(skill1).isEqualTo(skill2);
        skill2.setId(2L);
        assertThat(skill1).isNotEqualTo(skill2);
        skill1.setId(null);
        assertThat(skill1).isNotEqualTo(skill2);
    }
}
