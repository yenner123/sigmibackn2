package co.gov.and.mintic.sigmi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.gov.and.mintic.sigmi.web.rest.TestUtil;

public class SubOrdenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubOrden.class);
        SubOrden subOrden1 = new SubOrden();
        subOrden1.setId(1L);
        SubOrden subOrden2 = new SubOrden();
        subOrden2.setId(subOrden1.getId());
        assertThat(subOrden1).isEqualTo(subOrden2);
        subOrden2.setId(2L);
        assertThat(subOrden1).isNotEqualTo(subOrden2);
        subOrden1.setId(null);
        assertThat(subOrden1).isNotEqualTo(subOrden2);
    }
}
