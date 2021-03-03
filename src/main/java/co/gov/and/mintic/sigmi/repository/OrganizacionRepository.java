package co.gov.and.mintic.sigmi.repository;

import co.gov.and.mintic.sigmi.domain.Organizacion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Organizacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganizacionRepository extends JpaRepository<Organizacion, Long> {
}
