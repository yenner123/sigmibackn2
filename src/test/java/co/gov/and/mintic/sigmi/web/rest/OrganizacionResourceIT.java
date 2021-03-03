package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.Sigmibackn2App;
import co.gov.and.mintic.sigmi.domain.Organizacion;
import co.gov.and.mintic.sigmi.domain.Municipio;
import co.gov.and.mintic.sigmi.domain.Nivel;
import co.gov.and.mintic.sigmi.repository.OrganizacionRepository;
import co.gov.and.mintic.sigmi.service.OrganizacionService;

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
 * Integration tests for the {@link OrganizacionResource} REST controller.
 */
@SpringBootTest(classes = Sigmibackn2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrganizacionResourceIT {

    private static final String DEFAULT_NIT = "AAAAAAAAAA";
    private static final String UPDATED_NIT = "BBBBBBBBBB";

    private static final String DEFAULT_RAZON_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAZON_SOCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_SIGLA = "AAAAAAAAAA";
    private static final String UPDATED_SIGLA = "BBBBBBBBBB";

    private static final String DEFAULT_PAGINA_WEB = "AAAAAAAAAA";
    private static final String UPDATED_PAGINA_WEB = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final Instant DEFAULT_FECHA_CREACION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_CREACION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FECHA_ACTUALIZACION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_ACTUALIZACION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_ESTADO = 1;
    private static final Integer UPDATED_ESTADO = 2;

    @Autowired
    private OrganizacionRepository organizacionRepository;

    @Autowired
    private OrganizacionService organizacionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrganizacionMockMvc;

    private Organizacion organizacion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organizacion createEntity(EntityManager em) {
        Organizacion organizacion = new Organizacion()
            .nit(DEFAULT_NIT)
            .razonSocial(DEFAULT_RAZON_SOCIAL)
            .sigla(DEFAULT_SIGLA)
            .paginaWeb(DEFAULT_PAGINA_WEB)
            .email(DEFAULT_EMAIL)
            .direccion(DEFAULT_DIRECCION)
            .telefono(DEFAULT_TELEFONO)
            .fax(DEFAULT_FAX)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .fechaActualizacion(DEFAULT_FECHA_ACTUALIZACION)
            .estado(DEFAULT_ESTADO);
        // Add required entity
        Municipio municipio;
        if (TestUtil.findAll(em, Municipio.class).isEmpty()) {
            municipio = MunicipioResourceIT.createEntity(em);
            em.persist(municipio);
            em.flush();
        } else {
            municipio = TestUtil.findAll(em, Municipio.class).get(0);
        }
        organizacion.setMunicipio(municipio);
        // Add required entity
        Nivel nivel;
        if (TestUtil.findAll(em, Nivel.class).isEmpty()) {
            nivel = NivelResourceIT.createEntity(em);
            em.persist(nivel);
            em.flush();
        } else {
            nivel = TestUtil.findAll(em, Nivel.class).get(0);
        }
        organizacion.setNivel(nivel);
        return organizacion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organizacion createUpdatedEntity(EntityManager em) {
        Organizacion organizacion = new Organizacion()
            .nit(UPDATED_NIT)
            .razonSocial(UPDATED_RAZON_SOCIAL)
            .sigla(UPDATED_SIGLA)
            .paginaWeb(UPDATED_PAGINA_WEB)
            .email(UPDATED_EMAIL)
            .direccion(UPDATED_DIRECCION)
            .telefono(UPDATED_TELEFONO)
            .fax(UPDATED_FAX)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .estado(UPDATED_ESTADO);
        // Add required entity
        Municipio municipio;
        if (TestUtil.findAll(em, Municipio.class).isEmpty()) {
            municipio = MunicipioResourceIT.createUpdatedEntity(em);
            em.persist(municipio);
            em.flush();
        } else {
            municipio = TestUtil.findAll(em, Municipio.class).get(0);
        }
        organizacion.setMunicipio(municipio);
        // Add required entity
        Nivel nivel;
        if (TestUtil.findAll(em, Nivel.class).isEmpty()) {
            nivel = NivelResourceIT.createUpdatedEntity(em);
            em.persist(nivel);
            em.flush();
        } else {
            nivel = TestUtil.findAll(em, Nivel.class).get(0);
        }
        organizacion.setNivel(nivel);
        return organizacion;
    }

    @BeforeEach
    public void initTest() {
        organizacion = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllOrganizacions() throws Exception {
        // Initialize the database
        organizacionRepository.saveAndFlush(organizacion);

        // Get all the organizacionList
        restOrganizacionMockMvc.perform(get("/api/organizacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organizacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nit").value(hasItem(DEFAULT_NIT)))
            .andExpect(jsonPath("$.[*].razonSocial").value(hasItem(DEFAULT_RAZON_SOCIAL)))
            .andExpect(jsonPath("$.[*].sigla").value(hasItem(DEFAULT_SIGLA)))
            .andExpect(jsonPath("$.[*].paginaWeb").value(hasItem(DEFAULT_PAGINA_WEB)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].fechaActualizacion").value(hasItem(DEFAULT_FECHA_ACTUALIZACION.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)));
    }
    
    @Test
    @Transactional
    public void getOrganizacion() throws Exception {
        // Initialize the database
        organizacionRepository.saveAndFlush(organizacion);

        // Get the organizacion
        restOrganizacionMockMvc.perform(get("/api/organizacions/{id}", organizacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(organizacion.getId().intValue()))
            .andExpect(jsonPath("$.nit").value(DEFAULT_NIT))
            .andExpect(jsonPath("$.razonSocial").value(DEFAULT_RAZON_SOCIAL))
            .andExpect(jsonPath("$.sigla").value(DEFAULT_SIGLA))
            .andExpect(jsonPath("$.paginaWeb").value(DEFAULT_PAGINA_WEB))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.fechaActualizacion").value(DEFAULT_FECHA_ACTUALIZACION.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO));
    }
    @Test
    @Transactional
    public void getNonExistingOrganizacion() throws Exception {
        // Get the organizacion
        restOrganizacionMockMvc.perform(get("/api/organizacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
