package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.Synthetic;
import com.softdreams.excel.helper.ExportExcelHelper;
import com.softdreams.excel.repository.SyntheticRepository;
import com.softdreams.excel.service.CreditService;
import java.io.ByteArrayInputStream;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService {

    private SyntheticRepository syntheticRepository;

    public CreditServiceImpl(SyntheticRepository syntheticRepository) {
        this.syntheticRepository = syntheticRepository;
    }

    @Override
    public ByteArrayInputStream exportCreditExcel() {
        List<Synthetic> synthetics = syntheticRepository.getAccreditativeOrderByVoucherNo9();
        ByteArrayInputStream inputStream = ExportExcelHelper.creditTransferToExcel(synthetics);
        return inputStream;
    }
}
