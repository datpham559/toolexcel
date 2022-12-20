package com.softdreams.excel.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.softdreams.excel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SyntheticTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Synthetic.class);
        Synthetic synthetic1 = new Synthetic();
        synthetic1.setId(1L);
        Synthetic synthetic2 = new Synthetic();
        synthetic2.setId(synthetic1.getId());
        assertThat(synthetic1).isEqualTo(synthetic2);
        synthetic2.setId(2L);
        assertThat(synthetic1).isNotEqualTo(synthetic2);
        synthetic1.setId(null);
        assertThat(synthetic1).isNotEqualTo(synthetic2);
    }
}
