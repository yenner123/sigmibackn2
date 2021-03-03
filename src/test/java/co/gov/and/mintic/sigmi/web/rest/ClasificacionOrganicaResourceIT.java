package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.Sigmibackn2App;
import co.gov.and.mintic.sigmi.domain.ClasificacionOrganica;
import co.gov.and.mintic.sigmi.repository.ClasificacionOrganicaRepository;

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
 * Integration tests for the {@link ClasificacionOrganicaResource} REST controller.
 */
@SpringBootTest(classes = Sigmibackn2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class ClasificacionOrganicaResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_SIGLA = "AAA";
    private static final String UPDATED_SIGLA = "BBB";

    private static final Instant DEFAULT_FECHA_CREACION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_CREACION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_ESTADO = 1;
    private static final Integer UPDATED_ESTADO = 2;

    @Autowired
    private ClasificacionOrganicaRepository clasificacionOrganicaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClasificacionOrganicaMockMvc;

    private ClasificacionOrganica clasificacionOrganica;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClasificacionOrganica createEntity(EntityManager em) {
        ClasificacionOrganica clasificacionOrganica = new ClasificacionOrganica()
            .nombre(DEFAULT_NOMBRE)
            .sigla(DEFAULT_SIGLA)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .estado(DEFAULT_ESTADO);
        return clasificacionOrganica;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClasificacionOrganica createUpdatedEntity(EntityManager em) {
        ClasificacionOrganica clasificacionOrganica = new ClasificacionOrganica()
            .nombre(UPDATED_NOMBRE)
            .sigla(UPDATED_SIGLA)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .estado(UPDATED_ESTADO);
        return clasificacionOrganica;
    }

    @BeforeEach
    public void initTest() {
        clasificacionOrganica = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllClasificacionOrganicas() throws Exception {
        // Initialize the database
        clasificacionOrganicaRepository.saveAndFlush(clasificacionOrganica);

        // Get all the clasificacionOrganicaList
        restClasificacionOrganicaMockMvc.perform(get("/api/clasificacion-organicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clasificacionOrganica.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].sigla").value(hasItem(DEFAULT_SIGLA)))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)));
    }
    
    @Test
    @Transactional
    public void getClasificacionOrganica() throws Exception {
        // Initialize the database
        clasificacionOrganicaRepository.saveAndFlush(clasificacionOrganica);

        // Get the clasificacionOrganica
        restClasificacionOrganicaMockMvc.perform(get("/api/clasificacion-organicas/{id}", clasificacionOrganica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clasificacionOrganica.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.sigla").value(DEFAULT_SIGLA))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO));
    }
    @Test
    @Transactional
    public void getNonExistingClasificacionOrganica() throws Exception {
        // Get the clasificacionOrganica
        restClasificacionOrganicaMockMvc.perform(get("/api/clasificacion-organicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
