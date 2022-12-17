//package com.softdreams.excel.repository.custom.impl;
//
//import com.softdreams.excel.domain.Synthetic;
//import com.softdreams.excel.repository.custom.SyntheticRepositoryCustom;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//public class SyntheticRepositoryCustomImpl implements SyntheticRepositoryCustom {
//
//
//    @Autowired
//    @PersistenceContext(unitName = "entityManagerFactory")
//    private EntityManager entityManager;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public void insertSynthetic(List<Synthetic> synthetics) {
//        String sql =
//            "DISABLE TRIGGER ALL ON synthetic; insert into synthetic (id,voucherType,voucherTypeNo,voucherNo,voucherDate,accountingDate," +
//                "invoiceNo,invoiceDate,debitAccount,creditAccount,currencyType,currency,materialGoodCode," +
//                "materialGoodName,storageIn,storageOut,caculationUnit,amount,price,tranferRate,moneyTranfer,fixedAssetsType,fixedAssetsCode," +
//                "toolsCode,debitObject,creditObject,unit,employee,bankAccount,itemCost,construction,costSet,purchaseOrder" +
//                "buyOrder,purchaseContract,saleContract,statsCode,explanation,explanationDetail,recordStatus,createdDate,keyUUID)" +
//                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
//                "ENABLE TRIGGER ALL ON synthetic;";
//        jdbcTemplate.batchUpdate(
//            sql,
//            synthetics,
//            500,
//            (ps, detail) -> {
//                Common.setParam(ps, 1, detail.getId());
//                Common.setParam(ps, 2, detail.getCompanyId());
//                Common.setParam(ps, 3, detail.getTypeId());
//                Common.setParam(ps, 4, detail.getDate());
//                Common.setParamCustom(ps, 5, detail.getPostedDate());
//                Common.setParam(ps, 6, detail.getTypeLedger());
//                Common.setParam(ps, 7, detail.getNoFBook());
//                Common.setParam(ps, 8, detail.getNoMBook());
//                Common.setParam(ps, 9, detail.getImportPurchase());
//                Common.setParam(ps, 10, detail.getAccountingObjectId());
//                Common.setParam(ps, 11, detail.getAccountingObjectName());
//                Common.setParam(ps, 12, detail.getAccountingObjectAddress());
//                Common.setParam(ps, 13, detail.getCompanyTaxCode());
//                Common.setParam(ps, 14, detail.getContactName());
//                Common.setParam(ps, 15, detail.getReason());
//                Common.setParam(ps, 16, detail.getNumberAttach());
//                Common.setParam(ps, 17, detail.getCurrencyId());
//                Common.setParam(ps, 18, detail.getExchangeRate());
//                Common.setParam(ps, 19, detail.getEmployeeId());
//                Common.setParam(ps, 20, detail.getTotalAmount());
//                Common.setParam(ps, 21, detail.getTotalAmountOriginal());
//                Common.setParam(ps, 22, detail.getTotalDiscountAmount());
//                Common.setParam(ps, 23, detail.getTotalDiscountAmountOriginal());
//                Common.setParam(ps, 24, detail.getTotalImportTaxAmount());
//                Common.setParam(ps, 25, detail.getTotalImportTaxAmountOriginal());
//                Common.setParam(ps, 26, detail.getTotalVATAmount());
//                Common.setParam(ps, 27, detail.getTotalVATAmountOriginal());
//                Common.setParam(ps, 28, detail.getTotalInwardAmount());
//                Common.setParam(ps, 29, detail.getTotalInwardAmountOriginal());
//                Common.setParam(ps, 30, detail.getTotalSpecialConsumeTaxAmount());
//                Common.setParam(ps, 31, detail.getTotalSpecialConsumeTaxAmountOriginal());
//                Common.setParam(ps, 32, detail.getTotalFreightAmount());
//                Common.setParam(ps, 33, detail.getTotalFreightAmountOriginal());
//                Common.setParam(ps, 34, detail.getTotalImportTaxExpenseAmount());
//                Common.setParam(ps, 35, detail.getTotalImportTaxExpenseAmountOriginal());
//                Common.setParam(ps, 36, detail.getRsInwardOutwardId());
//                Common.setParam(ps, 37, detail.getPaymentVoucherId());
//                Common.setParam(ps, 38, detail.isRecorded());
//                Common.setParam(ps, 39, detail.getDueDate());
//                Common.setParam(ps, 40, detail.getPaymentClauseId());
//                Common.setParam(ps, 41, detail.getTransportMethodId());
//                Common.setParam(ps, 42, detail.isStoredInRepository());
//                Common.setParam(ps, 43, detail.isBillReceived());
//            }
//        )
//    }
//}
