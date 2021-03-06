package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.domain.Orden;
import co.gov.and.mintic.sigmi.repository.OrdenRepository;
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
 * REST controller for managing {@link co.gov.and.mintic.sigmi.domain.Orden}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OrdenResource {

    private final Logger log = LoggerFactory.getLogger(OrdenResource.class);

    private final OrdenRepository ordenRepository;

    public OrdenResource(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    /**
     * {@code GET  /ordens} : get all the ordens.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ordens in body.
     */
    @GetMapping("/ordens")
    public List<Orden> getAllOrdens() {
        log.debug("REST request to get all Ordens");
        return ordenRepository.findAll();
    }

    /**
     * {@code GET  /ordens/:id} : get the "id" orden.
     *
     * @param id the id of the orden to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orden, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ordens/{id}")
    public ResponseEntity<Orden> getOrden(@PathVariable Long id) {
        log.debug("REST request to get Orden : {}", id);
        Optional<Orden> orden = ordenRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(orden);
    }
}
