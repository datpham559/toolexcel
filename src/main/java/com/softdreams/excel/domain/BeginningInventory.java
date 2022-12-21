package com.softdreams.excel.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A BeginningInventory.
 */
@Entity
@Table(name = "beginning_inventory")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BeginningInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number")
    private String account_number;

    @Column(name = "account_name")
    private String account_name;

    @Column(name = "first_debt")
    private Double first_debt;

    @Column(name = "first_yes")
    private Double first_yes;

    @Column(name = "debt_arises")
    private Double debt_arises;

    @Column(name = "arises_yes")
    private Double arises_yes;

    @Column(name = "accumulated_debt")
    private Double accumulated_debt;

    @Column(name = "accumulated_yes")
    private Double accumulated_yes;

    @Column(name = "last_debt")
    private Double last_debt;

    @Column(name = "last_yes")
    private Double last_yes;

    @Column(name = "created_date")
    private LocalDate created_date;

    @Column(name = "key_uuid")
    private String keyUUID;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BeginningInventory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount_number() {
        return this.account_number;
    }

    public BeginningInventory account_number(String account_number) {
        this.setAccount_number(account_number);
        return this;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAccount_name() {
        return this.account_name;
    }

    public BeginningInventory account_name(String account_name) {
        this.setAccount_name(account_name);
        return this;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public Double getFirst_debt() {
        return this.first_debt;
    }

    public BeginningInventory first_debt(Double first_debt) {
        this.setFirst_debt(first_debt);
        return this;
    }

    public void setFirst_debt(Double first_debt) {
        this.first_debt = first_debt;
    }

    public Double getFirst_yes() {
        return this.first_yes;
    }

    public BeginningInventory first_yes(Double first_yes) {
        this.setFirst_yes(first_yes);
        return this;
    }

    public void setFirst_yes(Double first_yes) {
        this.first_yes = first_yes;
    }

    public Double getDebt_arises() {
        return this.debt_arises;
    }

    public BeginningInventory debt_arises(Double debt_arises) {
        this.setDebt_arises(debt_arises);
        return this;
    }

    public void setDebt_arises(Double debt_arises) {
        this.debt_arises = debt_arises;
    }

    public Double getArises_yes() {
        return this.arises_yes;
    }

    public BeginningInventory arises_yes(Double arises_yes) {
        this.setArises_yes(arises_yes);
        return this;
    }

    public void setArises_yes(Double arises_yes) {
        this.arises_yes = arises_yes;
    }

    public Double getAccumulated_debt() {
        return this.accumulated_debt;
    }

    public BeginningInventory accumulated_debt(Double accumulated_debt) {
        this.setAccumulated_debt(accumulated_debt);
        return this;
    }

    public void setAccumulated_debt(Double accumulated_debt) {
        this.accumulated_debt = accumulated_debt;
    }

    public Double getAccumulated_yes() {
        return this.accumulated_yes;
    }

    public BeginningInventory accumulated_yes(Double accumulated_yes) {
        this.setAccumulated_yes(accumulated_yes);
        return this;
    }

    public void setAccumulated_yes(Double accumulated_yes) {
        this.accumulated_yes = accumulated_yes;
    }

    public Double getLast_debt() {
        return this.last_debt;
    }

    public BeginningInventory last_debt(Double last_debt) {
        this.setLast_debt(last_debt);
        return this;
    }

    public void setLast_debt(Double last_debt) {
        this.last_debt = last_debt;
    }

    public Double getLast_yes() {
        return this.last_yes;
    }

    public BeginningInventory last_yes(Double last_yes) {
        this.setLast_yes(last_yes);
        return this;
    }

    public void setLast_yes(Double last_yes) {
        this.last_yes = last_yes;
    }

    public LocalDate getCreated_date() {
        return this.created_date;
    }

    public BeginningInventory created_date(LocalDate created_date) {
        this.setCreated_date(created_date);
        return this;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public String getKeyUUID() {
        return this.keyUUID;
    }

    public BeginningInventory keyUUID(String keyUUID) {
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
        if (!(o instanceof BeginningInventory)) {
            return false;
        }
        return id != null && id.equals(((BeginningInventory) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BeginningInventory{" +
            "id=" + getId() +
            ", account_number='" + getAccount_number() + "'" +
            ", account_name='" + getAccount_name() + "'" +
            ", first_debt=" + getFirst_debt() +
            ", first_yes=" + getFirst_yes() +
            ", debt_arises=" + getDebt_arises() +
            ", arises_yes=" + getArises_yes() +
            ", accumulated_debt=" + getAccumulated_debt() +
            ", accumulated_yes=" + getAccumulated_yes() +
            ", last_debt=" + getLast_debt() +
            ", last_yes=" + getLast_yes() +
            ", created_date='" + getCreated_date() + "'" +
            ", keyUUID='" + getKeyUUID() + "'" +
            "}";
    }
}
