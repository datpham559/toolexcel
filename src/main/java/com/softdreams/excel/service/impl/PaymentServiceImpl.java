package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.Synthetic;
import com.softdreams.excel.helper.PaymentExcelHelper;
import com.softdreams.excel.repository.SyntheticRepository;
import com.softdreams.excel.service.PaymentService;
import com.softdreams.excel.service.dto.SyntheticDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final SyntheticRepository syntheticRepository;

    public PaymentServiceImpl(SyntheticRepository syntheticRepository) {
        this.syntheticRepository = syntheticRepository;
    }

    @Override
    public ByteArrayInputStream exportExcel(int voucherTypeNo,String keyUUID) {
        List<SyntheticDTO> synthetics = syntheticRepository.getSynthetic(voucherTypeNo,keyUUID);
        ByteArrayInputStream inputStream = PaymentExcelHelper.paymentToExcel(synthetics);
        return inputStream;
    }
}
