package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.Synthetic;
import com.softdreams.excel.helper.PaymentExcelHelper;
import com.softdreams.excel.helper.ReceiptsExcelHelper;
import com.softdreams.excel.repository.SyntheticRepository;
import com.softdreams.excel.service.ReceiptsService;
import com.softdreams.excel.service.dto.SyntheticDTO;
import java.io.ByteArrayInputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReceiptsServiceImpl implements ReceiptsService {

    private final Logger log = LoggerFactory.getLogger(ReceiptsServiceImpl.class);
    private final SyntheticRepository syntheticRepository;

    public ReceiptsServiceImpl(SyntheticRepository syntheticRepository) {
        this.syntheticRepository = syntheticRepository;
    }

    @Override
    public ByteArrayInputStream exportExcel() {
        List<Synthetic> synthetics = syntheticRepository.getAccreditativeOrderByVoucherNoOfReceipts();
        ByteArrayInputStream inputStream = ReceiptsExcelHelper.receiptsToExcel(synthetics);
        return inputStream;
    }
}
