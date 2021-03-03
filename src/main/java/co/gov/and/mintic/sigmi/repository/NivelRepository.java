package co.gov.and.mintic.sigmi.repository;

import co.gov.and.mintic.sigmi.domain.Nivel;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Nivel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NivelRepository extends JpaRepository<Nivel, Long> {
}
