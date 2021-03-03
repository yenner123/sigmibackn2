package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.domain.SubOrden;
import co.gov.and.mintic.sigmi.repository.SubOrdenRepository;
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
 * REST controller for managing {@link co.gov.and.mintic.sigmi.domain.SubOrden}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SubOrdenResource {

    private final Logger log = LoggerFactory.getLogger(SubOrdenResource.class);

    private final SubOrdenRepository subOrdenRepository;

    public SubOrdenResource(SubOrdenRepository subOrdenRepository) {
        this.subOrdenRepository = subOrdenRepository;
    }

    /**
     * {@code GET  /sub-ordens} : get all the subOrdens.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subOrdens in body.
     */
    @GetMapping("/sub-ordens")
    public List<SubOrden> getAllSubOrdens() {
        log.debug("REST request to get all SubOrdens");
        return subOrdenRepository.findAll();
    }

    /**
     * {@code GET  /sub-ordens/:id} : get the "id" subOrden.
     *
     * @param id the id of the subOrden to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subOrden, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sub-ordens/{id}")
    public ResponseEntity<SubOrden> getSubOrden(@PathVariable Long id) {
        log.debug("REST request to get SubOrden : {}", id);
        Optional<SubOrden> subOrden = subOrdenRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(subOrden);
    }
}
