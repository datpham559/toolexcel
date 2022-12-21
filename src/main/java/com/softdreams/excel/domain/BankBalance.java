package com.softdreams.excel.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A BankBalance.
 */
@Entity
@Table(name = "bank_balance")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BankBalance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bank_account")
    private String bank_account;

    @Column(name = "bank_name")
    private String bank_name;

    @Column(name = "branch")
    private String branch;

    @Column(name = "opening_balance")
    private Double opening_balance;

    @Column(name = "debt_incurred")
    private Double debt_incurred;

    @Column(name = "incurred")
    private Double incurred;

    @Column(name = "ending_balance")
    private Double ending_balance;

    @Column(name = "created_date")
    private LocalDate created_date;

    @Column(name = "key_uuid")
    private String keyUUID;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BankBalance id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBank_account() {
        return this.bank_account;
    }

    public BankBalance bank_account(String bank_account) {
        this.setBank_account(bank_account);
        return this;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getBank_name() {
        return this.bank_name;
    }

    public BankBalance bank_name(String bank_name) {
        this.setBank_name(bank_name);
        return this;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBranch() {
        return this.branch;
    }

    public BankBalance branch(String branch) {
        this.setBranch(branch);
        return this;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Double getOpening_balance() {
        return this.opening_balance;
    }

    public BankBalance opening_balance(Double opening_balance) {
        this.setOpening_balance(opening_balance);
        return this;
    }

    public void setOpening_balance(Double opening_balance) {
        this.opening_balance = opening_balance;
    }

    public Double getDebt_incurred() {
        return this.debt_incurred;
    }

    public BankBalance debt_incurred(Double debt_incurred) {
        this.setDebt_incurred(debt_incurred);
        return this;
    }

    public void setDebt_incurred(Double debt_incurred) {
        this.debt_incurred = debt_incurred;
    }

    public Double getIncurred() {
        return this.incurred;
    }

    public BankBalance incurred(Double incurred) {
        this.setIncurred(incurred);
        return this;
    }

    public void setIncurred(Double incurred) {
        this.incurred = incurred;
    }

    public Double getEnding_balance() {
        return this.ending_balance;
    }

    public BankBalance ending_balance(Double ending_balance) {
        this.setEnding_balance(ending_balance);
        return this;
    }

    public void setEnding_balance(Double ending_balance) {
        this.ending_balance = ending_balance;
    }

    public LocalDate getCreated_date() {
        return this.created_date;
    }

    public BankBalance created_date(LocalDate created_date) {
        this.setCreated_date(created_date);
        return this;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public String getKeyUUID() {
        return this.keyUUID;
    }

    public BankBalance keyUUID(String keyUUID) {
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
        if (!(o instanceof BankBalance)) {
            return false;
        }
        return id != null && id.equals(((BankBalance) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankBalance{" +
            "id=" + getId() +
            ", bank_account='" + getBank_account() + "'" +
            ", bank_name='" + getBank_name() + "'" +
            ", branch='" + getBranch() + "'" +
            ", opening_balance=" + getOpening_balance() +
            ", debt_incurred=" + getDebt_incurred() +
            ", incurred=" + getIncurred() +
            ", ending_balance=" + getEnding_balance() +
            ", created_date='" + getCreated_date() + "'" +
            ", keyUUID='" + getKeyUUID() + "'" +
            "}";
    }
}
