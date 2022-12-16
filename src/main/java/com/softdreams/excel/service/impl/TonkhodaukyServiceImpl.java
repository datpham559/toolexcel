package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.Tonkhodauky;
import com.softdreams.excel.helper.ExcelHelper;
import com.softdreams.excel.repository.TonkhodaukyRepository;
import com.softdreams.excel.service.TonkhodaukyService;
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
 * Service Implementation for managing {@link Tonkhodauky}.
 */
@Service
@Transactional
public class TonkhodaukyServiceImpl implements TonkhodaukyService {

    private final Logger log = LoggerFactory.getLogger(TonkhodaukyServiceImpl.class);

    private final TonkhodaukyRepository tonkhodaukyRepository;

    public TonkhodaukyServiceImpl(TonkhodaukyRepository tonkhodaukyRepository) {
        this.tonkhodaukyRepository = tonkhodaukyRepository;
    }

    @Override
    public Tonkhodauky save(Tonkhodauky tonkhodauky) {
        log.debug("Request to save Tonkhodauky : {}", tonkhodauky);
        return tonkhodaukyRepository.save(tonkhodauky);
    }

    @Override
    public Tonkhodauky update(Tonkhodauky tonkhodauky) {
        log.debug("Request to update Tonkhodauky : {}", tonkhodauky);
        return tonkhodaukyRepository.save(tonkhodauky);
    }

    @Override
    public Optional<Tonkhodauky> partialUpdate(Tonkhodauky tonkhodauky) {
        log.debug("Request to partially update Tonkhodauky : {}", tonkhodauky);

        return tonkhodaukyRepository
            .findById(tonkhodauky.getId())
            .map(existingTonkhodauky -> {
                if (tonkhodauky.getTen_kho() != null) {
                    existingTonkhodauky.setTen_kho(tonkhodauky.getTen_kho());
                }
                if (tonkhodauky.getMa_hang() != null) {
                    existingTonkhodauky.setMa_hang(tonkhodauky.getMa_hang());
                }
                if (tonkhodauky.getTen_hang() != null) {
                    existingTonkhodauky.setTen_hang(tonkhodauky.getTen_hang());
                }
                if (tonkhodauky.getDvt() != null) {
                    existingTonkhodauky.setDvt(tonkhodauky.getDvt());
                }
                if (tonkhodauky.getDau_ky_so_luong() != null) {
                    existingTonkhodauky.setDau_ky_so_luong(tonkhodauky.getDau_ky_so_luong());
                }
                if (tonkhodauky.getDau_ky_gia_tri() != null) {
                    existingTonkhodauky.setDau_ky_gia_tri(tonkhodauky.getDau_ky_gia_tri());
                }
                if (tonkhodauky.getNhap_kho_so_luong() != null) {
                    existingTonkhodauky.setNhap_kho_so_luong(tonkhodauky.getNhap_kho_so_luong());
                }
                if (tonkhodauky.getNhap_kho_gia_tri() != null) {
                    existingTonkhodauky.setNhap_kho_gia_tri(tonkhodauky.getNhap_kho_gia_tri());
                }
                if (tonkhodauky.getXuat_kho_so_luong() != null) {
                    existingTonkhodauky.setXuat_kho_so_luong(tonkhodauky.getXuat_kho_so_luong());
                }
                if (tonkhodauky.getXuat_kho_gia_tri() != null) {
                    existingTonkhodauky.setXuat_kho_gia_tri(tonkhodauky.getXuat_kho_gia_tri());
                }
                if (tonkhodauky.getCuoi_ky_so_luong() != null) {
                    existingTonkhodauky.setCuoi_ky_so_luong(tonkhodauky.getCuoi_ky_so_luong());
                }
                if (tonkhodauky.getCuoi_ky_gia_tri() != null) {
                    existingTonkhodauky.setCuoi_ky_gia_tri(tonkhodauky.getCuoi_ky_gia_tri());
                }
                if (tonkhodauky.getDon_gia_binh_quan() != null) {
                    existingTonkhodauky.setDon_gia_binh_quan(tonkhodauky.getDon_gia_binh_quan());
                }
                if (tonkhodauky.getCreatedDate() != null) {
                    existingTonkhodauky.setCreatedDate(tonkhodauky.getCreatedDate());
                }
                if (tonkhodauky.getKey_uuid() != null) {
                    existingTonkhodauky.setKey_uuid(tonkhodauky.getKey_uuid());
                }

                return existingTonkhodauky;
            })
            .map(tonkhodaukyRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tonkhodauky> findAll() {
        log.debug("Request to get all Tonkhodaukies");
        return tonkhodaukyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tonkhodauky> findOne(Long id) {
        log.debug("Request to get Tonkhodauky : {}", id);
        return tonkhodaukyRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tonkhodauky : {}", id);
        tonkhodaukyRepository.deleteById(id);
    }

    @Override
    public void saveTonkhodauky(MultipartFile file) {
        try {
            List<Tonkhodauky> Tonkhodaukys = ExcelHelper.excelToTonkhodauky(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Tonkhodauky Tonkhodauky : Tonkhodaukys) {
                Tonkhodauky.setCreatedDate(date);
                Tonkhodauky.setKey_uuid(key);
            }
            tonkhodaukyRepository.saveAll(Tonkhodaukys);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
