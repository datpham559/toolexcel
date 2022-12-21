package com.softdreams.excel.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Merchandise.
 */
@Entity
@Table(name = "merchandise")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Merchandise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "nature")
    private String nature;

    @Column(name = "group_vthh")
    private String group_vthh;

    @Column(name = "describe")
    private String describe;

    @Column(name = "explain_buy")
    private String explain_buy;

    @Column(name = "explain_sell")
    private String explain_sell;

    @Column(name = "main_dvt")
    private String main_dvt;

    @Column(name = "warranty_period")
    private String warranty_period;

    @Column(name = "quantity_inventory")
    private Double quantity_inventory;

    @Column(name = "source")
    private String source;

    @Column(name = "implicitly_repository")
    private String implicitly_repository;

    @Column(name = "warehouse_account")
    private String warehouse_account;

    @Column(name = "expense_account")
    private String expense_account;

    @Column(name = "income_account")
    private String income_account;

    @Column(name = "discount_account")
    private String discount_account;

    @Column(name = "sale_account")
    private String sale_account;

    @Column(name = "return_account")
    private String return_account;

    @Column(name = "rate_ckmh")
    private Double rate_ckmh;

    @Column(name = "fixed_purchase_price")
    private Double fixed_purchase_price;

    @Column(name = "latest_purchase_price")
    private Double latest_purchase_price;

    @Column(name = "unit_price_sell_1")
    private Double unit_price_sell_1;

    @Column(name = "unit_price_sell_2")
    private Double unit_price_sell_2;

    @Column(name = "unit_price_sell_3")
    private Double unit_price_sell_3;

    @Column(name = "fixed_unit_price")
    private Double fixed_unit_price;

    @Column(name = "unit_price_after_tax")
    private Double unit_price_after_tax;

    @Column(name = "tax_rate_gtgt")
    private String tax_rate_gtgt;

    @Column(name = "tax_rate_nk")
    private Double tax_rate_nk;

    @Column(name = "tax_rate_xk")
    private Double tax_rate_xk;

    @Column(name = "group_hhdv_taxable_ttdb")
    private String group_hhdv_taxable_ttdb;

    @Column(name = "unfollow")
    private Double unfollow;

    @Column(name = "inventory_number")
    private Double inventory_number;

    @Column(name = "characteristic")
    private String characteristic;

    @Column(name = "inventory_value")
    private Double inventory_value;

    @Column(name = "follow")
    private Double follow;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "from_amount")
    private String from_amount;

    @Column(name = "to_amount")
    private String to_amount;

    @Column(name = "percent_discount")
    private Double percent_discount;

    @Column(name = "discount_amount")
    private Double discount_amount;

    @Column(name = "conversion_unit")
    private String conversion_unit;

    @Column(name = "primary_unit_conversion_rate")
    private Double primary_unit_conversion_rate;

    @Column(name = "calculation")
    private String calculation;

    @Column(name = "describe_1")
    private String describe1;

    @Column(name = "unit_price_1")
    private Double unit_price_1;

    @Column(name = "unit_price_2")
    private Double unit_price_2;

    @Column(name = "unit_price_3")
    private Double unit_price_3;

    @Column(name = "fixed_unit_price_1")
    private Double fixed_unit_price1;

    @Column(name = "material_code")
    private String material_code;

    @Column(name = "material_name")
    private String material_name;

    @Column(name = "dvt")
    private String dvt;

    @Column(name = "amount")
    private String amount;

    @Column(name = "specification_code")
    private String specification_code;

    @Column(name = "display_name")
    private String display_name;

    @Column(name = "allow_same")
    private String allow_same;

    @Column(name = "created_date")
    private LocalDate created_date;

    @Column(name = "key_uuid")
    private String keyUUID;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Merchandise id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Merchandise code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Merchandise name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNature() {
        return this.nature;
    }

    public Merchandise nature(String nature) {
        this.setNature(nature);
        return this;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getGroup_vthh() {
        return this.group_vthh;
    }

    public Merchandise group_vthh(String group_vthh) {
        this.setGroup_vthh(group_vthh);
        return this;
    }

    public void setGroup_vthh(String group_vthh) {
        this.group_vthh = group_vthh;
    }

    public String getDescribe() {
        return this.describe;
    }

    public Merchandise describe(String describe) {
        this.setDescribe(describe);
        return this;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getExplain_buy() {
        return this.explain_buy;
    }

    public Merchandise explain_buy(String explain_buy) {
        this.setExplain_buy(explain_buy);
        return this;
    }

    public void setExplain_buy(String explain_buy) {
        this.explain_buy = explain_buy;
    }

    public String getExplain_sell() {
        return this.explain_sell;
    }

    public Merchandise explain_sell(String explain_sell) {
        this.setExplain_sell(explain_sell);
        return this;
    }

    public void setExplain_sell(String explain_sell) {
        this.explain_sell = explain_sell;
    }

    public String getMain_dvt() {
        return this.main_dvt;
    }

    public Merchandise main_dvt(String main_dvt) {
        this.setMain_dvt(main_dvt);
        return this;
    }

    public void setMain_dvt(String main_dvt) {
        this.main_dvt = main_dvt;
    }

    public String getWarranty_period() {
        return this.warranty_period;
    }

    public Merchandise warranty_period(String warranty_period) {
        this.setWarranty_period(warranty_period);
        return this;
    }

    public void setWarranty_period(String warranty_period) {
        this.warranty_period = warranty_period;
    }

    public Double getQuantity_inventory() {
        return this.quantity_inventory;
    }

    public Merchandise quantity_inventory(Double quantity_inventory) {
        this.setQuantity_inventory(quantity_inventory);
        return this;
    }

    public void setQuantity_inventory(Double quantity_inventory) {
        this.quantity_inventory = quantity_inventory;
    }

    public String getSource() {
        return this.source;
    }

    public Merchandise source(String source) {
        this.setSource(source);
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImplicitly_repository() {
        return this.implicitly_repository;
    }

    public Merchandise implicitly_repository(String implicitly_repository) {
        this.setImplicitly_repository(implicitly_repository);
        return this;
    }

    public void setImplicitly_repository(String implicitly_repository) {
        this.implicitly_repository = implicitly_repository;
    }

    public String getWarehouse_account() {
        return this.warehouse_account;
    }

    public Merchandise warehouse_account(String warehouse_account) {
        this.setWarehouse_account(warehouse_account);
        return this;
    }

    public void setWarehouse_account(String warehouse_account) {
        this.warehouse_account = warehouse_account;
    }

    public String getExpense_account() {
        return this.expense_account;
    }

    public Merchandise expense_account(String expense_account) {
        this.setExpense_account(expense_account);
        return this;
    }

    public void setExpense_account(String expense_account) {
        this.expense_account = expense_account;
    }

    public String getIncome_account() {
        return this.income_account;
    }

    public Merchandise income_account(String income_account) {
        this.setIncome_account(income_account);
        return this;
    }

    public void setIncome_account(String income_account) {
        this.income_account = income_account;
    }

    public String getDiscount_account() {
        return this.discount_account;
    }

    public Merchandise discount_account(String discount_account) {
        this.setDiscount_account(discount_account);
        return this;
    }

    public void setDiscount_account(String discount_account) {
        this.discount_account = discount_account;
    }

    public String getSale_account() {
        return this.sale_account;
    }

    public Merchandise sale_account(String sale_account) {
        this.setSale_account(sale_account);
        return this;
    }

    public void setSale_account(String sale_account) {
        this.sale_account = sale_account;
    }

    public String getReturn_account() {
        return this.return_account;
    }

    public Merchandise return_account(String return_account) {
        this.setReturn_account(return_account);
        return this;
    }

    public void setReturn_account(String return_account) {
        this.return_account = return_account;
    }

    public Double getRate_ckmh() {
        return this.rate_ckmh;
    }

    public Merchandise rate_ckmh(Double rate_ckmh) {
        this.setRate_ckmh(rate_ckmh);
        return this;
    }

    public void setRate_ckmh(Double rate_ckmh) {
        this.rate_ckmh = rate_ckmh;
    }

    public Double getFixed_purchase_price() {
        return this.fixed_purchase_price;
    }

    public Merchandise fixed_purchase_price(Double fixed_purchase_price) {
        this.setFixed_purchase_price(fixed_purchase_price);
        return this;
    }

    public void setFixed_purchase_price(Double fixed_purchase_price) {
        this.fixed_purchase_price = fixed_purchase_price;
    }

    public Double getLatest_purchase_price() {
        return this.latest_purchase_price;
    }

    public Merchandise latest_purchase_price(Double latest_purchase_price) {
        this.setLatest_purchase_price(latest_purchase_price);
        return this;
    }

    public void setLatest_purchase_price(Double latest_purchase_price) {
        this.latest_purchase_price = latest_purchase_price;
    }

    public Double getUnit_price_sell_1() {
        return this.unit_price_sell_1;
    }

    public Merchandise unit_price_sell_1(Double unit_price_sell_1) {
        this.setUnit_price_sell_1(unit_price_sell_1);
        return this;
    }

    public void setUnit_price_sell_1(Double unit_price_sell_1) {
        this.unit_price_sell_1 = unit_price_sell_1;
    }

    public Double getUnit_price_sell_2() {
        return this.unit_price_sell_2;
    }

    public Merchandise unit_price_sell_2(Double unit_price_sell_2) {
        this.setUnit_price_sell_2(unit_price_sell_2);
        return this;
    }

    public void setUnit_price_sell_2(Double unit_price_sell_2) {
        this.unit_price_sell_2 = unit_price_sell_2;
    }

    public Double getUnit_price_sell_3() {
        return this.unit_price_sell_3;
    }

    public Merchandise unit_price_sell_3(Double unit_price_sell_3) {
        this.setUnit_price_sell_3(unit_price_sell_3);
        return this;
    }

    public void setUnit_price_sell_3(Double unit_price_sell_3) {
        this.unit_price_sell_3 = unit_price_sell_3;
    }

    public Double getFixed_unit_price() {
        return this.fixed_unit_price;
    }

    public Merchandise fixed_unit_price(Double fixed_unit_price) {
        this.setFixed_unit_price(fixed_unit_price);
        return this;
    }

    public void setFixed_unit_price(Double fixed_unit_price) {
        this.fixed_unit_price = fixed_unit_price;
    }

    public Double getUnit_price_after_tax() {
        return this.unit_price_after_tax;
    }

    public Merchandise unit_price_after_tax(Double unit_price_after_tax) {
        this.setUnit_price_after_tax(unit_price_after_tax);
        return this;
    }

    public void setUnit_price_after_tax(Double unit_price_after_tax) {
        this.unit_price_after_tax = unit_price_after_tax;
    }

    public String getTax_rate_gtgt() {
        return this.tax_rate_gtgt;
    }

    public Merchandise tax_rate_gtgt(String tax_rate_gtgt) {
        this.setTax_rate_gtgt(tax_rate_gtgt);
        return this;
    }

    public void setTax_rate_gtgt(String tax_rate_gtgt) {
        this.tax_rate_gtgt = tax_rate_gtgt;
    }

    public Double getTax_rate_nk() {
        return this.tax_rate_nk;
    }

    public Merchandise tax_rate_nk(Double tax_rate_nk) {
        this.setTax_rate_nk(tax_rate_nk);
        return this;
    }

    public void setTax_rate_nk(Double tax_rate_nk) {
        this.tax_rate_nk = tax_rate_nk;
    }

    public Double getTax_rate_xk() {
        return this.tax_rate_xk;
    }

    public Merchandise tax_rate_xk(Double tax_rate_xk) {
        this.setTax_rate_xk(tax_rate_xk);
        return this;
    }

    public void setTax_rate_xk(Double tax_rate_xk) {
        this.tax_rate_xk = tax_rate_xk;
    }

    public String getGroup_hhdv_taxable_ttdb() {
        return this.group_hhdv_taxable_ttdb;
    }

    public Merchandise group_hhdv_taxable_ttdb(String group_hhdv_taxable_ttdb) {
        this.setGroup_hhdv_taxable_ttdb(group_hhdv_taxable_ttdb);
        return this;
    }

    public void setGroup_hhdv_taxable_ttdb(String group_hhdv_taxable_ttdb) {
        this.group_hhdv_taxable_ttdb = group_hhdv_taxable_ttdb;
    }

    public Double getUnfollow() {
        return this.unfollow;
    }

    public Merchandise unfollow(Double unfollow) {
        this.setUnfollow(unfollow);
        return this;
    }

    public void setUnfollow(Double unfollow) {
        this.unfollow = unfollow;
    }

    public Double getInventory_number() {
        return this.inventory_number;
    }

    public Merchandise inventory_number(Double inventory_number) {
        this.setInventory_number(inventory_number);
        return this;
    }

    public void setInventory_number(Double inventory_number) {
        this.inventory_number = inventory_number;
    }

    public String getCharacteristic() {
        return this.characteristic;
    }

    public Merchandise characteristic(String characteristic) {
        this.setCharacteristic(characteristic);
        return this;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public Double getInventory_value() {
        return this.inventory_value;
    }

    public Merchandise inventory_value(Double inventory_value) {
        this.setInventory_value(inventory_value);
        return this;
    }

    public void setInventory_value(Double inventory_value) {
        this.inventory_value = inventory_value;
    }

    public Double getFollow() {
        return this.follow;
    }

    public Merchandise follow(Double follow) {
        this.setFollow(follow);
        return this;
    }

    public void setFollow(Double follow) {
        this.follow = follow;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public Merchandise discount(Double discount) {
        this.setDiscount(discount);
        return this;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getFrom_amount() {
        return this.from_amount;
    }

    public Merchandise from_amount(String from_amount) {
        this.setFrom_amount(from_amount);
        return this;
    }

    public void setFrom_amount(String from_amount) {
        this.from_amount = from_amount;
    }

    public String getTo_amount() {
        return this.to_amount;
    }

    public Merchandise to_amount(String to_amount) {
        this.setTo_amount(to_amount);
        return this;
    }

    public void setTo_amount(String to_amount) {
        this.to_amount = to_amount;
    }

    public Double getPercent_discount() {
        return this.percent_discount;
    }

    public Merchandise percent_discount(Double percent_discount) {
        this.setPercent_discount(percent_discount);
        return this;
    }

    public void setPercent_discount(Double percent_discount) {
        this.percent_discount = percent_discount;
    }

    public Double getDiscount_amount() {
        return this.discount_amount;
    }

    public Merchandise discount_amount(Double discount_amount) {
        this.setDiscount_amount(discount_amount);
        return this;
    }

    public void setDiscount_amount(Double discount_amount) {
        this.discount_amount = discount_amount;
    }

    public String getConversion_unit() {
        return this.conversion_unit;
    }

    public Merchandise conversion_unit(String conversion_unit) {
        this.setConversion_unit(conversion_unit);
        return this;
    }

    public void setConversion_unit(String conversion_unit) {
        this.conversion_unit = conversion_unit;
    }

    public Double getPrimary_unit_conversion_rate() {
        return this.primary_unit_conversion_rate;
    }

    public Merchandise primary_unit_conversion_rate(Double primary_unit_conversion_rate) {
        this.setPrimary_unit_conversion_rate(primary_unit_conversion_rate);
        return this;
    }

    public void setPrimary_unit_conversion_rate(Double primary_unit_conversion_rate) {
        this.primary_unit_conversion_rate = primary_unit_conversion_rate;
    }

    public String getCalculation() {
        return this.calculation;
    }

    public Merchandise calculation(String calculation) {
        this.setCalculation(calculation);
        return this;
    }

    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }

    public String getDescribe1() {
        return this.describe1;
    }

    public Merchandise describe1(String describe1) {
        this.setDescribe1(describe1);
        return this;
    }

    public void setDescribe1(String describe1) {
        this.describe1 = describe1;
    }

    public Double getUnit_price_1() {
        return this.unit_price_1;
    }

    public Merchandise unit_price_1(Double unit_price_1) {
        this.setUnit_price_1(unit_price_1);
        return this;
    }

    public void setUnit_price_1(Double unit_price_1) {
        this.unit_price_1 = unit_price_1;
    }

    public Double getUnit_price_2() {
        return this.unit_price_2;
    }

    public Merchandise unit_price_2(Double unit_price_2) {
        this.setUnit_price_2(unit_price_2);
        return this;
    }

    public void setUnit_price_2(Double unit_price_2) {
        this.unit_price_2 = unit_price_2;
    }

    public Double getUnit_price_3() {
        return this.unit_price_3;
    }

    public Merchandise unit_price_3(Double unit_price_3) {
        this.setUnit_price_3(unit_price_3);
        return this;
    }

    public void setUnit_price_3(Double unit_price_3) {
        this.unit_price_3 = unit_price_3;
    }

    public Double getFixed_unit_price1() {
        return this.fixed_unit_price1;
    }

    public Merchandise fixed_unit_price1(Double fixed_unit_price1) {
        this.setFixed_unit_price1(fixed_unit_price1);
        return this;
    }

    public void setFixed_unit_price1(Double fixed_unit_price1) {
        this.fixed_unit_price1 = fixed_unit_price1;
    }

    public String getMaterial_code() {
        return this.material_code;
    }

    public Merchandise material_code(String material_code) {
        this.setMaterial_code(material_code);
        return this;
    }

    public void setMaterial_code(String material_code) {
        this.material_code = material_code;
    }

    public String getMaterial_name() {
        return this.material_name;
    }

    public Merchandise material_name(String material_name) {
        this.setMaterial_name(material_name);
        return this;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getDvt() {
        return this.dvt;
    }

    public Merchandise dvt(String dvt) {
        this.setDvt(dvt);
        return this;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public String getAmount() {
        return this.amount;
    }

    public Merchandise amount(String amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSpecification_code() {
        return this.specification_code;
    }

    public Merchandise specification_code(String specification_code) {
        this.setSpecification_code(specification_code);
        return this;
    }

    public void setSpecification_code(String specification_code) {
        this.specification_code = specification_code;
    }

    public String getDisplay_name() {
        return this.display_name;
    }

    public Merchandise display_name(String display_name) {
        this.setDisplay_name(display_name);
        return this;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getAllow_same() {
        return this.allow_same;
    }

    public Merchandise allow_same(String allow_same) {
        this.setAllow_same(allow_same);
        return this;
    }

    public void setAllow_same(String allow_same) {
        this.allow_same = allow_same;
    }

    public LocalDate getCreated_date() {
        return this.created_date;
    }

    public Merchandise created_date(LocalDate created_date) {
        this.setCreated_date(created_date);
        return this;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public String getKeyUUID() {
        return this.keyUUID;
    }

    public Merchandise keyUUID(String keyUUID) {
        this.setKeyUUID(keyUUID);
        return this;
    }

    public void setKeyUUID(String keyUUID) {
        this.keyUUID = keyUUID;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Merchandise)) {
            return false;
        }
        return id != null && id.equals(((Merchandise) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Merchandise{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", nature='" + getNature() + "'" +
            ", group_vthh='" + getGroup_vthh() + "'" +
            ", describe='" + getDescribe() + "'" +
            ", explain_buy='" + getExplain_buy() + "'" +
            ", explain_sell='" + getExplain_sell() + "'" +
            ", main_dvt='" + getMain_dvt() + "'" +
            ", warranty_period='" + getWarranty_period() + "'" +
            ", quantity_inventory=" + getQuantity_inventory() +
            ", source='" + getSource() + "'" +
            ", implicitly_repository='" + getImplicitly_repository() + "'" +
            ", warehouse_account='" + getWarehouse_account() + "'" +
            ", expense_account='" + getExpense_account() + "'" +
            ", income_account='" + getIncome_account() + "'" +
            ", discount_account='" + getDiscount_account() + "'" +
            ", sale_account='" + getSale_account() + "'" +
            ", return_account='" + getReturn_account() + "'" +
            ", rate_ckmh=" + getRate_ckmh() +
            ", fixed_purchase_price=" + getFixed_purchase_price() +
            ", latest_purchase_price=" + getLatest_purchase_price() +
            ", unit_price_sell_1=" + getUnit_price_sell_1() +
            ", unit_price_sell_2=" + getUnit_price_sell_2() +
            ", unit_price_sell_3=" + getUnit_price_sell_3() +
            ", fixed_unit_price=" + getFixed_unit_price() +
            ", unit_price_after_tax=" + getUnit_price_after_tax() +
            ", tax_rate_gtgt='" + getTax_rate_gtgt() + "'" +
            ", tax_rate_nk=" + getTax_rate_nk() +
            ", tax_rate_xk=" + getTax_rate_xk() +
            ", group_hhdv_taxable_ttdb='" + getGroup_hhdv_taxable_ttdb() + "'" +
            ", unfollow=" + getUnfollow() +
            ", inventory_number=" + getInventory_number() +
            ", characteristic='" + getCharacteristic() + "'" +
            ", inventory_value=" + getInventory_value() +
            ", follow=" + getFollow() +
            ", discount=" + getDiscount() +
            ", from_amount='" + getFrom_amount() + "'" +
            ", to_amount='" + getTo_amount() + "'" +
            ", percent_discount=" + getPercent_discount() +
            ", discount_amount=" + getDiscount_amount() +
            ", conversion_unit='" + getConversion_unit() + "'" +
            ", primary_unit_conversion_rate=" + getPrimary_unit_conversion_rate() +
            ", calculation='" + getCalculation() + "'" +
            ", describe1='" + getDescribe1() + "'" +
            ", unit_price_1=" + getUnit_price_1() +
            ", unit_price_2=" + getUnit_price_2() +
            ", unit_price_3=" + getUnit_price_3() +
            ", fixed_unit_price1=" + getFixed_unit_price1() +
            ", material_code='" + getMaterial_code() + "'" +
            ", material_name='" + getMaterial_name() + "'" +
            ", dvt='" + getDvt() + "'" +
            ", amount='" + getAmount() + "'" +
            ", specification_code='" + getSpecification_code() + "'" +
            ", display_name='" + getDisplay_name() + "'" +
            ", allow_same='" + getAllow_same() + "'" +
            ", created_date='" + getCreated_date() + "'" +
            ", keyUUID='" + getKeyUUID() + "'" +
            "}";
    }
}
