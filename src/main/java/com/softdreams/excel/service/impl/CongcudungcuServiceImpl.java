package com.softdreams.excel.service.impl;

import com.softdreams.excel.domain.Congcudungcu;
import com.softdreams.excel.helper.ExcelHelper;
import com.softdreams.excel.repository.CongcudungcuRepository;
import com.softdreams.excel.service.CongcudungcuService;
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
 * Service Implementation for managing {@link Congcudungcu}.
 */
@Service
@Transactional
public class CongcudungcuServiceImpl implements CongcudungcuService {

    private final Logger log = LoggerFactory.getLogger(CongcudungcuServiceImpl.class);

    private final CongcudungcuRepository congcudungcuRepository;

    public CongcudungcuServiceImpl(CongcudungcuRepository congcudungcuRepository) {
        this.congcudungcuRepository = congcudungcuRepository;
    }

    @Override
    public Congcudungcu save(Congcudungcu congcudungcu) {
        log.debug("Request to save Congcudungcu : {}", congcudungcu);
        return congcudungcuRepository.save(congcudungcu);
    }

    @Override
    public Congcudungcu update(Congcudungcu congcudungcu) {
        log.debug("Request to update Congcudungcu : {}", congcudungcu);
        return congcudungcuRepository.save(congcudungcu);
    }

    @Override
    public Optional<Congcudungcu> partialUpdate(Congcudungcu congcudungcu) {
        log.debug("Request to partially update Congcudungcu : {}", congcudungcu);

        return congcudungcuRepository
            .findById(congcudungcu.getId())
            .map(existingCongcudungcu -> {
                if (congcudungcu.getMa_ccdc() != null) {
                    existingCongcudungcu.setMa_ccdc(congcudungcu.getMa_ccdc());
                }
                if (congcudungcu.getTen_ccdc() != null) {
                    existingCongcudungcu.setTen_ccdc(congcudungcu.getTen_ccdc());
                }
                if (congcudungcu.getLoai_ccdc() != null) {
                    existingCongcudungcu.setLoai_ccdc(congcudungcu.getLoai_ccdc());
                }
                if (congcudungcu.getLy_do_ghi_tang() != null) {
                    existingCongcudungcu.setLy_do_ghi_tang(congcudungcu.getLy_do_ghi_tang());
                }
                if (congcudungcu.getNgay_ghi_tang() != null) {
                    existingCongcudungcu.setNgay_ghi_tang(congcudungcu.getNgay_ghi_tang());
                }
                if (congcudungcu.getSo_ct_ghi_tang() != null) {
                    existingCongcudungcu.setSo_ct_ghi_tang(congcudungcu.getSo_ct_ghi_tang());
                }
                if (congcudungcu.getSo_ky_phan_bo() != null) {
                    existingCongcudungcu.setSo_ky_phan_bo(congcudungcu.getSo_ky_phan_bo());
                }
                if (congcudungcu.getSo_ky_pb_con_lai() != null) {
                    existingCongcudungcu.setSo_ky_pb_con_lai(congcudungcu.getSo_ky_pb_con_lai());
                }
                if (congcudungcu.getDvt() != null) {
                    existingCongcudungcu.setDvt(congcudungcu.getDvt());
                }
                if (congcudungcu.getSl_ghi_tang() != null) {
                    existingCongcudungcu.setSl_ghi_tang(congcudungcu.getSl_ghi_tang());
                }
                if (congcudungcu.getLuy_ke_sl_da_giam() != null) {
                    existingCongcudungcu.setLuy_ke_sl_da_giam(congcudungcu.getLuy_ke_sl_da_giam());
                }
                if (congcudungcu.getSl_con_lai() != null) {
                    existingCongcudungcu.setSl_con_lai(congcudungcu.getSl_con_lai());
                }
                if (congcudungcu.getGia_tri_ccdc() != null) {
                    existingCongcudungcu.setGia_tri_ccdc(congcudungcu.getGia_tri_ccdc());
                }
                if (congcudungcu.getGia_tri_PB_hang_ky() != null) {
                    existingCongcudungcu.setGia_tri_PB_hang_ky(congcudungcu.getGia_tri_PB_hang_ky());
                }
                if (congcudungcu.getPb_trong_ky() != null) {
                    existingCongcudungcu.setPb_trong_ky(congcudungcu.getPb_trong_ky());
                }
                if (congcudungcu.getLuy_ke_da_pb() != null) {
                    existingCongcudungcu.setLuy_ke_da_pb(congcudungcu.getLuy_ke_da_pb());
                }
                if (congcudungcu.getGia_tri_con_lai() != null) {
                    existingCongcudungcu.setGia_tri_con_lai(congcudungcu.getGia_tri_con_lai());
                }
                if (congcudungcu.getTk_cho_phan_bo() != null) {
                    existingCongcudungcu.setTk_cho_phan_bo(congcudungcu.getTk_cho_phan_bo());
                }
                if (congcudungcu.getCreatedDate() != null) {
                    existingCongcudungcu.setCreatedDate(congcudungcu.getCreatedDate());
                }
                if (congcudungcu.getKey_uuid() != null) {
                    existingCongcudungcu.setKey_uuid(congcudungcu.getKey_uuid());
                }

                return existingCongcudungcu;
            })
            .map(congcudungcuRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Congcudungcu> findAll() {
        log.debug("Request to get all Congcudungcus");
        return congcudungcuRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Congcudungcu> findOne(Long id) {
        log.debug("Request to get Congcudungcu : {}", id);
        return congcudungcuRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Congcudungcu : {}", id);
        congcudungcuRepository.deleteById(id);
    }

    @Override
    public void saveCongcudungcu(MultipartFile file) {
        try {
            List<Congcudungcu> inventories = ExcelHelper.excelToCongcudungcu(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Congcudungcu Congcudungcu : inventories) {
                Congcudungcu.setCreatedDate(date);
                Congcudungcu.setKey_uuid(key);
            }
            congcudungcuRepository.saveAll(inventories);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
