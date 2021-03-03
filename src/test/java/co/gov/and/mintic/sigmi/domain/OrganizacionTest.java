package co.gov.and.mintic.sigmi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.gov.and.mintic.sigmi.web.rest.TestUtil;

public class OrganizacionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Organizacion.class);
        Organizacion organizacion1 = new Organizacion();
        organizacion1.setId(1L);
        Organizacion organizacion2 = new Organizacion();
        organizacion2.setId(organizacion1.getId());
        assertThat(organizacion1).isEqualTo(organizacion2);
        organizacion2.setId(2L);
        assertThat(organizacion1).isNotEqualTo(organizacion2);
        organizacion1.setId(null);
        assertThat(organizacion1).isNotEqualTo(organizacion2);
    }
}
