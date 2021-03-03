package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.Sigmibackn2App;
import co.gov.and.mintic.sigmi.domain.NaturalezaJuridica;
import co.gov.and.mintic.sigmi.repository.NaturalezaJuridicaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NaturalezaJuridicaResource} REST controller.
 */
@SpringBootTest(classes = Sigmibackn2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class NaturalezaJuridicaResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_SIGLA = "AAA";
    private static final String UPDATED_SIGLA = "BBB";

    private static final Instant DEFAULT_FECHA_CREACION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_CREACION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_ESTADO = 1;
    private static final Integer UPDATED_ESTADO = 2;

    @Autowired
    private NaturalezaJuridicaRepository naturalezaJuridicaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNaturalezaJuridicaMockMvc;

    private NaturalezaJuridica naturalezaJuridica;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NaturalezaJuridica createEntity(EntityManager em) {
        NaturalezaJuridica naturalezaJuridica = new NaturalezaJuridica()
            .nombre(DEFAULT_NOMBRE)
            .sigla(DEFAULT_SIGLA)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .estado(DEFAULT_ESTADO);
        return naturalezaJuridica;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NaturalezaJuridica createUpdatedEntity(EntityManager em) {
        NaturalezaJuridica naturalezaJuridica = new NaturalezaJuridica()
            .nombre(UPDATED_NOMBRE)
            .sigla(UPDATED_SIGLA)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .estado(UPDATED_ESTADO);
        return naturalezaJuridica;
    }

    @BeforeEach
    public void initTest() {
        naturalezaJuridica = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllNaturalezaJuridicas() throws Exception {
        // Initialize the database
        naturalezaJuridicaRepository.saveAndFlush(naturalezaJuridica);

        // Get all the naturalezaJuridicaList
        restNaturalezaJuridicaMockMvc.perform(get("/api/naturaleza-juridicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(naturalezaJuridica.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].sigla").value(hasItem(DEFAULT_SIGLA)))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)));
    }
    
    @Test
    @Transactional
    public void getNaturalezaJuridica() throws Exception {
        // Initialize the database
        naturalezaJuridicaRepository.saveAndFlush(naturalezaJuridica);

        // Get the naturalezaJuridica
        restNaturalezaJuridicaMockMvc.perform(get("/api/naturaleza-juridicas/{id}", naturalezaJuridica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(naturalezaJuridica.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.sigla").value(DEFAULT_SIGLA))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO));
    }
    @Test
    @Transactional
    public void getNonExistingNaturalezaJuridica() throws Exception {
        // Get the naturalezaJuridica
        restNaturalezaJuridicaMockMvc.perform(get("/api/naturaleza-juridicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
