package co.gov.and.mintic.sigmi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.gov.and.mintic.sigmi.web.rest.TestUtil;

public class SectorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sector.class);
        Sector sector1 = new Sector();
        sector1.setId(1L);
        Sector sector2 = new Sector();
        sector2.setId(sector1.getId());
        assertThat(sector1).isEqualTo(sector2);
        sector2.setId(2L);
        assertThat(sector1).isNotEqualTo(sector2);
        sector1.setId(null);
        assertThat(sector1).isNotEqualTo(sector2);
    }
}
