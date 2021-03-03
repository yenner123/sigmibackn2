package co.gov.and.mintic.sigmi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.gov.and.mintic.sigmi.web.rest.TestUtil;

public class NaturalezaJuridicaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NaturalezaJuridica.class);
        NaturalezaJuridica naturalezaJuridica1 = new NaturalezaJuridica();
        naturalezaJuridica1.setId(1L);
        NaturalezaJuridica naturalezaJuridica2 = new NaturalezaJuridica();
        naturalezaJuridica2.setId(naturalezaJuridica1.getId());
        assertThat(naturalezaJuridica1).isEqualTo(naturalezaJuridica2);
        naturalezaJuridica2.setId(2L);
        assertThat(naturalezaJuridica1).isNotEqualTo(naturalezaJuridica2);
        naturalezaJuridica1.setId(null);
        assertThat(naturalezaJuridica1).isNotEqualTo(naturalezaJuridica2);
    }
}
