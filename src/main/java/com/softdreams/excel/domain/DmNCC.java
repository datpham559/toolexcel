package com.softdreams.excel.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A DmNCC.
 */
@Entity
@Table(name = "dmncc")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DmNCC implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "supplier_code")
    private String supplier_code;

    @Column(name = "supplier_name")
    private String supplier_name;

    @Column(name = "address")
    private String address;

    @Column(name = "group_kh_ncc")
    private String group_kh_ncc;

    @Column(name = "tax_code")
    private String tax_code;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "unfollow")
    private Boolean unfollow;

    @Column(name = "created_date")
    private LocalDate created_date;

    @Column(name = "key_uuid")
    private String keyUUID;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DmNCC id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplier_code() {
        return this.supplier_code;
    }

    public DmNCC supplier_code(String supplier_code) {
        this.setSupplier_code(supplier_code);
        return this;
    }

    public void setSupplier_code(String supplier_code) {
        this.supplier_code = supplier_code;
    }

    public String getSupplier_name() {
        return this.supplier_name;
    }

    public DmNCC supplier_name(String supplier_name) {
        this.setSupplier_name(supplier_name);
        return this;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getAddress() {
        return this.address;
    }

    public DmNCC address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGroup_kh_ncc() {
        return this.group_kh_ncc;
    }

    public DmNCC group_kh_ncc(String group_kh_ncc) {
        this.setGroup_kh_ncc(group_kh_ncc);
        return this;
    }

    public void setGroup_kh_ncc(String group_kh_ncc) {
        this.group_kh_ncc = group_kh_ncc;
    }

    public String getTax_code() {
        return this.tax_code;
    }

    public DmNCC tax_code(String tax_code) {
        this.setTax_code(tax_code);
        return this;
    }

    public void setTax_code(String tax_code) {
        this.tax_code = tax_code;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public DmNCC phone_number(String phone_number) {
        this.setPhone_number(phone_number);
        return this;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Boolean getUnfollow() {
        return this.unfollow;
    }

    public DmNCC unfollow(Boolean unfollow) {
        this.setUnfollow(unfollow);
        return this;
    }

    public void setUnfollow(Boolean unfollow) {
        this.unfollow = unfollow;
    }

    public LocalDate getCreated_date() {
        return this.created_date;
    }

    public DmNCC created_date(LocalDate created_date) {
        this.setCreated_date(created_date);
        return this;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public String getKeyUUID() {
        return this.keyUUID;
    }

    public DmNCC keyUUID(String keyUUID) {
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
        if (!(o instanceof DmNCC)) {
            return false;
        }
        return id != null && id.equals(((DmNCC) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmNCC{" +
            "id=" + getId() +
            ", supplier_code='" + getSupplier_code() + "'" +
            ", supplier_name='" + getSupplier_name() + "'" +
            ", address='" + getAddress() + "'" +
            ", group_kh_ncc='" + getGroup_kh_ncc() + "'" +
            ", tax_code='" + getTax_code() + "'" +
            ", phone_number='" + getPhone_number() + "'" +
            ", unfollow='" + getUnfollow() + "'" +
            ", created_date='" + getCreated_date() + "'" +
            ", keyUUID='" + getKeyUUID() + "'" +
            "}";
    }
}
