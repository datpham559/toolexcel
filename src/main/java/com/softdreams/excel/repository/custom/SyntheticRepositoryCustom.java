package com.softdreams.excel.repository.custom;

import com.softdreams.excel.service.dto.SyntheticDTO;
import java.util.List;

public interface SyntheticRepositoryCustom {
    List<SyntheticDTO> getSynthetic(int VoucherNoType, String keyUUID);
}
