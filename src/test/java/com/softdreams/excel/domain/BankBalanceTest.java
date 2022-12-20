package com.softdreams.excel.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.softdreams.excel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BankBalanceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankBalance.class);
        BankBalance bankBalance1 = new BankBalance();
        bankBalance1.setId(1L);
        BankBalance bankBalance2 = new BankBalance();
        bankBalance2.setId(bankBalance1.getId());
        assertThat(bankBalance1).isEqualTo(bankBalance2);
        bankBalance2.setId(2L);
        assertThat(bankBalance1).isNotEqualTo(bankBalance2);
        bankBalance1.setId(null);
        assertThat(bankBalance1).isNotEqualTo(bankBalance2);
    }
}
