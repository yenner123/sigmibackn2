package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.domain.Pais;
import co.gov.and.mintic.sigmi.repository.PaisRepository;
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
 * REST controller for managing {@link co.gov.and.mintic.sigmi.domain.Pais}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PaisResource {

    private final Logger log = LoggerFactory.getLogger(PaisResource.class);

    private final PaisRepository paisRepository;

    public PaisResource(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    /**
     * {@code GET  /pais} : get all the pais.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pais in body.
     */
    @GetMapping("/pais")
    public List<Pais> getAllPais() {
        log.debug("REST request to get all Pais");
        return paisRepository.findAll();
    }

    /**
     * {@code GET  /pais/:id} : get the "id" pais.
     *
     * @param id the id of the pais to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pais, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pais/{id}")
    public ResponseEntity<Pais> getPais(@PathVariable Long id) {
        log.debug("REST request to get Pais : {}", id);
        Optional<Pais> pais = paisRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pais);
    }
}
