package com.softdreams.excel.service.impl;

import com.softdreams.excel.helper.ExportExcelHelper;
import com.softdreams.excel.repository.SyntheticRepository;
import com.softdreams.excel.service.ChungTuMuaHangService;
import com.softdreams.excel.service.dto.SyntheticDTO;
import java.io.ByteArrayInputStream;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ChungTuMuaHangServiceImpl implements ChungTuMuaHangService {

    public SyntheticRepository syntheticRepository;

    public ChungTuMuaHangServiceImpl(SyntheticRepository syntheticRepository) {
        this.syntheticRepository = syntheticRepository;
    }

    @Override
    public ByteArrayInputStream exportBuyServiceExcel(int voucherTypeNo, String keyUUID) {
        List<SyntheticDTO> synthetics = syntheticRepository.getSynthetic(voucherTypeNo, keyUUID);
        ByteArrayInputStream inputStream = ExportExcelHelper.chung_tu_mua_dich_vuToExcel(synthetics);
        return inputStream;
    }
}
