package co.gov.and.mintic.sigmi.repository;

import co.gov.and.mintic.sigmi.domain.SubOrden;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SubOrden entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubOrdenRepository extends JpaRepository<SubOrden, Long> {
}
