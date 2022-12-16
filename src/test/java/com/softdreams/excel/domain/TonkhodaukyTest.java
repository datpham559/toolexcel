package com.softdreams.excel.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.softdreams.excel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TonkhodaukyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tonkhodauky.class);
        Tonkhodauky tonkhodauky1 = new Tonkhodauky();
        tonkhodauky1.setId(1L);
        Tonkhodauky tonkhodauky2 = new Tonkhodauky();
        tonkhodauky2.setId(tonkhodauky1.getId());
        assertThat(tonkhodauky1).isEqualTo(tonkhodauky2);
        tonkhodauky2.setId(2L);
        assertThat(tonkhodauky1).isNotEqualTo(tonkhodauky2);
        tonkhodauky1.setId(null);
        assertThat(tonkhodauky1).isNotEqualTo(tonkhodauky2);
    }
}
