package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.Taisancodinh;
import com.softdreams.excel.helper.ExcelHelper;
import com.softdreams.excel.helper.TaisancodinhHelper;
import com.softdreams.excel.repository.TaisancodinhRepository;
import com.softdreams.excel.service.TaisancodinhService;
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
 * Service Implementation for managing {@link Taisancodinh}.
 */
@Service
@Transactional
public class TaisancodinhServiceImpl implements TaisancodinhService {

    private final Logger log = LoggerFactory.getLogger(TaisancodinhServiceImpl.class);

    private final TaisancodinhRepository taisancodinhRepository;

    public TaisancodinhServiceImpl(TaisancodinhRepository taisancodinhRepository) {
        this.taisancodinhRepository = taisancodinhRepository;
    }

    @Override
    public Taisancodinh save(Taisancodinh taisancodinh) {
        log.debug("Request to save Taisancodinh : {}", taisancodinh);
        return taisancodinhRepository.save(taisancodinh);
    }

    @Override
    public Taisancodinh update(Taisancodinh taisancodinh) {
        log.debug("Request to update Taisancodinh : {}", taisancodinh);
        return taisancodinhRepository.save(taisancodinh);
    }

    @Override
    public Optional<Taisancodinh> partialUpdate(Taisancodinh taisancodinh) {
        log.debug("Request to partially update Taisancodinh : {}", taisancodinh);

        return taisancodinhRepository
            .findById(taisancodinh.getId())
            .map(existingTaisancodinh -> {
                if (taisancodinh.getMa_tscd() != null) {
                    existingTaisancodinh.setMa_tscd(taisancodinh.getMa_tscd());
                }
                if (taisancodinh.getTen_tscd() != null) {
                    existingTaisancodinh.setTen_tscd(taisancodinh.getTen_tscd());
                }
                if (taisancodinh.getLoai_tscd() != null) {
                    existingTaisancodinh.setLoai_tscd(taisancodinh.getLoai_tscd());
                }
                if (taisancodinh.getDon_vi_su_dung() != null) {
                    existingTaisancodinh.setDon_vi_su_dung(taisancodinh.getDon_vi_su_dung());
                }
                if (taisancodinh.getNgay_ghi_tang() != null) {
                    existingTaisancodinh.setNgay_ghi_tang(taisancodinh.getNgay_ghi_tang());
                }
                if (taisancodinh.getSo_ct_ghi_tang() != null) {
                    existingTaisancodinh.setSo_ct_ghi_tang(taisancodinh.getSo_ct_ghi_tang());
                }
                if (taisancodinh.getNgay_bat_dau_tinh_kh() != null) {
                    existingTaisancodinh.setNgay_bat_dau_tinh_kh(taisancodinh.getNgay_bat_dau_tinh_kh());
                }
                if (taisancodinh.getThoi_gian_su_dung() != null) {
                    existingTaisancodinh.setThoi_gian_su_dung(taisancodinh.getThoi_gian_su_dung());
                }
                if (taisancodinh.getThoi_gian_su_dung_con_lai() != null) {
                    existingTaisancodinh.setThoi_gian_su_dung_con_lai(taisancodinh.getThoi_gian_su_dung_con_lai());
                }
                if (taisancodinh.getNguyen_gia() != null) {
                    existingTaisancodinh.setNguyen_gia(taisancodinh.getNguyen_gia());
                }
                if (taisancodinh.getGia_tri_tinh_kh() != null) {
                    existingTaisancodinh.setGia_tri_tinh_kh(taisancodinh.getGia_tri_tinh_kh());
                }
                if (taisancodinh.getHao_mon_trong_ky() != null) {
                    existingTaisancodinh.setHao_mon_trong_ky(taisancodinh.getHao_mon_trong_ky());
                }
                if (taisancodinh.getHao_mon_luy_ke() != null) {
                    existingTaisancodinh.setHao_mon_luy_ke(taisancodinh.getHao_mon_luy_ke());
                }
                if (taisancodinh.getGia_tri_con_lai() != null) {
                    existingTaisancodinh.setGia_tri_con_lai(taisancodinh.getGia_tri_con_lai());
                }
                if (taisancodinh.getGia_tri_KH_thang() != null) {
                    existingTaisancodinh.setGia_tri_KH_thang(taisancodinh.getGia_tri_KH_thang());
                }
                if (taisancodinh.getTk_nguyen_gia() != null) {
                    existingTaisancodinh.setTk_nguyen_gia(taisancodinh.getTk_nguyen_gia());
                }
                if (taisancodinh.getTk_khau_hao() != null) {
                    existingTaisancodinh.setTk_khau_hao(taisancodinh.getTk_khau_hao());
                }
                if (taisancodinh.getCreatedDate() != null) {
                    existingTaisancodinh.setCreatedDate(taisancodinh.getCreatedDate());
                }
                if (taisancodinh.getKey_uuid() != null) {
                    existingTaisancodinh.setKey_uuid(taisancodinh.getKey_uuid());
                }

                return existingTaisancodinh;
            })
            .map(taisancodinhRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Taisancodinh> findAll() {
        log.debug("Request to get all Taisancodinhs");
        return taisancodinhRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Taisancodinh> findOne(Long id) {
        log.debug("Request to get Taisancodinh : {}", id);
        return taisancodinhRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Taisancodinh : {}", id);
        taisancodinhRepository.deleteById(id);
    }

    @Override
    public void saveTaisancodinh(MultipartFile file) {
        try {
            List<Taisancodinh> Taisancodinhs = TaisancodinhHelper.excelToTaisancodinh(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Taisancodinh Taisancodinh : Taisancodinhs) {
                Taisancodinh.setCreatedDate(date);
                Taisancodinh.setKey_uuid(key);
            }
            taisancodinhRepository.saveAll(Taisancodinhs);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
