package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.Synthetic;
import com.softdreams.excel.helper.ExcelHelper;
import com.softdreams.excel.repository.SyntheticRepository;
import com.softdreams.excel.service.SyntheticService;
import com.softdreams.excel.service.dto.SyntheticDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation for managing {@link Synthetic}.
 */
@Service
@Transactional
public class SyntheticServiceImpl implements SyntheticService {

    private final Logger log = LoggerFactory.getLogger(SyntheticServiceImpl.class);

    private final SyntheticRepository syntheticRepository;

    public SyntheticServiceImpl(SyntheticRepository syntheticRepository) {
        this.syntheticRepository = syntheticRepository;
    }

    @Override
    public Synthetic save(Synthetic synthetic) {
        log.debug("Request to save Synthetic : {}", synthetic);
        return syntheticRepository.save(synthetic);
    }

    @Override
    public Synthetic update(Synthetic synthetic) {
        log.debug("Request to update Synthetic : {}", synthetic);
        return syntheticRepository.save(synthetic);
    }

    @Override
    public Optional<Synthetic> partialUpdate(Synthetic synthetic) {
        log.debug("Request to partially update Synthetic : {}", synthetic);

        return syntheticRepository
            .findById(synthetic.getId())
            .map(existingSynthetic -> {
                if (synthetic.getVoucherType() != null) {
                    existingSynthetic.setVoucherType(synthetic.getVoucherType());
                }
                if (synthetic.getVoucherTypeNo() != null) {
                    existingSynthetic.setVoucherTypeNo(synthetic.getVoucherTypeNo());
                }
                if (synthetic.getVoucherNo() != null) {
                    existingSynthetic.setVoucherNo(synthetic.getVoucherNo());
                }
                if (synthetic.getVoucherDate() != null) {
                    existingSynthetic.setVoucherDate(synthetic.getVoucherDate());
                }
                if (synthetic.getAccountingDate() != null) {
                    existingSynthetic.setAccountingDate(synthetic.getAccountingDate());
                }
                if (synthetic.getInvoiceNo() != null) {
                    existingSynthetic.setInvoiceNo(synthetic.getInvoiceNo());
                }
                if (synthetic.getInvoiceDate() != null) {
                    existingSynthetic.setInvoiceDate(synthetic.getInvoiceDate());
                }
                if (synthetic.getDebitAccount() != null) {
                    existingSynthetic.setDebitAccount(synthetic.getDebitAccount());
                }
                if (synthetic.getCreditAccount() != null) {
                    existingSynthetic.setCreditAccount(synthetic.getCreditAccount());
                }
                if (synthetic.getCurrencyType() != null) {
                    existingSynthetic.setCurrencyType(synthetic.getCurrencyType());
                }
                if (synthetic.getCurrency() != null) {
                    existingSynthetic.setCurrency(synthetic.getCurrency());
                }
                if (synthetic.getMaterialGoodCode() != null) {
                    existingSynthetic.setMaterialGoodCode(synthetic.getMaterialGoodCode());
                }
                if (synthetic.getMaterialGoodName() != null) {
                    existingSynthetic.setMaterialGoodName(synthetic.getMaterialGoodName());
                }
                if (synthetic.getStorageIn() != null) {
                    existingSynthetic.setStorageIn(synthetic.getStorageIn());
                }
                if (synthetic.getStorageOut() != null) {
                    existingSynthetic.setStorageOut(synthetic.getStorageOut());
                }
                if (synthetic.getCaculationUnit() != null) {
                    existingSynthetic.setCaculationUnit(synthetic.getCaculationUnit());
                }
                if (synthetic.getAmount() != null) {
                    existingSynthetic.setAmount(synthetic.getAmount());
                }
                if (synthetic.getPrice() != null) {
                    existingSynthetic.setPrice(synthetic.getPrice());
                }
                if (synthetic.getTranferRate() != null) {
                    existingSynthetic.setTranferRate(synthetic.getTranferRate());
                }
                if (synthetic.getMoneyTranfer() != null) {
                    existingSynthetic.setMoneyTranfer(synthetic.getMoneyTranfer());
                }
                if (synthetic.getFixedAssetsType() != null) {
                    existingSynthetic.setFixedAssetsType(synthetic.getFixedAssetsType());
                }
                if (synthetic.getFixedAssetsCode() != null) {
                    existingSynthetic.setFixedAssetsCode(synthetic.getFixedAssetsCode());
                }
                if (synthetic.getToolsCode() != null) {
                    existingSynthetic.setToolsCode(synthetic.getToolsCode());
                }
                if (synthetic.getDebitObject() != null) {
                    existingSynthetic.setDebitObject(synthetic.getDebitObject());
                }
                if (synthetic.getCreditObject() != null) {
                    existingSynthetic.setCreditObject(synthetic.getCreditObject());
                }
                if (synthetic.getUnit() != null) {
                    existingSynthetic.setUnit(synthetic.getUnit());
                }
                if (synthetic.getEmployee() != null) {
                    existingSynthetic.setEmployee(synthetic.getEmployee());
                }
                if (synthetic.getBankAccount() != null) {
                    existingSynthetic.setBankAccount(synthetic.getBankAccount());
                }
                if (synthetic.getItemCost() != null) {
                    existingSynthetic.setItemCost(synthetic.getItemCost());
                }
                if (synthetic.getConstruction() != null) {
                    existingSynthetic.setConstruction(synthetic.getConstruction());
                }
                if (synthetic.getCostSet() != null) {
                    existingSynthetic.setCostSet(synthetic.getCostSet());
                }
                if (synthetic.getPurchaseOrder() != null) {
                    existingSynthetic.setPurchaseOrder(synthetic.getPurchaseOrder());
                }
                if (synthetic.getBuyOrder() != null) {
                    existingSynthetic.setBuyOrder(synthetic.getBuyOrder());
                }
                if (synthetic.getPurchaseContract() != null) {
                    existingSynthetic.setPurchaseContract(synthetic.getPurchaseContract());
                }
                if (synthetic.getSaleContract() != null) {
                    existingSynthetic.setSaleContract(synthetic.getSaleContract());
                }
                if (synthetic.getStatsCode() != null) {
                    existingSynthetic.setStatsCode(synthetic.getStatsCode());
                }
                if (synthetic.getExplanation() != null) {
                    existingSynthetic.setExplanation(synthetic.getExplanation());
                }
                if (synthetic.getExplanationDetail() != null) {
                    existingSynthetic.setExplanationDetail(synthetic.getExplanationDetail());
                }
                if (synthetic.getRecordStatus() != null) {
                    existingSynthetic.setRecordStatus(synthetic.getRecordStatus());
                }
                if (synthetic.getCreatedDate() != null) {
                    existingSynthetic.setCreatedDate(synthetic.getCreatedDate());
                }
                if (synthetic.getKeyUUID() != null) {
                    existingSynthetic.setKeyUUID(synthetic.getKeyUUID());
                }

                return existingSynthetic;
            })
            .map(syntheticRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Synthetic> findAll() {
        log.debug("Request to get all Synthetics");
        return syntheticRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Synthetic> findOne(Long id) {
        log.debug("Request to get Synthetic : {}", id);
        return syntheticRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Synthetic : {}", id);
        syntheticRepository.deleteById(id);
    }

    @Override
    public void saveToSynthetic(MultipartFile file) {
        try {
            List<Synthetic> synthetics = ExcelHelper.excelToSynthetic(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Synthetic synthetic : synthetics) {
                synthetic.setCreatedDate(date);
                synthetic.setKeyUUID(key);
            }
            syntheticRepository.insertBulk(synthetics);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    @Override
    public ByteArrayInputStream exportDebitNote(int voucherTypeNo,String keyUUID) {
        List<SyntheticDTO> synthetics = syntheticRepository.getSynthetic(voucherTypeNo,keyUUID);
        ByteArrayInputStream inputStream = ExcelHelper.debitNoteToExcel(synthetics);
        return inputStream;
    }
}
