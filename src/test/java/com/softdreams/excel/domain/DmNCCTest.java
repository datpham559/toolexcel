package com.softdreams.excel.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.softdreams.excel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DmNCCTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmNCC.class);
        DmNCC dmNCC1 = new DmNCC();
        dmNCC1.setId(1L);
        DmNCC dmNCC2 = new DmNCC();
        dmNCC2.setId(dmNCC1.getId());
        assertThat(dmNCC1).isEqualTo(dmNCC2);
        dmNCC2.setId(2L);
        assertThat(dmNCC1).isNotEqualTo(dmNCC2);
        dmNCC1.setId(null);
        assertThat(dmNCC1).isNotEqualTo(dmNCC2);
    }
}
