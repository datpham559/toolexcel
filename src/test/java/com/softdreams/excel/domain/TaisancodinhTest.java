package com.softdreams.excel.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.softdreams.excel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaisancodinhTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Taisancodinh.class);
        Taisancodinh taisancodinh1 = new Taisancodinh();
        taisancodinh1.setId(1L);
        Taisancodinh taisancodinh2 = new Taisancodinh();
        taisancodinh2.setId(taisancodinh1.getId());
        assertThat(taisancodinh1).isEqualTo(taisancodinh2);
        taisancodinh2.setId(2L);
        assertThat(taisancodinh1).isNotEqualTo(taisancodinh2);
        taisancodinh1.setId(null);
        assertThat(taisancodinh1).isNotEqualTo(taisancodinh2);
    }
}
