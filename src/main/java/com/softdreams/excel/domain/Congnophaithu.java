package com.softdreams.excel.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Congnophaithu.
 */
@Entity
@Table(name = "cong_no_phai_thu")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Congnophaithu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_kh")
    private String makh;

    @Column(name = "ten_kh")
    private String tenkh;

    @Column(name = "tk_cong_no")
    private String tkcongno;

    @Column(name = "so_du_no_dau_ky")
    private Double sodunodauky;

    @Column(name = "so_du_co_dau_ky")
    private Double soducodauky;

    @Column(name = "so_no_phat_sinh")
    private Double sonophatsinh;

    @Column(name = "so_co_phat_sinh")
    private Double socophatsinh;

    @Column(name = "so_du_no_cuoi_ky")
    private Double sodunocuoiky;

    @Column(name = "so_du_co_cuoi_ky")
    private Double soducocuoiky;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "key_uuid")
    private String key_uuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Congnophaithu id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMakh() {
        return this.makh;
    }

    public Congnophaithu makh(String makh) {
        this.setMakh(makh);
        return this;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getTenkh() {
        return this.tenkh;
    }

    public Congnophaithu tenkh(String tenkh) {
        this.setTenkh(tenkh);
        return this;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getTkcongno() {
        return this.tkcongno;
    }

    public Congnophaithu tkcongno(String tkcongno) {
        this.setTkcongno(tkcongno);
        return this;
    }

    public void setTkcongno(String tkcongno) {
        this.tkcongno = tkcongno;
    }

    public Double getSodunodauky() {
        return this.sodunodauky;
    }

    public Congnophaithu sodunodauky(Double sodunodauky) {
        this.setSodunodauky(sodunodauky);
        return this;
    }

    public void setSodunodauky(Double sodunodauky) {
        this.sodunodauky = sodunodauky;
    }

    public Double getSoducodauky() {
        return this.soducodauky;
    }

    public Congnophaithu soducodauky(Double soducodauky) {
        this.setSoducodauky(soducodauky);
        return this;
    }

    public void setSoducodauky(Double soducodauky) {
        this.soducodauky = soducodauky;
    }

    public Double getSonophatsinh() {
        return this.sonophatsinh;
    }

    public Congnophaithu sonophatsinh(Double sonophatsinh) {
        this.setSonophatsinh(sonophatsinh);
        return this;
    }

    public void setSonophatsinh(Double sonophatsinh) {
        this.sonophatsinh = sonophatsinh;
    }

    public Double getSocophatsinh() {
        return this.socophatsinh;
    }

    public Congnophaithu socophatsinh(Double socophatsinh) {
        this.setSocophatsinh(socophatsinh);
        return this;
    }

    public void setSocophatsinh(Double socophatsinh) {
        this.socophatsinh = socophatsinh;
    }

    public Double getSodunocuoiky() {
        return this.sodunocuoiky;
    }

    public Congnophaithu sodunocuoiky(Double sodunocuoiky) {
        this.setSodunocuoiky(sodunocuoiky);
        return this;
    }

    public void setSodunocuoiky(Double sodunocuoiky) {
        this.sodunocuoiky = sodunocuoiky;
    }

    public Double getSoducocuoiky() {
        return this.soducocuoiky;
    }

    public Congnophaithu soducocuoiky(Double soducocuoiky) {
        this.setSoducocuoiky(soducocuoiky);
        return this;
    }

    public void setSoducocuoiky(Double soducocuoiky) {
        this.soducocuoiky = soducocuoiky;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Congnophaithu createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getKey_uuid() {
        return this.key_uuid;
    }

    public Congnophaithu key_uuid(String key_uuid) {
        this.setKey_uuid(key_uuid);
        return this;
    }

    public void setKey_uuid(String key_uuid) {
        this.key_uuid = key_uuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Congnophaithu)) {
            return false;
        }
        return id != null && id.equals(((Congnophaithu) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Congnophaithu{" +
            "id=" + getId() +
            ", makh='" + getMakh() + "'" +
            ", tenkh='" + getTenkh() + "'" +
            ", tkcongno='" + getTkcongno() + "'" +
            ", sodunodauky=" + getSodunodauky() +
            ", soducodauky=" + getSoducodauky() +
            ", sonophatsinh=" + getSonophatsinh() +
            ", socophatsinh=" + getSocophatsinh() +
            ", sodunocuoiky=" + getSodunocuoiky() +
            ", soducocuoiky=" + getSoducocuoiky() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", key_uuid='" + getKey_uuid() + "'" +
            "}";
    }
}
