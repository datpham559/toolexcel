package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.Congnophaithu;
import com.softdreams.excel.helper.ExcelHelper;
import com.softdreams.excel.repository.CongnophaithuRepository;
import com.softdreams.excel.service.CongnophaithuService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Implementation for managing {@link Congnophaithu}.
 */
@Service
@Transactional
public class CongnophaithuServiceImpl implements CongnophaithuService {

    @Autowired
    private CongnophaithuRepository congnophaithuRepository;

    private final Logger log = LoggerFactory.getLogger(CongnophaithuServiceImpl.class);

    public CongnophaithuServiceImpl(CongnophaithuRepository congnophaithuRepository) {
        this.congnophaithuRepository = congnophaithuRepository;
    }

    @Override
    public Congnophaithu save(Congnophaithu congnophaithu) {
        log.debug("Request to save Congnophaithu : {}", congnophaithu);
        return congnophaithuRepository.save(congnophaithu);
    }

    @Override
    public Congnophaithu update(Congnophaithu congnophaithu) {
        log.debug("Request to update Congnophaithu : {}", congnophaithu);
        return congnophaithuRepository.save(congnophaithu);
    }

    @Override
    public Optional<Congnophaithu> partialUpdate(Congnophaithu congnophaithu) {
        log.debug("Request to partially update Congnophaithu : {}", congnophaithu);

        return congnophaithuRepository
            .findById(congnophaithu.getId())
            .map(existingCongnophaithu -> {
                if (congnophaithu.getMakh() != null) {
                    existingCongnophaithu.setMakh(congnophaithu.getMakh());
                }
                if (congnophaithu.getTenkh() != null) {
                    existingCongnophaithu.setTenkh(congnophaithu.getTenkh());
                }
                if (congnophaithu.getTkcongno() != null) {
                    existingCongnophaithu.setTkcongno(congnophaithu.getTkcongno());
                }
                if (congnophaithu.getSodunodauky() != null) {
                    existingCongnophaithu.setSodunodauky(congnophaithu.getSodunodauky());
                }
                if (congnophaithu.getSoducodauky() != null) {
                    existingCongnophaithu.setSoducodauky(congnophaithu.getSoducodauky());
                }
                if (congnophaithu.getSonophatsinh() != null) {
                    existingCongnophaithu.setSonophatsinh(congnophaithu.getSonophatsinh());
                }
                if (congnophaithu.getSocophatsinh() != null) {
                    existingCongnophaithu.setSocophatsinh(congnophaithu.getSocophatsinh());
                }
                if (congnophaithu.getSodunocuoiky() != null) {
                    existingCongnophaithu.setSodunocuoiky(congnophaithu.getSodunocuoiky());
                }
                if (congnophaithu.getSoducocuoiky() != null) {
                    existingCongnophaithu.setSoducocuoiky(congnophaithu.getSoducocuoiky());
                }
                if (congnophaithu.getCreatedDate() != null) {
                    existingCongnophaithu.setCreatedDate(congnophaithu.getCreatedDate());
                }
                if (congnophaithu.getKey_uuid() != null) {
                    existingCongnophaithu.setKey_uuid(congnophaithu.getKey_uuid());
                }

                return existingCongnophaithu;
            })
            .map(congnophaithuRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Congnophaithu> findAll() {
        log.debug("Request to get all Congnophaithus");
        return congnophaithuRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Congnophaithu> findOne(Long id) {
        log.debug("Request to get Congnophaithu : {}", id);
        return congnophaithuRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Congnophaithu : {}", id);
        congnophaithuRepository.deleteById(id);
    }

    @Override
    public void saveCongnophaithu(MultipartFile file) {
        try {
            List<Congnophaithu> Congnophaithus = ExcelHelper.excelToCong_no_phai_thu(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Congnophaithu Congnophaithu : Congnophaithus) {
                Congnophaithu.setCreatedDate(date);
                Congnophaithu.setKey_uuid(key);
            }
            congnophaithuRepository.saveAll(Congnophaithus);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
