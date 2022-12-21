package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.DmNCC;
import com.softdreams.excel.helper.DmNccExcelHelper;
import com.softdreams.excel.repository.DmNCCRepository;
import com.softdreams.excel.service.DmNCCService;
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
 * Service Implementation for managing {@link DmNCC}.
 */
@Service
@Transactional
public class DmNCCServiceImpl implements DmNCCService {

    private final Logger log = LoggerFactory.getLogger(DmNCCServiceImpl.class);

    private final DmNCCRepository dmNCCRepository;

    public DmNCCServiceImpl(DmNCCRepository dmNCCRepository) {
        this.dmNCCRepository = dmNCCRepository;
    }

    @Override
    public DmNCC save(DmNCC dmNCC) {
        log.debug("Request to save DmNCC : {}", dmNCC);
        return dmNCCRepository.save(dmNCC);
    }

    @Override
    public DmNCC update(DmNCC dmNCC) {
        log.debug("Request to update DmNCC : {}", dmNCC);
        return dmNCCRepository.save(dmNCC);
    }

    @Override
    public Optional<DmNCC> partialUpdate(DmNCC dmNCC) {
        log.debug("Request to partially update DmNCC : {}", dmNCC);

        return dmNCCRepository
            .findById(dmNCC.getId())
            .map(existingDmNCC -> {
                if (dmNCC.getSupplier_code() != null) {
                    existingDmNCC.setSupplier_code(dmNCC.getSupplier_code());
                }
                if (dmNCC.getSupplier_name() != null) {
                    existingDmNCC.setSupplier_name(dmNCC.getSupplier_name());
                }
                if (dmNCC.getAddress() != null) {
                    existingDmNCC.setAddress(dmNCC.getAddress());
                }
                if (dmNCC.getGroup_kh_ncc() != null) {
                    existingDmNCC.setGroup_kh_ncc(dmNCC.getGroup_kh_ncc());
                }
                if (dmNCC.getTax_code() != null) {
                    existingDmNCC.setTax_code(dmNCC.getTax_code());
                }
                if (dmNCC.getPhone_number() != null) {
                    existingDmNCC.setPhone_number(dmNCC.getPhone_number());
                }
                if (dmNCC.getUnfollow() != null) {
                    existingDmNCC.setUnfollow(dmNCC.getUnfollow());
                }
                if (dmNCC.getCreated_date() != null) {
                    existingDmNCC.setCreated_date(dmNCC.getCreated_date());
                }
                if (dmNCC.getKeyUUID() != null) {
                    existingDmNCC.setKeyUUID(dmNCC.getKeyUUID());
                }

                return existingDmNCC;
            })
            .map(dmNCCRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DmNCC> findAll() {
        log.debug("Request to get all DmNCCS");
        return dmNCCRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DmNCC> findOne(Long id) {
        log.debug("Request to get DmNCC : {}", id);
        return dmNCCRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DmNCC : {}", id);
        dmNCCRepository.deleteById(id);
    }

    @Override
    public void saveToDmNcc(MultipartFile file) {
        try {
            List<DmNCC> dmNCCS = DmNccExcelHelper.excelToDmNcc(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (DmNCC dmNCC : dmNCCS) {
                dmNCC.setCreated_date(date);
                dmNCC.setKeyUUID(key);
            }
            dmNCCRepository.saveAll(dmNCCS);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
