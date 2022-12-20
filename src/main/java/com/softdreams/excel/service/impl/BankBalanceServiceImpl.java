package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.BankBalance;
import com.softdreams.excel.helper.BankBalanceExcelHelper;
import com.softdreams.excel.repository.BankBalanceRepository;
import com.softdreams.excel.service.BankBalanceService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Implementation for managing {@link BankBalance}.
 */
@Service
@Transactional
public class BankBalanceServiceImpl implements BankBalanceService {

    private final Logger log = LoggerFactory.getLogger(BankBalanceServiceImpl.class);

    private final BankBalanceRepository bankBalanceRepository;

    public BankBalanceServiceImpl(BankBalanceRepository bankBalanceRepository) {
        this.bankBalanceRepository = bankBalanceRepository;
    }

    @Override
    public BankBalance save(BankBalance bankBalance) {
        log.debug("Request to save BankBalance : {}", bankBalance);
        return bankBalanceRepository.save(bankBalance);
    }

    @Override
    public BankBalance update(BankBalance bankBalance) {
        log.debug("Request to update BankBalance : {}", bankBalance);
        return bankBalanceRepository.save(bankBalance);
    }

    @Override
    public Optional<BankBalance> partialUpdate(BankBalance bankBalance) {
        log.debug("Request to partially update BankBalance : {}", bankBalance);

        return bankBalanceRepository
            .findById(bankBalance.getId())
            .map(existingBankBalance -> {
                if (bankBalance.getBank_account() != null) {
                    existingBankBalance.setBank_account(bankBalance.getBank_account());
                }
                if (bankBalance.getBank_name() != null) {
                    existingBankBalance.setBank_name(bankBalance.getBank_name());
                }
                if (bankBalance.getBranch() != null) {
                    existingBankBalance.setBranch(bankBalance.getBranch());
                }
                if (bankBalance.getOpening_balance() != null) {
                    existingBankBalance.setOpening_balance(bankBalance.getOpening_balance());
                }
                if (bankBalance.getDebt_incurred() != null) {
                    existingBankBalance.setDebt_incurred(bankBalance.getDebt_incurred());
                }
                if (bankBalance.getIncurred() != null) {
                    existingBankBalance.setIncurred(bankBalance.getIncurred());
                }
                if (bankBalance.getEnding_balance() != null) {
                    existingBankBalance.setEnding_balance(bankBalance.getEnding_balance());
                }
                if (bankBalance.getCreated_date() != null) {
                    existingBankBalance.setCreated_date(bankBalance.getCreated_date());
                }
                if (bankBalance.getKeyUUID() != null) {
                    existingBankBalance.setKeyUUID(bankBalance.getKeyUUID());
                }

                return existingBankBalance;
            })
            .map(bankBalanceRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankBalance> findAll() {
        log.debug("Request to get all BankBalances");
        return bankBalanceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BankBalance> findOne(Long id) {
        log.debug("Request to get BankBalance : {}", id);
        return bankBalanceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BankBalance : {}", id);
        bankBalanceRepository.deleteById(id);
    }

    @Override
    public void saveToBankBlance(MultipartFile file) {
        try {
            List<BankBalance> bankBalances = BankBalanceExcelHelper.excelToBankBalance(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (BankBalance bankBalance : bankBalances) {
                bankBalance.setCreated_date(date);
                bankBalance.setKeyUUID(key);
            }
            bankBalanceRepository.saveAll(bankBalances);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

}
