package com.softdreams.excel.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.softdreams.excel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CongnophaitraTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Congnophaitra.class);
        Congnophaitra congnophaitra1 = new Congnophaitra();
        congnophaitra1.setId(1L);
        Congnophaitra congnophaitra2 = new Congnophaitra();
        congnophaitra2.setId(congnophaitra1.getId());
        assertThat(congnophaitra1).isEqualTo(congnophaitra2);
        congnophaitra2.setId(2L);
        assertThat(congnophaitra1).isNotEqualTo(congnophaitra2);
        congnophaitra1.setId(null);
        assertThat(congnophaitra1).isNotEqualTo(congnophaitra2);
    }
}
