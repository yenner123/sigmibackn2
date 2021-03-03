package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.Sigmibackn2App;
import co.gov.and.mintic.sigmi.domain.Departamento;
import co.gov.and.mintic.sigmi.domain.Pais;
import co.gov.and.mintic.sigmi.repository.DepartamentoRepository;

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
 * Integration tests for the {@link DepartamentoResource} REST controller.
 */
@SpringBootTest(classes = Sigmibackn2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class DepartamentoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final Instant DEFAULT_FECHA_CREACION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_CREACION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_ESTADO = 1;
    private static final Integer UPDATED_ESTADO = 2;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDepartamentoMockMvc;

    private Departamento departamento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Departamento createEntity(EntityManager em) {
        Departamento departamento = new Departamento()
            .nombre(DEFAULT_NOMBRE)
            .codigo(DEFAULT_CODIGO)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .estado(DEFAULT_ESTADO);
        // Add required entity
        Pais pais;
        if (TestUtil.findAll(em, Pais.class).isEmpty()) {
            pais = PaisResourceIT.createEntity(em);
            em.persist(pais);
            em.flush();
        } else {
            pais = TestUtil.findAll(em, Pais.class).get(0);
        }
        departamento.setPais(pais);
        return departamento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Departamento createUpdatedEntity(EntityManager em) {
        Departamento departamento = new Departamento()
            .nombre(UPDATED_NOMBRE)
            .codigo(UPDATED_CODIGO)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .estado(UPDATED_ESTADO);
        // Add required entity
        Pais pais;
        if (TestUtil.findAll(em, Pais.class).isEmpty()) {
            pais = PaisResourceIT.createUpdatedEntity(em);
            em.persist(pais);
            em.flush();
        } else {
            pais = TestUtil.findAll(em, Pais.class).get(0);
        }
        departamento.setPais(pais);
        return departamento;
    }

    @BeforeEach
    public void initTest() {
        departamento = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllDepartamentos() throws Exception {
        // Initialize the database
        departamentoRepository.saveAndFlush(departamento);

        // Get all the departamentoList
        restDepartamentoMockMvc.perform(get("/api/departamentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(departamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)));
    }
    
    @Test
    @Transactional
    public void getDepartamento() throws Exception {
        // Initialize the database
        departamentoRepository.saveAndFlush(departamento);

        // Get the departamento
        restDepartamentoMockMvc.perform(get("/api/departamentos/{id}", departamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(departamento.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO));
    }
    @Test
    @Transactional
    public void getNonExistingDepartamento() throws Exception {
        // Get the departamento
        restDepartamentoMockMvc.perform(get("/api/departamentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
