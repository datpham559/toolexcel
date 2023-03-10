package com.softdreams.excel.repository.custom;

import com.softdreams.excel.domain.Synthetic;
import com.softdreams.excel.service.dto.SyntheticDTO;
import java.util.List;

public interface SyntheticRepositoryCustom {
    List<SyntheticDTO> getSynthetic(int VoucherTypeNo, String keyUUID);
    List<SyntheticDTO> getSaInvoice(int VoucherTypeNo, String keyUUID);

    void insertBulk(List<Synthetic> synthetics);
}
