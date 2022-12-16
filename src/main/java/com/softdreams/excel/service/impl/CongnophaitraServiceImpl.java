package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.Congnophaitra;
import com.softdreams.excel.helper.ExcelHelper;
import com.softdreams.excel.repository.CongnophaitraRepository;
import com.softdreams.excel.service.CongnophaitraService;
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
 * Service Implementation for managing {@link Congnophaitra}.
 */
@Service
@Transactional
public class CongnophaitraServiceImpl implements CongnophaitraService {

    private final Logger log = LoggerFactory.getLogger(CongnophaitraServiceImpl.class);

    private final CongnophaitraRepository congnophaitraRepository;

    public CongnophaitraServiceImpl(CongnophaitraRepository congnophaitraRepository) {
        this.congnophaitraRepository = congnophaitraRepository;
    }

    @Override
    public Congnophaitra save(Congnophaitra congnophaitra) {
        log.debug("Request to save Congnophaitra : {}", congnophaitra);
        return congnophaitraRepository.save(congnophaitra);
    }

    @Override
    public Congnophaitra update(Congnophaitra congnophaitra) {
        log.debug("Request to update Congnophaitra : {}", congnophaitra);
        return congnophaitraRepository.save(congnophaitra);
    }

    @Override
    public Optional<Congnophaitra> partialUpdate(Congnophaitra congnophaitra) {
        log.debug("Request to partially update Congnophaitra : {}", congnophaitra);

        return congnophaitraRepository
            .findById(congnophaitra.getId())
            .map(existingCongnophaitra -> {
                if (congnophaitra.getMa_ncc() != null) {
                    existingCongnophaitra.setMa_ncc(congnophaitra.getMa_ncc());
                }
                if (congnophaitra.getTen_ncc() != null) {
                    existingCongnophaitra.setTen_ncc(congnophaitra.getTen_ncc());
                }
                if (congnophaitra.getTk_congno() != null) {
                    existingCongnophaitra.setTk_congno(congnophaitra.getTk_congno());
                }
                if (congnophaitra.getSo_du_no_dau_ky() != null) {
                    existingCongnophaitra.setSo_du_no_dau_ky(congnophaitra.getSo_du_no_dau_ky());
                }
                if (congnophaitra.getSo_du_co_dau_ky() != null) {
                    existingCongnophaitra.setSo_du_co_dau_ky(congnophaitra.getSo_du_co_dau_ky());
                }
                if (congnophaitra.getPhat_sinh_no() != null) {
                    existingCongnophaitra.setPhat_sinh_no(congnophaitra.getPhat_sinh_no());
                }
                if (congnophaitra.getPhat_sinh_co() != null) {
                    existingCongnophaitra.setPhat_sinh_co(congnophaitra.getPhat_sinh_co());
                }
                if (congnophaitra.getSo_du_no_cuoi_ky() != null) {
                    existingCongnophaitra.setSo_du_no_cuoi_ky(congnophaitra.getSo_du_no_cuoi_ky());
                }
                if (congnophaitra.getSo_du_co_cuoi_ky() != null) {
                    existingCongnophaitra.setSo_du_co_cuoi_ky(congnophaitra.getSo_du_co_cuoi_ky());
                }
                if (congnophaitra.getCreated_Date() != null) {
                    existingCongnophaitra.setCreated_Date(congnophaitra.getCreated_Date());
                }
                if (congnophaitra.getKey_uuid() != null) {
                    existingCongnophaitra.setKey_uuid(congnophaitra.getKey_uuid());
                }

                return existingCongnophaitra;
            })
            .map(congnophaitraRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Congnophaitra> findAll() {
        log.debug("Request to get all Congnophaitras");
        return congnophaitraRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Congnophaitra> findOne(Long id) {
        log.debug("Request to get Congnophaitra : {}", id);
        return congnophaitraRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Congnophaitra : {}", id);
        congnophaitraRepository.deleteById(id);
    }

    @Override
    public void saveCongnophaitra(MultipartFile file) {
        try {
            List<Congnophaitra> Congnophaitras = ExcelHelper.excelToCong_no_phai_tra(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Congnophaitra Congnophaitra : Congnophaitras) {
                Congnophaitra.setCreated_Date(date);
                Congnophaitra.setKey_uuid(key);
            }
            congnophaitraRepository.saveAll(Congnophaitras);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
