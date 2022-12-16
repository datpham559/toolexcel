package com.softdreams.excel.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Tonkhodauky.
 */
@Entity
@Table(name = "ton_kho_dau_ky")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Tonkhodauky implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ten_kho")
    private String ten_kho;

    @Column(name = "ma_hang")
    private String ma_hang;

    @Column(name = "ten_hang")
    private String ten_hang;

    @Column(name = "dvt")
    private String dvt;

    @Column(name = "dau_ky_so_luong")
    private Double dau_ky_so_luong;

    @Column(name = "dau_ky_gia_tri")
    private Double dau_ky_gia_tri;

    @Column(name = "nhap_kho_so_luong")
    private Double nhap_kho_so_luong;

    @Column(name = "nhap_kho_gia_tri")
    private Double nhap_kho_gia_tri;

    @Column(name = "xuat_kho_so_luong")
    private Double xuat_kho_so_luong;

    @Column(name = "xuat_kho_gia_tri")
    private Double xuat_kho_gia_tri;

    @Column(name = "cuoi_ky_so_luong")
    private Double cuoi_ky_so_luong;

    @Column(name = "cuoi_ky_gia_tri")
    private Double cuoi_ky_gia_tri;

    @Column(name = "don_gia_binh_quan")
    private Double don_gia_binh_quan;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "key_uuid")
    private String key_uuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Tonkhodauky id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen_kho() {
        return this.ten_kho;
    }

    public Tonkhodauky ten_kho(String ten_kho) {
        this.setTen_kho(ten_kho);
        return this;
    }

    public void setTen_kho(String ten_kho) {
        this.ten_kho = ten_kho;
    }

    public String getMa_hang() {
        return this.ma_hang;
    }

    public Tonkhodauky ma_hang(String ma_hang) {
        this.setMa_hang(ma_hang);
        return this;
    }

    public void setMa_hang(String ma_hang) {
        this.ma_hang = ma_hang;
    }

    public String getTen_hang() {
        return this.ten_hang;
    }

    public Tonkhodauky ten_hang(String ten_hang) {
        this.setTen_hang(ten_hang);
        return this;
    }

    public void setTen_hang(String ten_hang) {
        this.ten_hang = ten_hang;
    }

    public String getDvt() {
        return this.dvt;
    }

    public Tonkhodauky dvt(String dvt) {
        this.setDvt(dvt);
        return this;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public Double getDau_ky_so_luong() {
        return this.dau_ky_so_luong;
    }

    public Tonkhodauky dau_ky_so_luong(Double dau_ky_so_luong) {
        this.setDau_ky_so_luong(dau_ky_so_luong);
        return this;
    }

    public void setDau_ky_so_luong(Double dau_ky_so_luong) {
        this.dau_ky_so_luong = dau_ky_so_luong;
    }

    public Double getDau_ky_gia_tri() {
        return this.dau_ky_gia_tri;
    }

    public Tonkhodauky dau_ky_gia_tri(Double dau_ky_gia_tri) {
        this.setDau_ky_gia_tri(dau_ky_gia_tri);
        return this;
    }

    public void setDau_ky_gia_tri(Double dau_ky_gia_tri) {
        this.dau_ky_gia_tri = dau_ky_gia_tri;
    }

    public Double getNhap_kho_so_luong() {
        return this.nhap_kho_so_luong;
    }

    public Tonkhodauky nhap_kho_so_luong(Double nhap_kho_so_luong) {
        this.setNhap_kho_so_luong(nhap_kho_so_luong);
        return this;
    }

    public void setNhap_kho_so_luong(Double nhap_kho_so_luong) {
        this.nhap_kho_so_luong = nhap_kho_so_luong;
    }

    public Double getNhap_kho_gia_tri() {
        return this.nhap_kho_gia_tri;
    }

    public Tonkhodauky nhap_kho_gia_tri(Double nhap_kho_gia_tri) {
        this.setNhap_kho_gia_tri(nhap_kho_gia_tri);
        return this;
    }

    public void setNhap_kho_gia_tri(Double nhap_kho_gia_tri) {
        this.nhap_kho_gia_tri = nhap_kho_gia_tri;
    }

    public Double getXuat_kho_so_luong() {
        return this.xuat_kho_so_luong;
    }

    public Tonkhodauky xuat_kho_so_luong(Double xuat_kho_so_luong) {
        this.setXuat_kho_so_luong(xuat_kho_so_luong);
        return this;
    }

    public void setXuat_kho_so_luong(Double xuat_kho_so_luong) {
        this.xuat_kho_so_luong = xuat_kho_so_luong;
    }

    public Double getXuat_kho_gia_tri() {
        return this.xuat_kho_gia_tri;
    }

    public Tonkhodauky xuat_kho_gia_tri(Double xuat_kho_gia_tri) {
        this.setXuat_kho_gia_tri(xuat_kho_gia_tri);
        return this;
    }

    public void setXuat_kho_gia_tri(Double xuat_kho_gia_tri) {
        this.xuat_kho_gia_tri = xuat_kho_gia_tri;
    }

    public Double getCuoi_ky_so_luong() {
        return this.cuoi_ky_so_luong;
    }

    public Tonkhodauky cuoi_ky_so_luong(Double cuoi_ky_so_luong) {
        this.setCuoi_ky_so_luong(cuoi_ky_so_luong);
        return this;
    }

    public void setCuoi_ky_so_luong(Double cuoi_ky_so_luong) {
        this.cuoi_ky_so_luong = cuoi_ky_so_luong;
    }

    public Double getCuoi_ky_gia_tri() {
        return this.cuoi_ky_gia_tri;
    }

    public Tonkhodauky cuoi_ky_gia_tri(Double cuoi_ky_gia_tri) {
        this.setCuoi_ky_gia_tri(cuoi_ky_gia_tri);
        return this;
    }

    public void setCuoi_ky_gia_tri(Double cuoi_ky_gia_tri) {
        this.cuoi_ky_gia_tri = cuoi_ky_gia_tri;
    }

    public Double getDon_gia_binh_quan() {
        return this.don_gia_binh_quan;
    }

    public Tonkhodauky don_gia_binh_quan(Double don_gia_binh_quan) {
        this.setDon_gia_binh_quan(don_gia_binh_quan);
        return this;
    }

    public void setDon_gia_binh_quan(Double don_gia_binh_quan) {
        this.don_gia_binh_quan = don_gia_binh_quan;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Tonkhodauky createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getKey_uuid() {
        return this.key_uuid;
    }

    public Tonkhodauky key_uuid(String key_uuid) {
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
        if (!(o instanceof Tonkhodauky)) {
            return false;
        }
        return id != null && id.equals(((Tonkhodauky) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tonkhodauky{" +
            "id=" + getId() +
            ", ten_kho='" + getTen_kho() + "'" +
            ", ma_hang='" + getMa_hang() + "'" +
            ", ten_hang='" + getTen_hang() + "'" +
            ", dvt='" + getDvt() + "'" +
            ", dau_ky_so_luong=" + getDau_ky_so_luong() +
            ", dau_ky_gia_tri=" + getDau_ky_gia_tri() +
            ", nhap_kho_so_luong=" + getNhap_kho_so_luong() +
            ", nhap_kho_gia_tri=" + getNhap_kho_gia_tri() +
            ", xuat_kho_so_luong=" + getXuat_kho_so_luong() +
            ", xuat_kho_gia_tri=" + getXuat_kho_gia_tri() +
            ", cuoi_ky_so_luong=" + getCuoi_ky_so_luong() +
            ", cuoi_ky_gia_tri=" + getCuoi_ky_gia_tri() +
            ", don_gia_binh_quan=" + getDon_gia_binh_quan() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", key_uuid='" + getKey_uuid() + "'" +
            "}";
    }
}
