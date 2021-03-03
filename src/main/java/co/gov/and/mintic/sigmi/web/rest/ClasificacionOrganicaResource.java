package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.domain.ClasificacionOrganica;
import co.gov.and.mintic.sigmi.repository.ClasificacionOrganicaRepository;
import co.gov.and.mintic.sigmi.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link co.gov.and.mintic.sigmi.domain.ClasificacionOrganica}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ClasificacionOrganicaResource {

    private final Logger log = LoggerFactory.getLogger(ClasificacionOrganicaResource.class);

    private final ClasificacionOrganicaRepository clasificacionOrganicaRepository;

    public ClasificacionOrganicaResource(ClasificacionOrganicaRepository clasificacionOrganicaRepository) {
        this.clasificacionOrganicaRepository = clasificacionOrganicaRepository;
    }

    /**
     * {@code GET  /clasificacion-organicas} : get all the clasificacionOrganicas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clasificacionOrganicas in body.
     */
    @GetMapping("/clasificacion-organicas")
    public List<ClasificacionOrganica> getAllClasificacionOrganicas() {
        log.debug("REST request to get all ClasificacionOrganicas");
        return clasificacionOrganicaRepository.findAll();
    }

    /**
     * {@code GET  /clasificacion-organicas/:id} : get the "id" clasificacionOrganica.
     *
     * @param id the id of the clasificacionOrganica to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clasificacionOrganica, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/clasificacion-organicas/{id}")
    public ResponseEntity<ClasificacionOrganica> getClasificacionOrganica(@PathVariable Long id) {
        log.debug("REST request to get ClasificacionOrganica : {}", id);
        Optional<ClasificacionOrganica> clasificacionOrganica = clasificacionOrganicaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(clasificacionOrganica);
    }
}
