package com.softdreams.excel.repository.custom.impl;

import com.softdreams.excel.repository.custom.SyntheticRepositoryCustom;
import com.softdreams.excel.service.dto.SyntheticDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

public class SyntheticRepositoryCustomImpl implements SyntheticRepositoryCustom {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<SyntheticDTO> getSynthetic(int voucherTypeNo, String keyUUID) {
        StringBuilder sql = new StringBuilder();
        sql.append("exec ConvertDataToTax @voucherTypeNo = ?1,@keyUUID = ?2");
        Query query = entityManager.createNativeQuery(sql.toString(), "SyntheticDTO");
        query.setParameter(1, voucherTypeNo);
        query.setParameter(2, keyUUID);
        query.executeUpdate();
        List<SyntheticDTO> syntheticDTOS = query.getResultList();
        return syntheticDTOS;
    }
}
