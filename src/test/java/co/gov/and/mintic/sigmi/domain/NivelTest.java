package co.gov.and.mintic.sigmi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.gov.and.mintic.sigmi.web.rest.TestUtil;

public class NivelTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nivel.class);
        Nivel nivel1 = new Nivel();
        nivel1.setId(1L);
        Nivel nivel2 = new Nivel();
        nivel2.setId(nivel1.getId());
        assertThat(nivel1).isEqualTo(nivel2);
        nivel2.setId(2L);
        assertThat(nivel1).isNotEqualTo(nivel2);
        nivel1.setId(null);
        assertThat(nivel1).isNotEqualTo(nivel2);
    }
}
