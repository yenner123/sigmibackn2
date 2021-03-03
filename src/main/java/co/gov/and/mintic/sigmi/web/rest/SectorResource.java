package co.gov.and.mintic.sigmi.web.rest;

import co.gov.and.mintic.sigmi.domain.Sector;
import co.gov.and.mintic.sigmi.repository.SectorRepository;
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
 * REST controller for managing {@link co.gov.and.mintic.sigmi.domain.Sector}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SectorResource {

    private final Logger log = LoggerFactory.getLogger(SectorResource.class);

    private final SectorRepository sectorRepository;

    public SectorResource(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    /**
     * {@code GET  /sectors} : get all the sectors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sectors in body.
     */
    @GetMapping("/sectors")
    public List<Sector> getAllSectors() {
        log.debug("REST request to get all Sectors");
        return sectorRepository.findAll();
    }

    /**
     * {@code GET  /sectors/:id} : get the "id" sector.
     *
     * @param id the id of the sector to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sector, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sectors/{id}")
    public ResponseEntity<Sector> getSector(@PathVariable Long id) {
        log.debug("REST request to get Sector : {}", id);
        Optional<Sector> sector = sectorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sector);
    }
}
