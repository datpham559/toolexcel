package com.softdreams.excel.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SyntheticDTO {

    private Long id;

    private String voucherType;

    private Integer voucherTypeNo;

    private String voucherNo;

    private LocalDate voucherDate;

    private LocalDate accountingDate;

    private String invoiceNo;

    private LocalDate invoiceDate;

    private String debitAccount;

    private String creditAccount;

    private String currencyType;

    private BigDecimal currency;

    private String materialGoodCode;

    private String materialGoodName;

    private String storageIn;

    private String storageOut;

    private String caculationUnit;

    private Float amount;

    private BigDecimal price;

    private BigDecimal tranferRate;

    private BigDecimal moneyTranfer;

    private String fixedAssetsType;

    private String fixedAssetsCode;

    private String toolsCode;

    private String debitObject;

    private String creditObject;

    private String unit;

    private String employee;

    private String bankAccount;

    private String itemCost;

    private String construction;

    private String costSet;

    private String purchaseOrder;

    private String buyOrder;

    private String purchaseContract;

    private String saleContract;

    private String statsCode;

    private String explanation;

    private String explanationDetail;

    private String recordStatus;

    private LocalDate createdDate;

    private String keyUUID;

    private BigDecimal currencyTax;

    private String debitAccountTax;

    private String creditAccountTax;

    private Integer taxPercent;

    public SyntheticDTO(Long id, String voucherType, Integer voucherTypeNo,
                        String voucherNo, LocalDate voucherDate, LocalDate accountingDate,
                        String invoiceNo, LocalDate invoiceDate, String debitAccount,
                        String creditAccount, String currencyType,
                        BigDecimal currency, String materialGoodCode, String materialGoodName,
                        String storageIn, String storageOut, String caculationUnit,
                        Float amount, BigDecimal price, BigDecimal tranferRate,
                        BigDecimal moneyTranfer, String fixedAssetsType, String fixedAssetsCode,
                        String toolsCode, String debitObject, String creditObject,
                        String unit, String employee, String bankAccount, String itemCost,
                        String construction, String costSet, String purchaseOrder,
                        String buyOrder, String purchaseContract, String saleContract,
                        String statsCode, String explanation, String explanationDetail,
                        String recordStatus, LocalDate createdDate, String keyUUID,
                        BigDecimal currencyTax, Integer taxPercent, String debitAccountTax, String creditAccountTax) {
        this.id = id;
        this.voucherType = voucherType;
        this.voucherTypeNo = voucherTypeNo;
        this.voucherNo = voucherNo;
        this.voucherDate = voucherDate;
        this.accountingDate = accountingDate;
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.currencyType = currencyType;
        this.currency = currency;
        this.materialGoodCode = materialGoodCode;
        this.materialGoodName = materialGoodName;
        this.storageIn = storageIn;
        this.storageOut = storageOut;
        this.caculationUnit = caculationUnit;
        this.amount = amount;
        this.price = price;
        this.tranferRate = tranferRate;
        this.moneyTranfer = moneyTranfer;
        this.fixedAssetsType = fixedAssetsType;
        this.fixedAssetsCode = fixedAssetsCode;
        this.toolsCode = toolsCode;
        this.debitObject = debitObject;
        this.creditObject = creditObject;
        this.unit = unit;
        this.employee = employee;
        this.bankAccount = bankAccount;
        this.itemCost = itemCost;
        this.construction = construction;
        this.costSet = costSet;
        this.purchaseOrder = purchaseOrder;
        this.buyOrder = buyOrder;
        this.purchaseContract = purchaseContract;
        this.saleContract = saleContract;
        this.statsCode = statsCode;
        this.explanation = explanation;
        this.explanationDetail = explanationDetail;
        this.recordStatus = recordStatus;
        this.createdDate = createdDate;
        this.keyUUID = keyUUID;
        this.currencyTax = currencyTax;
        this.taxPercent = taxPercent;
        this.debitAccountTax = debitAccountTax;
        this.creditAccountTax = creditAccountTax;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public Integer getVoucherTypeNo() {
        return voucherTypeNo;
    }

    public void setVoucherTypeNo(Integer voucherTypeNo) {
        this.voucherTypeNo = voucherTypeNo;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public LocalDate getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(LocalDate voucherDate) {
        this.voucherDate = voucherDate;
    }

    public LocalDate getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(LocalDate accountingDate) {
        this.accountingDate = accountingDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getCurrency() {
        return currency;
    }

    public void setCurrency(BigDecimal currency) {
        this.currency = currency;
    }

    public String getMaterialGoodCode() {
        return materialGoodCode;
    }

    public void setMaterialGoodCode(String materialGoodCode) {
        this.materialGoodCode = materialGoodCode;
    }

    public String getMaterialGoodName() {
        return materialGoodName;
    }

    public void setMaterialGoodName(String materialGoodName) {
        this.materialGoodName = materialGoodName;
    }

    public String getStorageIn() {
        return storageIn;
    }

    public void setStorageIn(String storageIn) {
        this.storageIn = storageIn;
    }

    public String getStorageOut() {
        return storageOut;
    }

    public void setStorageOut(String storageOut) {
        this.storageOut = storageOut;
    }

    public String getCaculationUnit() {
        return caculationUnit;
    }

    public void setCaculationUnit(String caculationUnit) {
        this.caculationUnit = caculationUnit;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTranferRate() {
        return tranferRate;
    }

    public void setTranferRate(BigDecimal tranferRate) {
        this.tranferRate = tranferRate;
    }

    public BigDecimal getMoneyTranfer() {
        return moneyTranfer;
    }

    public void setMoneyTranfer(BigDecimal moneyTranfer) {
        this.moneyTranfer = moneyTranfer;
    }

    public String getFixedAssetsType() {
        return fixedAssetsType;
    }

    public void setFixedAssetsType(String fixedAssetsType) {
        this.fixedAssetsType = fixedAssetsType;
    }

    public String getFixedAssetsCode() {
        return fixedAssetsCode;
    }

    public void setFixedAssetsCode(String fixedAssetsCode) {
        this.fixedAssetsCode = fixedAssetsCode;
    }

    public String getToolsCode() {
        return toolsCode;
    }

    public void setToolsCode(String toolsCode) {
        this.toolsCode = toolsCode;
    }

    public String getDebitObject() {
        return debitObject;
    }

    public void setDebitObject(String debitObject) {
        this.debitObject = debitObject;
    }

    public String getCreditObject() {
        return creditObject;
    }

    public void setCreditObject(String creditObject) {
        this.creditObject = creditObject;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getItemCost() {
        return itemCost;
    }

    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public String getCostSet() {
        return costSet;
    }

    public void setCostSet(String costSet) {
        this.costSet = costSet;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(String buyOrder) {
        this.buyOrder = buyOrder;
    }

    public String getPurchaseContract() {
        return purchaseContract;
    }

    public void setPurchaseContract(String purchaseContract) {
        this.purchaseContract = purchaseContract;
    }

    public String getSaleContract() {
        return saleContract;
    }

    public void setSaleContract(String saleContract) {
        this.saleContract = saleContract;
    }

    public String getStatsCode() {
        return statsCode;
    }

    public void setStatsCode(String statsCode) {
        this.statsCode = statsCode;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanationDetail() {
        return explanationDetail;
    }

    public void setExplanationDetail(String explanationDetail) {
        this.explanationDetail = explanationDetail;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getKeyUUID() {
        return keyUUID;
    }

    public void setKeyUUID(String keyUUID) {
        this.keyUUID = keyUUID;
    }

    public BigDecimal getCurrencyTax() {
        return currencyTax;
    }

    public void setCurrencyTax(BigDecimal currencyTax) {
        this.currencyTax = currencyTax;
    }

    public Integer getTaxPercent() {
        return taxPercent;
    }

    public String getDebitAccountTax() {
        return debitAccountTax;
    }

    public void setDebitAccountTax(String debitAccountTax) {
        this.debitAccountTax = debitAccountTax;
    }

    public String getCreditAccountTax() {
        return creditAccountTax;
    }

    public void setCreditAccountTax(String creditAccountTax) {
        this.creditAccountTax = creditAccountTax;
    }

    public void setTaxPercent(Integer taxPercent) {
        this.taxPercent = taxPercent;
    }
}
