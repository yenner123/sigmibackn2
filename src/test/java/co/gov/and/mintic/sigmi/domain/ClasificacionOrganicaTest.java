package co.gov.and.mintic.sigmi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.gov.and.mintic.sigmi.web.rest.TestUtil;

public class ClasificacionOrganicaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClasificacionOrganica.class);
        ClasificacionOrganica clasificacionOrganica1 = new ClasificacionOrganica();
        clasificacionOrganica1.setId(1L);
        ClasificacionOrganica clasificacionOrganica2 = new ClasificacionOrganica();
        clasificacionOrganica2.setId(clasificacionOrganica1.getId());
        assertThat(clasificacionOrganica1).isEqualTo(clasificacionOrganica2);
        clasificacionOrganica2.setId(2L);
        assertThat(clasificacionOrganica1).isNotEqualTo(clasificacionOrganica2);
        clasificacionOrganica1.setId(null);
        assertThat(clasificacionOrganica1).isNotEqualTo(clasificacionOrganica2);
    }
}
