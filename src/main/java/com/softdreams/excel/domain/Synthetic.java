package com.softdreams.excel.domain;

import com.softdreams.excel.service.dto.SyntheticDTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Synthetic.
 */
@Entity
@Table(name = "synthetic")
@SqlResultSetMappings(
    {
        @SqlResultSetMapping(
            name = "SaInvoiceDTO",
            classes = {
                @ConstructorResult(
                    targetClass = SyntheticDTO.class,
                    columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "voucherType", type = String.class),
                        @ColumnResult(name = "voucherTypeNo", type = Integer.class),
                        @ColumnResult(name = "voucherNo", type = String.class),
                        @ColumnResult(name = "voucherDate", type = LocalDate.class),
                        @ColumnResult(name = "accountingDate", type = LocalDate.class),
                        @ColumnResult(name = "invoiceNo", type = String.class),
                        @ColumnResult(name = "invoiceDate", type = LocalDate.class),
                        @ColumnResult(name = "debitAccount", type = String.class),
                        @ColumnResult(name = "creditAccount", type = String.class),
                        @ColumnResult(name = "currencyType", type = String.class),
                        @ColumnResult(name = "currency", type = BigDecimal.class),
                        @ColumnResult(name = "materialGoodCode", type = String.class),
                        @ColumnResult(name = "materialGoodName", type = String.class),
                        @ColumnResult(name = "storageIn", type = String.class),
                        @ColumnResult(name = "storageOut", type = String.class),
                        @ColumnResult(name = "caculationUnit", type = String.class),
                        @ColumnResult(name = "amount", type = Float.class),
                        @ColumnResult(name = "price", type = BigDecimal.class),
                        @ColumnResult(name = "tranferRate", type = BigDecimal.class),
                        @ColumnResult(name = "moneyTranfer", type = BigDecimal.class),
                        @ColumnResult(name = "fixedAssetsType", type = String.class),
                        @ColumnResult(name = "fixedAssetsCode", type = String.class),
                        @ColumnResult(name = "toolsCode", type = String.class),
                        @ColumnResult(name = "debitObject", type = String.class),
                        @ColumnResult(name = "creditObject", type = String.class),
                        @ColumnResult(name = "unit", type = String.class),
                        @ColumnResult(name = "employee", type = String.class),
                        @ColumnResult(name = "bankAccount", type = String.class),
                        @ColumnResult(name = "itemCost", type = String.class),
                        @ColumnResult(name = "construction", type = String.class),
                        @ColumnResult(name = "costSet", type = String.class),
                        @ColumnResult(name = "purchaseOrder", type = String.class),
                        @ColumnResult(name = "buyOrder", type = String.class),
                        @ColumnResult(name = "purchaseContract", type = String.class),
                        @ColumnResult(name = "saleContract", type = String.class),
                        @ColumnResult(name = "statsCode", type = String.class),
                        @ColumnResult(name = "explanation", type = String.class),
                        @ColumnResult(name = "explanationDetail", type = String.class),
                        @ColumnResult(name = "recordStatus", type = String.class),
                        @ColumnResult(name = "createdDate", type = LocalDate.class),
                        @ColumnResult(name = "keyUUID", type = String.class),
                        @ColumnResult(name = "serialInvoice", type = String.class),
                        @ColumnResult(name = "currencyTax", type = BigDecimal.class),
                        @ColumnResult(name = "taxPercent", type = Integer.class),
                        @ColumnResult(name = "debitAccountTax", type = String.class),
                        @ColumnResult(name = "creditAccountTax", type = String.class),
                        @ColumnResult(name = "debitAccountXK", type = String.class),
                        @ColumnResult(name = "creditAccountXK", type = String.class),
                        @ColumnResult(name = "currencyXK", type = BigDecimal.class),
                        @ColumnResult(name = "priceXK", type = BigDecimal.class),
                        @ColumnResult(name = "voucherNoXK", type = String.class),
                    }
                ),
            }
        ),
        @SqlResultSetMapping(
            name = "SyntheticDTO",
            classes = {
                @ConstructorResult(
                    targetClass = SyntheticDTO.class,
                    columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "voucherType", type = String.class),
                        @ColumnResult(name = "voucherTypeNo", type = Integer.class),
                        @ColumnResult(name = "voucherNo", type = String.class),
                        @ColumnResult(name = "voucherDate", type = LocalDate.class),
                        @ColumnResult(name = "accountingDate", type = LocalDate.class),
                        @ColumnResult(name = "invoiceNo", type = String.class),
                        @ColumnResult(name = "invoiceDate", type = LocalDate.class),
                        @ColumnResult(name = "debitAccount", type = String.class),
                        @ColumnResult(name = "creditAccount", type = String.class),
                        @ColumnResult(name = "currencyType", type = String.class),
                        @ColumnResult(name = "currency", type = BigDecimal.class),
                        @ColumnResult(name = "materialGoodCode", type = String.class),
                        @ColumnResult(name = "materialGoodName", type = String.class),
                        @ColumnResult(name = "storageIn", type = String.class),
                        @ColumnResult(name = "storageOut", type = String.class),
                        @ColumnResult(name = "caculationUnit", type = String.class),
                        @ColumnResult(name = "amount", type = Float.class),
                        @ColumnResult(name = "price", type = BigDecimal.class),
                        @ColumnResult(name = "tranferRate", type = BigDecimal.class),
                        @ColumnResult(name = "moneyTranfer", type = BigDecimal.class),
                        @ColumnResult(name = "fixedAssetsType", type = String.class),
                        @ColumnResult(name = "fixedAssetsCode", type = String.class),
                        @ColumnResult(name = "toolsCode", type = String.class),
                        @ColumnResult(name = "debitObject", type = String.class),
                        @ColumnResult(name = "creditObject", type = String.class),
                        @ColumnResult(name = "unit", type = String.class),
                        @ColumnResult(name = "employee", type = String.class),
                        @ColumnResult(name = "bankAccount", type = String.class),
                        @ColumnResult(name = "itemCost", type = String.class),
                        @ColumnResult(name = "construction", type = String.class),
                        @ColumnResult(name = "costSet", type = String.class),
                        @ColumnResult(name = "purchaseOrder", type = String.class),
                        @ColumnResult(name = "buyOrder", type = String.class),
                        @ColumnResult(name = "purchaseContract", type = String.class),
                        @ColumnResult(name = "saleContract", type = String.class),
                        @ColumnResult(name = "statsCode", type = String.class),
                        @ColumnResult(name = "explanation", type = String.class),
                        @ColumnResult(name = "explanationDetail", type = String.class),
                        @ColumnResult(name = "recordStatus", type = String.class),
                        @ColumnResult(name = "createdDate", type = LocalDate.class),
                        @ColumnResult(name = "keyUUID", type = String.class),
                        @ColumnResult(name = "serialInvoice", type = String.class),
                        @ColumnResult(name = "currencyTax", type = BigDecimal.class),
                        @ColumnResult(name = "taxPercent", type = Integer.class),
                        @ColumnResult(name = "debitAccountTax", type = String.class),
                        @ColumnResult(name = "creditAccountTax", type = String.class),
                    }
                ),
            }
        ),
    }
)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Synthetic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "voucherType")
    private String voucherType;

    @Column(name = "voucherTypeNo")
    private Integer voucherTypeNo;

    @Column(name = "voucherNo")
    private String voucherNo;

    @Column(name = "voucherDate")
    private LocalDate voucherDate;

    @Column(name = "accountingDate")
    private LocalDate accountingDate;

    @Column(name = "invoiceNo")
    private String invoiceNo;

    @Column(name = "invoiceDate")
    private LocalDate invoiceDate;

    @Column(name = "debitAccount")
    private String debitAccount;

    @Column(name = "creditAccount")
    private String creditAccount;

    @Column(name = "currencyType")
    private String currencyType;

    @Column(name = "currency")
    private BigDecimal currency;

    @Column(name = "materialGoodCode")
    private String materialGoodCode;

    @Column(name = "materialGoodName")
    private String materialGoodName;

    @Column(name = "storageIn")
    private String storageIn;

    @Column(name = "storageOut")
    private String storageOut;

    @Column(name = "caculationUnit")
    private String caculationUnit;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "tranferRate")
    private BigDecimal tranferRate;

    @Column(name = "moneyTranfer")
    private BigDecimal moneyTranfer;

    @Column(name = "fixedAssetsType")
    private String fixedAssetsType;

    @Column(name = "fixedAssetsCode")
    private String fixedAssetsCode;

    @Column(name = "toolsCode")
    private String toolsCode;

    @Column(name = "debitObject")
    private String debitObject;

    @Column(name = "creditObject")
    private String creditObject;

    @Column(name = "unit")
    private String unit;

    @Column(name = "employee")
    private String employee;

    @Column(name = "bankAccount")
    private String bankAccount;

    @Column(name = "itemCost")
    private String itemCost;

    @Column(name = "construction")
    private String construction;

    @Column(name = "costSet")
    private String costSet;

    @Column(name = "purchaseOrder")
    private String purchaseOrder;

    @Column(name = "buyOrder")
    private String buyOrder;

    @Column(name = "purchaseContract")
    private String purchaseContract;

    @Column(name = "saleContract")
    private String saleContract;

    @Column(name = "statsCode")
    private String statsCode;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "explanationDetail")
    private String explanationDetail;

    @Column(name = "recordStatus")
    private String recordStatus;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    @Column(name = "keyUUID")
    private String keyUUID;

    @Column(name = "serialInvoice")
    private String serialInvoice;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Synthetic id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoucherType() {
        return this.voucherType;
    }

    public Synthetic voucherType(String voucherType) {
        this.setVoucherType(voucherType);
        return this;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public Integer getVoucherTypeNo() {
        return this.voucherTypeNo;
    }

    public Synthetic voucherTypeNo(Integer voucherTypeNo) {
        this.setVoucherTypeNo(voucherTypeNo);
        return this;
    }

    public void setVoucherTypeNo(Integer voucherTypeNo) {
        this.voucherTypeNo = voucherTypeNo;
    }

    public String getVoucherNo() {
        return this.voucherNo;
    }

    public Synthetic voucherNo(String voucherNo) {
        this.setVoucherNo(voucherNo);
        return this;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public LocalDate getVoucherDate() {
        return this.voucherDate;
    }

    public Synthetic voucherDate(LocalDate voucherDate) {
        this.setVoucherDate(voucherDate);
        return this;
    }

    public void setVoucherDate(LocalDate voucherDate) {
        this.voucherDate = voucherDate;
    }

    public LocalDate getAccountingDate() {
        return this.accountingDate;
    }

    public Synthetic accountingDate(LocalDate accountingDate) {
        this.setAccountingDate(accountingDate);
        return this;
    }

    public void setAccountingDate(LocalDate accountingDate) {
        this.accountingDate = accountingDate;
    }

    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    public Synthetic invoiceNo(String invoiceNo) {
        this.setInvoiceNo(invoiceNo);
        return this;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public LocalDate getInvoiceDate() {
        return this.invoiceDate;
    }

    public Synthetic invoiceDate(LocalDate invoiceDate) {
        this.setInvoiceDate(invoiceDate);
        return this;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getDebitAccount() {
        return this.debitAccount;
    }

    public Synthetic debitAccount(String debitAccount) {
        this.setDebitAccount(debitAccount);
        return this;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getCreditAccount() {
        return this.creditAccount;
    }

    public Synthetic creditAccount(String creditAccount) {
        this.setCreditAccount(creditAccount);
        return this;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getCurrencyType() {
        return this.currencyType;
    }

    public Synthetic currencyType(String currencyType) {
        this.setCurrencyType(currencyType);
        return this;
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

    public Synthetic currency(BigDecimal currency) {
        this.setCurrency(currency);
        return this;
    }

    public String getMaterialGoodCode() {
        return this.materialGoodCode;
    }

    public Synthetic materialGoodCode(String materialGoodCode) {
        this.setMaterialGoodCode(materialGoodCode);
        return this;
    }

    public void setMaterialGoodCode(String materialGoodCode) {
        this.materialGoodCode = materialGoodCode;
    }

    public String getMaterialGoodName() {
        return this.materialGoodName;
    }

    public Synthetic materialGoodName(String materialGoodName) {
        this.setMaterialGoodName(materialGoodName);
        return this;
    }

    public void setMaterialGoodName(String materialGoodName) {
        this.materialGoodName = materialGoodName;
    }

    public String getStorageIn() {
        return this.storageIn;
    }

    public Synthetic storageIn(String storageIn) {
        this.setStorageIn(storageIn);
        return this;
    }

    public void setStorageIn(String storageIn) {
        this.storageIn = storageIn;
    }

    public String getStorageOut() {
        return this.storageOut;
    }

    public Synthetic storageOut(String storageOut) {
        this.setStorageOut(storageOut);
        return this;
    }

    public void setStorageOut(String storageOut) {
        this.storageOut = storageOut;
    }

    public String getCaculationUnit() {
        return this.caculationUnit;
    }

    public Synthetic caculationUnit(String caculationUnit) {
        this.setCaculationUnit(caculationUnit);
        return this;
    }

    public void setCaculationUnit(String caculationUnit) {
        this.caculationUnit = caculationUnit;
    }

    public Float getAmount() {
        return this.amount;
    }

    public Synthetic amount(Float amount) {
        this.setAmount(amount);
        return this;
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

    public Synthetic price(BigDecimal price) {
        this.setPrice(price);
        return this;
    }

    public BigDecimal getTranferRate() {
        return tranferRate;
    }

    public void setTranferRate(BigDecimal tranferRate) {
        this.tranferRate = tranferRate;
    }

    public Synthetic tranferRate(BigDecimal tranferRate) {
        this.setTranferRate(tranferRate);
        return this;
    }

    public BigDecimal getMoneyTranfer() {
        return moneyTranfer;
    }

    public void setMoneyTranfer(BigDecimal moneyTranfer) {
        this.moneyTranfer = moneyTranfer;
    }

    public Synthetic moneyTranfer(BigDecimal moneyTranfer) {
        this.setMoneyTranfer(moneyTranfer);
        return this;
    }

    public String getFixedAssetsType() {
        return this.fixedAssetsType;
    }

    public Synthetic fixedAssetsType(String fixedAssetsType) {
        this.setFixedAssetsType(fixedAssetsType);
        return this;
    }

    public void setFixedAssetsType(String fixedAssetsType) {
        this.fixedAssetsType = fixedAssetsType;
    }

    public String getFixedAssetsCode() {
        return this.fixedAssetsCode;
    }

    public Synthetic fixedAssetsCode(String fixedAssetsCode) {
        this.setFixedAssetsCode(fixedAssetsCode);
        return this;
    }

    public void setFixedAssetsCode(String fixedAssetsCode) {
        this.fixedAssetsCode = fixedAssetsCode;
    }

    public String getToolsCode() {
        return this.toolsCode;
    }

    public Synthetic toolsCode(String toolsCode) {
        this.setToolsCode(toolsCode);
        return this;
    }

    public void setToolsCode(String toolsCode) {
        this.toolsCode = toolsCode;
    }

    public String getDebitObject() {
        return this.debitObject;
    }

    public Synthetic debitObject(String debitObject) {
        this.setDebitObject(debitObject);
        return this;
    }

    public void setDebitObject(String debitObject) {
        this.debitObject = debitObject;
    }

    public String getCreditObject() {
        return this.creditObject;
    }

    public Synthetic creditObject(String creditObject) {
        this.setCreditObject(creditObject);
        return this;
    }

    public void setCreditObject(String creditObject) {
        this.creditObject = creditObject;
    }

    public String getUnit() {
        return this.unit;
    }

    public Synthetic unit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getEmployee() {
        return this.employee;
    }

    public Synthetic employee(String employee) {
        this.setEmployee(employee);
        return this;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getBankAccount() {
        return this.bankAccount;
    }

    public Synthetic bankAccount(String bankAccount) {
        this.setBankAccount(bankAccount);
        return this;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getItemCost() {
        return this.itemCost;
    }

    public Synthetic itemCost(String itemCost) {
        this.setItemCost(itemCost);
        return this;
    }

    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }

    public String getConstruction() {
        return this.construction;
    }

    public Synthetic construction(String construction) {
        this.setConstruction(construction);
        return this;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public String getCostSet() {
        return this.costSet;
    }

    public Synthetic costSet(String costSet) {
        this.setCostSet(costSet);
        return this;
    }

    public void setCostSet(String costSet) {
        this.costSet = costSet;
    }

    public String getPurchaseOrder() {
        return this.purchaseOrder;
    }

    public Synthetic purchaseOrder(String purchaseOrder) {
        this.setPurchaseOrder(purchaseOrder);
        return this;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getBuyOrder() {
        return this.buyOrder;
    }

    public Synthetic buyOrder(String buyOrder) {
        this.setBuyOrder(buyOrder);
        return this;
    }

    public void setBuyOrder(String buyOrder) {
        this.buyOrder = buyOrder;
    }

    public String getPurchaseContract() {
        return this.purchaseContract;
    }

    public Synthetic purchaseContract(String purchaseContract) {
        this.setPurchaseContract(purchaseContract);
        return this;
    }

    public void setPurchaseContract(String purchaseContract) {
        this.purchaseContract = purchaseContract;
    }

    public String getSaleContract() {
        return this.saleContract;
    }

    public Synthetic saleContract(String saleContract) {
        this.setSaleContract(saleContract);
        return this;
    }

    public void setSaleContract(String saleContract) {
        this.saleContract = saleContract;
    }

    public String getStatsCode() {
        return this.statsCode;
    }

    public Synthetic statsCode(String statsCode) {
        this.setStatsCode(statsCode);
        return this;
    }

    public void setStatsCode(String statsCode) {
        this.statsCode = statsCode;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public Synthetic explanation(String explanation) {
        this.setExplanation(explanation);
        return this;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanationDetail() {
        return this.explanationDetail;
    }

    public Synthetic explanationDetail(String explanationDetail) {
        this.setExplanationDetail(explanationDetail);
        return this;
    }

    public void setExplanationDetail(String explanationDetail) {
        this.explanationDetail = explanationDetail;
    }

    public String getRecordStatus() {
        return this.recordStatus;
    }

    public Synthetic recordStatus(String recordStatus) {
        this.setRecordStatus(recordStatus);
        return this;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Synthetic createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getKeyUUID() {
        return this.keyUUID;
    }

    public Synthetic keyUUID(String keyUUID) {
        this.setKeyUUID(keyUUID);
        return this;
    }

    public void setKeyUUID(String keyUUID) {
        this.keyUUID = keyUUID;
    }

    public String getSerialInvoice() {
        return serialInvoice;
    }

    public void setSerialInvoice(String serialInvoice) {
        this.serialInvoice = serialInvoice;
    }

    public Synthetic serialInvoice(String serialInvoice) {
        this.serialInvoice = serialInvoice;
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Synthetic)) {
            return false;
        }
        return id != null && id.equals(((Synthetic) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Synthetic{" +
            "id=" + getId() +
            ", voucherType='" + getVoucherType() + "'" +
            ", voucherTypeNo=" + getVoucherTypeNo() +
            ", voucherNo='" + getVoucherNo() + "'" +
            ", voucherDate='" + getVoucherDate() + "'" +
            ", accountingDate='" + getAccountingDate() + "'" +
            ", invoiceNo='" + getInvoiceNo() + "'" +
            ", invoiceDate='" + getInvoiceDate() + "'" +
            ", debitAccount='" + getDebitAccount() + "'" +
            ", creditAccount='" + getCreditAccount() + "'" +
            ", currencyType='" + getCurrencyType() + "'" +
            ", currency=" + getCurrency() +
            ", materialGoodCode='" + getMaterialGoodCode() + "'" +
            ", materialGoodName='" + getMaterialGoodName() + "'" +
            ", storageIn='" + getStorageIn() + "'" +
            ", storageOut='" + getStorageOut() + "'" +
            ", caculationUnit='" + getCaculationUnit() + "'" +
            ", amount=" + getAmount() +
            ", price=" + getPrice() +
            ", tranferRate=" + getTranferRate() +
            ", moneyTranfer=" + getMoneyTranfer() +
            ", fixedAssetsType='" + getFixedAssetsType() + "'" +
            ", fixedAssetsCode='" + getFixedAssetsCode() + "'" +
            ", toolsCode='" + getToolsCode() + "'" +
            ", debitObject='" + getDebitObject() + "'" +
            ", creditObject='" + getCreditObject() + "'" +
            ", unit='" + getUnit() + "'" +
            ", employee='" + getEmployee() + "'" +
            ", bankAccount='" + getBankAccount() + "'" +
            ", itemCost='" + getItemCost() + "'" +
            ", construction='" + getConstruction() + "'" +
            ", costSet='" + getCostSet() + "'" +
            ", purchaseOrder='" + getPurchaseOrder() + "'" +
            ", buyOrder='" + getBuyOrder() + "'" +
            ", purchaseContract='" + getPurchaseContract() + "'" +
            ", saleContract='" + getSaleContract() + "'" +
            ", statsCode='" + getStatsCode() + "'" +
            ", explanation='" + getExplanation() + "'" +
            ", explanationDetail='" + getExplanationDetail() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", keyUUID='" + getKeyUUID() + "'" +
            "}";
    }
}
