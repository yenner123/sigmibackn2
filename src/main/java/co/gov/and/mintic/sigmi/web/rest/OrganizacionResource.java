package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.domain.Organizacion;
import co.gov.and.mintic.sigmi.service.OrganizacionService;
import co.gov.and.mintic.sigmi.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link co.gov.and.mintic.sigmi.domain.Organizacion}.
 */
@RestController
@RequestMapping("/api")
public class OrganizacionResource {

    private final Logger log = LoggerFactory.getLogger(OrganizacionResource.class);

    private final OrganizacionService organizacionService;

    public OrganizacionResource(OrganizacionService organizacionService) {
        this.organizacionService = organizacionService;
    }

    /**
     * {@code GET  /organizacions} : get all the organizacions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organizacions in body.
     */
    @GetMapping("/organizacions")
    public List<Organizacion> getAllOrganizacions() {
        log.debug("REST request to get all Organizacions");
        return organizacionService.findAll();
    }

    /**
     * {@code GET  /organizacions/:id} : get the "id" organizacion.
     *
     * @param id the id of the organizacion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the organizacion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/organizacions/{id}")
    public ResponseEntity<Organizacion> getOrganizacion(@PathVariable Long id) {
        log.debug("REST request to get Organizacion : {}", id);
        Optional<Organizacion> organizacion = organizacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organizacion);
    }
}
