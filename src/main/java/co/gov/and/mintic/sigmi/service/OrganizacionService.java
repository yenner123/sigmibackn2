package co.gov.and.mintic.sigmi.service;

import co.gov.and.mintic.sigmi.domain.Organizacion;
import co.gov.and.mintic.sigmi.repository.OrganizacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Organizacion}.
 */
@Service
@Transactional
public class OrganizacionService {

    private final Logger log = LoggerFactory.getLogger(OrganizacionService.class);

    private final OrganizacionRepository organizacionRepository;

    public OrganizacionService(OrganizacionRepository organizacionRepository) {
        this.organizacionRepository = organizacionRepository;
    }

    /**
     * Save a organizacion.
     *
     * @param organizacion the entity to save.
     * @return the persisted entity.
     */
    public Organizacion save(Organizacion organizacion) {
        log.debug("Request to save Organizacion : {}", organizacion);
        return organizacionRepository.save(organizacion);
    }

    /**
     * Get all the organizacions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Organizacion> findAll() {
        log.debug("Request to get all Organizacions");
        return organizacionRepository.findAll();
    }


    /**
     * Get one organizacion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Organizacion> findOne(Long id) {
        log.debug("Request to get Organizacion : {}", id);
        return organizacionRepository.findById(id);
    }

    /**
     * Delete the organizacion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Organizacion : {}", id);
        organizacionRepository.deleteById(id);
    }
}
