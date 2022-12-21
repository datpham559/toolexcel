package com.softdreams.excel.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.softdreams.excel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BeginningInventoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BeginningInventory.class);
        BeginningInventory beginningInventory1 = new BeginningInventory();
        beginningInventory1.setId(1L);
        BeginningInventory beginningInventory2 = new BeginningInventory();
        beginningInventory2.setId(beginningInventory1.getId());
        assertThat(beginningInventory1).isEqualTo(beginningInventory2);
        beginningInventory2.setId(2L);
        assertThat(beginningInventory1).isNotEqualTo(beginningInventory2);
        beginningInventory1.setId(null);
        assertThat(beginningInventory1).isNotEqualTo(beginningInventory2);
    }
}
