package com.softdreams.excel.repository.custom.impl;

import com.softdreams.excel.domain.Synthetic;
import com.softdreams.excel.repository.custom.SyntheticRepositoryCustom;
import com.softdreams.excel.service.dto.SyntheticDTO;
import com.softdreams.excel.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class SyntheticRepositoryCustomImpl implements SyntheticRepositoryCustom {
    @Autowired
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<SyntheticDTO> getSynthetic(int voucherTypeNo, String keyUUID) {
        StringBuilder sql = new StringBuilder();
        sql.append(
            "exec ConvertDataToTax @voucherTypeNo = ?1,@keyUUID = ?2"
        );
        Query query = entityManager.createNativeQuery(sql.toString(), "SyntheticDTO");
        query.setParameter(1, voucherTypeNo);
        query.setParameter(2, keyUUID);
        query.executeUpdate();
        List<SyntheticDTO> syntheticDTOS = query.getResultList();
        return syntheticDTOS;
    }
    public void insertBulk(List<Synthetic> synthetics){
        String sql =
            "   insert into synthetic (voucherType,voucherTypeNo,voucherNo,voucherDate,accountingDate," +
                "invoiceNo,invoiceDate,debitAccount,creditAccount,currencyType,currency,materialGoodCode," +
                "materialGoodName,storageIn,storageOut,caculationUnit,amount,price,tranferRate,moneyTranfer,fixedAssetsType,fixedAssetsCode," +
                "toolsCode,debitObject,creditObject,unit,employee,bankAccount,itemCost,construction,costSet,purchaseOrder," +
                "buyOrder,purchaseContract,saleContract,statsCode,explanation,explanationDetail,recordStatus,createdDate,keyUUID)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " ;
        jdbcTemplate.batchUpdate(sql,
            synthetics,
            500,
            (ps, detail) -> {
                Common.setParam(ps,1,detail.getVoucherType());
                Common.setParam(ps,2,detail.getVoucherTypeNo());
                Common.setParam(ps,3,detail.getVoucherNo());
                Common.setParam(ps,4,detail.getVoucherDate());
                Common.setParam(ps,5,detail.getAccountingDate());
                Common.setParam(ps,6,detail.getInvoiceNo());
                Common.setParam(ps,7,detail.getInvoiceDate());
                Common.setParam(ps,8,detail.getDebitAccount());
                Common.setParam(ps,9,detail.getCreditAccount());
                Common.setParam(ps,10,detail.getCurrencyType());
                Common.setParam(ps,11,detail.getCurrency());
                Common.setParam(ps,12,detail.getMaterialGoodCode());
                Common.setParam(ps,13,detail.getMaterialGoodName());
                Common.setParam(ps,14,detail.getStorageIn());
                Common.setParam(ps,15,detail.getStorageOut());
                Common.setParam(ps,16,detail.getCaculationUnit());
                Common.setParam(ps,17,detail.getAmount());
                Common.setParam(ps,18,detail.getPrice());
                Common.setParam(ps,19,detail.getTranferRate());
                Common.setParam(ps,20,detail.getMoneyTranfer());
                Common.setParam(ps,21,detail.getFixedAssetsType());
                Common.setParam(ps,22,detail.getFixedAssetsCode());
                Common.setParam(ps,23,detail.getToolsCode());
                Common.setParam(ps,24,detail.getDebitObject());
                Common.setParam(ps,25,detail.getCreditObject());
                Common.setParam(ps,26,detail.getUnit());
                Common.setParam(ps,27,detail.getEmployee());
                Common.setParam(ps,28,detail.getBankAccount());
                Common.setParam(ps,29,detail.getItemCost());
                Common.setParam(ps,30,detail.getConstruction());
                Common.setParam(ps,31,detail.getCostSet());
                Common.setParam(ps,32,detail.getPurchaseOrder());
                Common.setParam(ps,33,detail.getBuyOrder());
                Common.setParam(ps,34,detail.getPurchaseContract());
                Common.setParam(ps,35,detail.getSaleContract());
                Common.setParam(ps,36,detail.getStatsCode());
                Common.setParam(ps,37,detail.getExplanation());
                Common.setParam(ps,38,detail.getExplanationDetail());
                Common.setParam(ps,39,detail.getRecordStatus());
                Common.setParam(ps,40,detail.getCreatedDate());
                Common.setParam(ps,41,detail.getKeyUUID());
            }
            );
    }
}
