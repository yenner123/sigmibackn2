package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.domain.Nivel;
import co.gov.and.mintic.sigmi.repository.NivelRepository;
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
 * REST controller for managing {@link co.gov.and.mintic.sigmi.domain.Nivel}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class NivelResource {

    private final Logger log = LoggerFactory.getLogger(NivelResource.class);

    private final NivelRepository nivelRepository;

    public NivelResource(NivelRepository nivelRepository) {
        this.nivelRepository = nivelRepository;
    }

    /**
     * {@code GET  /nivels} : get all the nivels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nivels in body.
     */
    @GetMapping("/nivels")
    public List<Nivel> getAllNivels() {
        log.debug("REST request to get all Nivels");
        return nivelRepository.findAll();
    }

    /**
     * {@code GET  /nivels/:id} : get the "id" nivel.
     *
     * @param id the id of the nivel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nivel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nivels/{id}")
    public ResponseEntity<Nivel> getNivel(@PathVariable Long id) {
        log.debug("REST request to get Nivel : {}", id);
        Optional<Nivel> nivel = nivelRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(nivel);
    }
}
