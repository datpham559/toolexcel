package com.softdreams.excel.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "customerCode")
    private String customerCode;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "address")
    private String address;

    @Column(name = "customerGroup")
    private String customerGroup;

    @Column(name = "tax")
    private String tax;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "unfollow")
    private Boolean unfollow;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    @Column(name = "keyUUID")
    private String keyUUID;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Customer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public Customer customerCode(String customerCode) {
        this.setCustomerCode(customerCode);
        return this;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public Customer customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return this.address;
    }

    public Customer address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerGroup() {
        return this.customerGroup;
    }

    public Customer customerGroup(String customerGroup) {
        this.setCustomerGroup(customerGroup);
        return this;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public String getTax() {
        return this.tax;
    }

    public Customer tax(String tax) {
        this.setTax(tax);
        return this;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Customer phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getUnfollow() {
        return unfollow;
    }

    public void setUnfollow(Boolean unfollow) {
        this.unfollow = unfollow;
    }

    public Customer unfollow(Boolean unfollow) {
        this.setUnfollow(unfollow);
        return this;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Customer createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getKeyUUID() {
        return this.keyUUID;
    }

    public Customer keyUUID(String keyUUID) {
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
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", customerCode='" + getCustomerCode() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", address='" + getAddress() + "'" +
            ", customerGroup='" + getCustomerGroup() + "'" +
            ", tax='" + getTax() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", unfollow='" + getUnfollow() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", keyUUID='" + getKeyUUID() + "'" +
            "}";
    }
}
