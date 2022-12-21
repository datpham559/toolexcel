package com.softdreams.excel.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.softdreams.excel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CongcudungcuTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Congcudungcu.class);
        Congcudungcu congcudungcu1 = new Congcudungcu();
        congcudungcu1.setId(1L);
        Congcudungcu congcudungcu2 = new Congcudungcu();
        congcudungcu2.setId(congcudungcu1.getId());
        assertThat(congcudungcu1).isEqualTo(congcudungcu2);
        congcudungcu2.setId(2L);
        assertThat(congcudungcu1).isNotEqualTo(congcudungcu2);
        congcudungcu1.setId(null);
        assertThat(congcudungcu1).isNotEqualTo(congcudungcu2);
    }
}
