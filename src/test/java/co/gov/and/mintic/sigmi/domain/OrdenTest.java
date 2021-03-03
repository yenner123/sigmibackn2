package co.gov.and.mintic.sigmi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.gov.and.mintic.sigmi.web.rest.TestUtil;

public class OrdenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Orden.class);
        Orden orden1 = new Orden();
        orden1.setId(1L);
        Orden orden2 = new Orden();
        orden2.setId(orden1.getId());
        assertThat(orden1).isEqualTo(orden2);
        orden2.setId(2L);
        assertThat(orden1).isNotEqualTo(orden2);
        orden1.setId(null);
        assertThat(orden1).isNotEqualTo(orden2);
    }
}
