package co.gov.and.mintic.sigmi.repository;

import co.gov.and.mintic.sigmi.domain.NaturalezaJuridica;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NaturalezaJuridica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NaturalezaJuridicaRepository extends JpaRepository<NaturalezaJuridica, Long> {
}
