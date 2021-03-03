package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.Sigmibackn2App;
import co.gov.and.mintic.sigmi.domain.Municipio;
import co.gov.and.mintic.sigmi.domain.Departamento;
import co.gov.and.mintic.sigmi.repository.MunicipioRepository;

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
 * Integration tests for the {@link MunicipioResource} REST controller.
 */
@SpringBootTest(classes = Sigmibackn2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class MunicipioResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final Instant DEFAULT_FECHA_CREACION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_CREACION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_ESTADO = 1;
    private static final Integer UPDATED_ESTADO = 2;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMunicipioMockMvc;

    private Municipio municipio;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Municipio createEntity(EntityManager em) {
        Municipio municipio = new Municipio()
            .nombre(DEFAULT_NOMBRE)
            .codigo(DEFAULT_CODIGO)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .estado(DEFAULT_ESTADO);
        // Add required entity
        Departamento departamento;
        if (TestUtil.findAll(em, Departamento.class).isEmpty()) {
            departamento = DepartamentoResourceIT.createEntity(em);
            em.persist(departamento);
            em.flush();
        } else {
            departamento = TestUtil.findAll(em, Departamento.class).get(0);
        }
        municipio.setDepartamento(departamento);
        return municipio;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Municipio createUpdatedEntity(EntityManager em) {
        Municipio municipio = new Municipio()
            .nombre(UPDATED_NOMBRE)
            .codigo(UPDATED_CODIGO)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .estado(UPDATED_ESTADO);
        // Add required entity
        Departamento departamento;
        if (TestUtil.findAll(em, Departamento.class).isEmpty()) {
            departamento = DepartamentoResourceIT.createUpdatedEntity(em);
            em.persist(departamento);
            em.flush();
        } else {
            departamento = TestUtil.findAll(em, Departamento.class).get(0);
        }
        municipio.setDepartamento(departamento);
        return municipio;
    }

    @BeforeEach
    public void initTest() {
        municipio = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllMunicipios() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get all the municipioList
        restMunicipioMockMvc.perform(get("/api/municipios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(municipio.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)));
    }
    
    @Test
    @Transactional
    public void getMunicipio() throws Exception {
        // Initialize the database
        municipioRepository.saveAndFlush(municipio);

        // Get the municipio
        restMunicipioMockMvc.perform(get("/api/municipios/{id}", municipio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(municipio.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO));
    }
    @Test
    @Transactional
    public void getNonExistingMunicipio() throws Exception {
        // Get the municipio
        restMunicipioMockMvc.perform(get("/api/municipios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
