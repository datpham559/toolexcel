package com.softdreams.excel.repository.custom.impl;

import com.softdreams.excel.repository.custom.SyntheticRepositoryCustom;
import com.softdreams.excel.service.dto.SyntheticDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class SyntheticRepositoryCustomImpl implements SyntheticRepositoryCustom {


    @Autowired
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<SyntheticDTO> getSynthetic(int voucherTypeNo,String keyUUID) {
        StringBuilder sql = new StringBuilder();
        sql.append(
            "exec ConvertDataToTax @voucherTypeNo = ?1,@keyUUID = ?2"
        );
        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter(1, voucherTypeNo);
        query.setParameter(2,keyUUID);
        query.executeUpdate();
        List<SyntheticDTO> syntheticDTOS = query.getResultList();
        return syntheticDTOS;
    }
}
