package com.softdreams.excel.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.softdreams.excel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MerchandiseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Merchandise.class);
        Merchandise merchandise1 = new Merchandise();
        merchandise1.setId(1L);
        Merchandise merchandise2 = new Merchandise();
        merchandise2.setId(merchandise1.getId());
        assertThat(merchandise1).isEqualTo(merchandise2);
        merchandise2.setId(2L);
        assertThat(merchandise1).isNotEqualTo(merchandise2);
        merchandise1.setId(null);
        assertThat(merchandise1).isNotEqualTo(merchandise2);
    }
}
