package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.domain.NaturalezaJuridica;
import co.gov.and.mintic.sigmi.repository.NaturalezaJuridicaRepository;
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
 * REST controller for managing {@link co.gov.and.mintic.sigmi.domain.NaturalezaJuridica}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class NaturalezaJuridicaResource {

    private final Logger log = LoggerFactory.getLogger(NaturalezaJuridicaResource.class);

    private final NaturalezaJuridicaRepository naturalezaJuridicaRepository;

    public NaturalezaJuridicaResource(NaturalezaJuridicaRepository naturalezaJuridicaRepository) {
        this.naturalezaJuridicaRepository = naturalezaJuridicaRepository;
    }

    /**
     * {@code GET  /naturaleza-juridicas} : get all the naturalezaJuridicas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of naturalezaJuridicas in body.
     */
    @GetMapping("/naturaleza-juridicas")
    public List<NaturalezaJuridica> getAllNaturalezaJuridicas() {
        log.debug("REST request to get all NaturalezaJuridicas");
        return naturalezaJuridicaRepository.findAll();
    }

    /**
     * {@code GET  /naturaleza-juridicas/:id} : get the "id" naturalezaJuridica.
     *
     * @param id the id of the naturalezaJuridica to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the naturalezaJuridica, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/naturaleza-juridicas/{id}")
    public ResponseEntity<NaturalezaJuridica> getNaturalezaJuridica(@PathVariable Long id) {
        log.debug("REST request to get NaturalezaJuridica : {}", id);
        Optional<NaturalezaJuridica> naturalezaJuridica = naturalezaJuridicaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(naturalezaJuridica);
    }
}
