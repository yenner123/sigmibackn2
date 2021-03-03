package co.gov.and.mintic.sigmi.repository;

import co.gov.and.mintic.sigmi.domain.Orden;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Orden entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
