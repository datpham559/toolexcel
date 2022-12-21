package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.BeginningInventory;
import com.softdreams.excel.helper.BeginningInventoryExcelHelper;
import com.softdreams.excel.repository.BeginningInventoryRepository;
import com.softdreams.excel.service.BeginningInventoryService;
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
 * Service Implementation for managing {@link BeginningInventory}.
 */
@Service
@Transactional
public class BeginningInventoryServiceImpl implements BeginningInventoryService {

    private final Logger log = LoggerFactory.getLogger(BeginningInventoryServiceImpl.class);

    private final BeginningInventoryRepository beginningInventoryRepository;

    public BeginningInventoryServiceImpl(BeginningInventoryRepository beginningInventoryRepository) {
        this.beginningInventoryRepository = beginningInventoryRepository;
    }

    @Override
    public BeginningInventory save(BeginningInventory beginningInventory) {
        log.debug("Request to save BeginningInventory : {}", beginningInventory);
        return beginningInventoryRepository.save(beginningInventory);
    }

    @Override
    public BeginningInventory update(BeginningInventory beginningInventory) {
        log.debug("Request to update BeginningInventory : {}", beginningInventory);
        return beginningInventoryRepository.save(beginningInventory);
    }

    @Override
    public Optional<BeginningInventory> partialUpdate(BeginningInventory beginningInventory) {
        log.debug("Request to partially update BeginningInventory : {}", beginningInventory);

        return beginningInventoryRepository
            .findById(beginningInventory.getId())
            .map(existingBeginningInventory -> {
                if (beginningInventory.getAccount_number() != null) {
                    existingBeginningInventory.setAccount_number(beginningInventory.getAccount_number());
                }
                if (beginningInventory.getAccount_name() != null) {
                    existingBeginningInventory.setAccount_name(beginningInventory.getAccount_name());
                }
                if (beginningInventory.getFirst_debt() != null) {
                    existingBeginningInventory.setFirst_debt(beginningInventory.getFirst_debt());
                }
                if (beginningInventory.getFirst_yes() != null) {
                    existingBeginningInventory.setFirst_yes(beginningInventory.getFirst_yes());
                }
                if (beginningInventory.getDebt_arises() != null) {
                    existingBeginningInventory.setDebt_arises(beginningInventory.getDebt_arises());
                }
                if (beginningInventory.getArises_yes() != null) {
                    existingBeginningInventory.setArises_yes(beginningInventory.getArises_yes());
                }
                if (beginningInventory.getAccumulated_debt() != null) {
                    existingBeginningInventory.setAccumulated_debt(beginningInventory.getAccumulated_debt());
                }
                if (beginningInventory.getAccumulated_yes() != null) {
                    existingBeginningInventory.setAccumulated_yes(beginningInventory.getAccumulated_yes());
                }
                if (beginningInventory.getLast_debt() != null) {
                    existingBeginningInventory.setLast_debt(beginningInventory.getLast_debt());
                }
                if (beginningInventory.getLast_yes() != null) {
                    existingBeginningInventory.setLast_yes(beginningInventory.getLast_yes());
                }
                if (beginningInventory.getCreated_date() != null) {
                    existingBeginningInventory.setCreated_date(beginningInventory.getCreated_date());
                }
                if (beginningInventory.getKeyUUID() != null) {
                    existingBeginningInventory.setKeyUUID(beginningInventory.getKeyUUID());
                }

                return existingBeginningInventory;
            })
            .map(beginningInventoryRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BeginningInventory> findAll() {
        log.debug("Request to get all BeginningInventories");
        return beginningInventoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BeginningInventory> findOne(Long id) {
        log.debug("Request to get BeginningInventory : {}", id);
        return beginningInventoryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BeginningInventory : {}", id);
        beginningInventoryRepository.deleteById(id);
    }

    @Override
    public void saveToBeginningInventory(MultipartFile file) {
        try {
            List<BeginningInventory> beginningInventories = BeginningInventoryExcelHelper.excelToBeginningInventory(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (BeginningInventory beginningInventory : beginningInventories) {
                beginningInventory.setCreated_date(date);
                beginningInventory.setKeyUUID(key);
            }
            beginningInventoryRepository.saveAll(beginningInventories);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
