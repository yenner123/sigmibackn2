package co.gov.and.mintic.sigmi.repository;

import co.gov.and.mintic.sigmi.domain.ClasificacionOrganica;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ClasificacionOrganica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClasificacionOrganicaRepository extends JpaRepository<ClasificacionOrganica, Long> {
}
