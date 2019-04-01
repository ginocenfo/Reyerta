package com.charactergenerator.reyerta.web.rest;

import com.charactergenerator.reyerta.ReyertaApp;

import com.charactergenerator.reyerta.domain.Dado;
import com.charactergenerator.reyerta.repository.DadoRepository;
import com.charactergenerator.reyerta.service.DadoService;
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
 * Test class for the DadoResource REST controller.
 *
 * @see DadoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReyertaApp.class)
public class DadoResourceIntTest {

    private static final Integer DEFAULT_SIZE = 1;
    private static final Integer UPDATED_SIZE = 2;

    private static final Integer DEFAULT_CANT = 1;
    private static final Integer UPDATED_CANT = 2;

    @Autowired
    private DadoRepository dadoRepository;

    @Autowired
    private DadoService dadoService;

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

    private MockMvc restDadoMockMvc;

    private Dado dado;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DadoResource dadoResource = new DadoResource(dadoService);
        this.restDadoMockMvc = MockMvcBuilders.standaloneSetup(dadoResource)
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
    public static Dado createEntity(EntityManager em) {
        Dado dado = new Dado()
            .size(DEFAULT_SIZE)
            .cant(DEFAULT_CANT);
        return dado;
    }

    @Before
    public void initTest() {
        dado = createEntity(em);
    }

    @Test
    @Transactional
    public void createDado() throws Exception {
        int databaseSizeBeforeCreate = dadoRepository.findAll().size();

        // Create the Dado
        restDadoMockMvc.perform(post("/api/dados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dado)))
            .andExpect(status().isCreated());

        // Validate the Dado in the database
        List<Dado> dadoList = dadoRepository.findAll();
        assertThat(dadoList).hasSize(databaseSizeBeforeCreate + 1);
        Dado testDado = dadoList.get(dadoList.size() - 1);
        assertThat(testDado.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testDado.getCant()).isEqualTo(DEFAULT_CANT);
    }

    @Test
    @Transactional
    public void createDadoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dadoRepository.findAll().size();

        // Create the Dado with an existing ID
        dado.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDadoMockMvc.perform(post("/api/dados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dado)))
            .andExpect(status().isBadRequest());

        // Validate the Dado in the database
        List<Dado> dadoList = dadoRepository.findAll();
        assertThat(dadoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDados() throws Exception {
        // Initialize the database
        dadoRepository.saveAndFlush(dado);

        // Get all the dadoList
        restDadoMockMvc.perform(get("/api/dados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dado.getId().intValue())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)))
            .andExpect(jsonPath("$.[*].cant").value(hasItem(DEFAULT_CANT)));
    }
    
    @Test
    @Transactional
    public void getDado() throws Exception {
        // Initialize the database
        dadoRepository.saveAndFlush(dado);

        // Get the dado
        restDadoMockMvc.perform(get("/api/dados/{id}", dado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dado.getId().intValue()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE))
            .andExpect(jsonPath("$.cant").value(DEFAULT_CANT));
    }

    @Test
    @Transactional
    public void getNonExistingDado() throws Exception {
        // Get the dado
        restDadoMockMvc.perform(get("/api/dados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDado() throws Exception {
        // Initialize the database
        dadoService.save(dado);

        int databaseSizeBeforeUpdate = dadoRepository.findAll().size();

        // Update the dado
        Dado updatedDado = dadoRepository.findById(dado.getId()).get();
        // Disconnect from session so that the updates on updatedDado are not directly saved in db
        em.detach(updatedDado);
        updatedDado
            .size(UPDATED_SIZE)
            .cant(UPDATED_CANT);

        restDadoMockMvc.perform(put("/api/dados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDado)))
            .andExpect(status().isOk());

        // Validate the Dado in the database
        List<Dado> dadoList = dadoRepository.findAll();
        assertThat(dadoList).hasSize(databaseSizeBeforeUpdate);
        Dado testDado = dadoList.get(dadoList.size() - 1);
        assertThat(testDado.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testDado.getCant()).isEqualTo(UPDATED_CANT);
    }

    @Test
    @Transactional
    public void updateNonExistingDado() throws Exception {
        int databaseSizeBeforeUpdate = dadoRepository.findAll().size();

        // Create the Dado

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDadoMockMvc.perform(put("/api/dados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dado)))
            .andExpect(status().isBadRequest());

        // Validate the Dado in the database
        List<Dado> dadoList = dadoRepository.findAll();
        assertThat(dadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDado() throws Exception {
        // Initialize the database
        dadoService.save(dado);

        int databaseSizeBeforeDelete = dadoRepository.findAll().size();

        // Delete the dado
        restDadoMockMvc.perform(delete("/api/dados/{id}", dado.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Dado> dadoList = dadoRepository.findAll();
        assertThat(dadoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dado.class);
        Dado dado1 = new Dado();
        dado1.setId(1L);
        Dado dado2 = new Dado();
        dado2.setId(dado1.getId());
        assertThat(dado1).isEqualTo(dado2);
        dado2.setId(2L);
        assertThat(dado1).isNotEqualTo(dado2);
        dado1.setId(null);
        assertThat(dado1).isNotEqualTo(dado2);
    }
}
