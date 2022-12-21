package com.softdreams.excel.service.impl;

import com.softdreams.excel.helper.ExcelHelper;
import com.softdreams.excel.helper.SaInvoiceExcelHelper;
import com.softdreams.excel.repository.SyntheticRepository;
import com.softdreams.excel.service.SaInvoiceService;
import com.softdreams.excel.service.dto.SyntheticDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@Transactional
public class SaInvoiceServiceImpl implements SaInvoiceService {
    @Autowired
    private SyntheticRepository syntheticRepository;
    @Override
    public ByteArrayInputStream exportSaInvoice(int voucherTypeNo, String keyUUID,String serialInvoice) {
        List<SyntheticDTO> synthetics = syntheticRepository.getSaInvoice(voucherTypeNo,keyUUID);
        ByteArrayInputStream inputStream = SaInvoiceExcelHelper.saInvoiceToExcel(synthetics,serialInvoice);
        return inputStream;
    }
}
