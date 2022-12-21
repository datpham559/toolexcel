package com.softdreams.excel.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.softdreams.excel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CongnophaithuTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Congnophaithu.class);
        Congnophaithu congnophaithu1 = new Congnophaithu();
        congnophaithu1.setId(1L);
        Congnophaithu congnophaithu2 = new Congnophaithu();
        congnophaithu2.setId(congnophaithu1.getId());
        assertThat(congnophaithu1).isEqualTo(congnophaithu2);
        congnophaithu2.setId(2L);
        assertThat(congnophaithu1).isNotEqualTo(congnophaithu2);
        congnophaithu1.setId(null);
        assertThat(congnophaithu1).isNotEqualTo(congnophaithu2);
    }
}
