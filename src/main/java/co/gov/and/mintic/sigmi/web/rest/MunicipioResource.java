package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.domain.Municipio;
import co.gov.and.mintic.sigmi.repository.MunicipioRepository;
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
 * REST controller for managing {@link co.gov.and.mintic.sigmi.domain.Municipio}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MunicipioResource {

    private final Logger log = LoggerFactory.getLogger(MunicipioResource.class);

    private final MunicipioRepository municipioRepository;

    public MunicipioResource(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    /**
     * {@code GET  /municipios} : get all the municipios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of municipios in body.
     */
    @GetMapping("/municipios")
    public List<Municipio> getAllMunicipios() {
        log.debug("REST request to get all Municipios");
        return municipioRepository.findAll();
    }

    /**
     * {@code GET  /municipios/:id} : get the "id" municipio.
     *
     * @param id the id of the municipio to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the municipio, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/municipios/{id}")
    public ResponseEntity<Municipio> getMunicipio(@PathVariable Long id) {
        log.debug("REST request to get Municipio : {}", id);
        Optional<Municipio> municipio = municipioRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(municipio);
    }
}
