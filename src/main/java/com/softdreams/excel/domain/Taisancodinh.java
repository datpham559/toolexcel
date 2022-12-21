package com.softdreams.excel.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Taisancodinh.
 */
@Entity
@Table(name = "tai_san_co_dinh")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Taisancodinh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_tscd")
    private String ma_tscd;

    @Column(name = "ten_tscd")
    private String ten_tscd;

    @Column(name = "loai_tscd")
    private String loai_tscd;

    @Column(name = "don_vi_su_dung")
    private String don_vi_su_dung;

    @Column(name = "ngay_ghi_tang")
    private LocalDate ngay_ghi_tang;

    @Column(name = "so_ct_ghi_tang")
    private String so_ct_ghi_tang;

    @Column(name = "ngay_bat_dau_tinh_kh")
    private LocalDate ngay_bat_dau_tinh_kh;

    @Column(name = "thoi_gian_su_dung")
    private Double thoi_gian_su_dung;

    @Column(name = "thoi_gian_su_dung_con_lai")
    private Double thoi_gian_su_dung_con_lai;

    @Column(name = "nguyen_gia")
    private Double nguyen_gia;

    @Column(name = "gia_tri_tinh_kh")
    private Double gia_tri_tinh_kh;

    @Column(name = "hao_mon_trong_ky")
    private Double hao_mon_trong_ky;

    @Column(name = "hao_mon_luy_ke")
    private Double hao_mon_luy_ke;

    @Column(name = "gia_tri_con_lai")
    private Double gia_tri_con_lai;

    @Column(name = "gia_tri_kh_thang")
    private Double gia_tri_KH_thang;

    @Column(name = "tk_nguyen_gia")
    private String tk_nguyen_gia;

    @Column(name = "tk_khau_hao")
    private String tk_khau_hao;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "key_uuid")
    private String key_uuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Taisancodinh id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa_tscd() {
        return this.ma_tscd;
    }

    public Taisancodinh ma_tscd(String ma_tscd) {
        this.setMa_tscd(ma_tscd);
        return this;
    }

    public void setMa_tscd(String ma_tscd) {
        this.ma_tscd = ma_tscd;
    }

    public String getTen_tscd() {
        return this.ten_tscd;
    }

    public Taisancodinh ten_tscd(String ten_tscd) {
        this.setTen_tscd(ten_tscd);
        return this;
    }

    public void setTen_tscd(String ten_tscd) {
        this.ten_tscd = ten_tscd;
    }

    public String getLoai_tscd() {
        return this.loai_tscd;
    }

    public Taisancodinh loai_tscd(String loai_tscd) {
        this.setLoai_tscd(loai_tscd);
        return this;
    }

    public void setLoai_tscd(String loai_tscd) {
        this.loai_tscd = loai_tscd;
    }

    public String getDon_vi_su_dung() {
        return this.don_vi_su_dung;
    }

    public Taisancodinh don_vi_su_dung(String don_vi_su_dung) {
        this.setDon_vi_su_dung(don_vi_su_dung);
        return this;
    }

    public void setDon_vi_su_dung(String don_vi_su_dung) {
        this.don_vi_su_dung = don_vi_su_dung;
    }

    public LocalDate getNgay_ghi_tang() {
        return this.ngay_ghi_tang;
    }

    public Taisancodinh ngay_ghi_tang(LocalDate ngay_ghi_tang) {
        this.setNgay_ghi_tang(ngay_ghi_tang);
        return this;
    }

    public void setNgay_ghi_tang(LocalDate ngay_ghi_tang) {
        this.ngay_ghi_tang = ngay_ghi_tang;
    }

    public String getSo_ct_ghi_tang() {
        return this.so_ct_ghi_tang;
    }

    public Taisancodinh so_ct_ghi_tang(String so_ct_ghi_tang) {
        this.setSo_ct_ghi_tang(so_ct_ghi_tang);
        return this;
    }

    public void setSo_ct_ghi_tang(String so_ct_ghi_tang) {
        this.so_ct_ghi_tang = so_ct_ghi_tang;
    }

    public LocalDate getNgay_bat_dau_tinh_kh() {
        return this.ngay_bat_dau_tinh_kh;
    }

    public Taisancodinh ngay_bat_dau_tinh_kh(LocalDate ngay_bat_dau_tinh_kh) {
        this.setNgay_bat_dau_tinh_kh(ngay_bat_dau_tinh_kh);
        return this;
    }

    public void setNgay_bat_dau_tinh_kh(LocalDate ngay_bat_dau_tinh_kh) {
        this.ngay_bat_dau_tinh_kh = ngay_bat_dau_tinh_kh;
    }

    public Double getThoi_gian_su_dung() {
        return this.thoi_gian_su_dung;
    }

    public Taisancodinh thoi_gian_su_dung(Double thoi_gian_su_dung) {
        this.setThoi_gian_su_dung(thoi_gian_su_dung);
        return this;
    }

    public void setThoi_gian_su_dung(Double thoi_gian_su_dung) {
        this.thoi_gian_su_dung = thoi_gian_su_dung;
    }

    public Double getThoi_gian_su_dung_con_lai() {
        return this.thoi_gian_su_dung_con_lai;
    }

    public Taisancodinh thoi_gian_su_dung_con_lai(Double thoi_gian_su_dung_con_lai) {
        this.setThoi_gian_su_dung_con_lai(thoi_gian_su_dung_con_lai);
        return this;
    }

    public void setThoi_gian_su_dung_con_lai(Double thoi_gian_su_dung_con_lai) {
        this.thoi_gian_su_dung_con_lai = thoi_gian_su_dung_con_lai;
    }

    public Double getNguyen_gia() {
        return this.nguyen_gia;
    }

    public Taisancodinh nguyen_gia(Double nguyen_gia) {
        this.setNguyen_gia(nguyen_gia);
        return this;
    }

    public void setNguyen_gia(Double nguyen_gia) {
        this.nguyen_gia = nguyen_gia;
    }

    public Double getGia_tri_tinh_kh() {
        return this.gia_tri_tinh_kh;
    }

    public Taisancodinh gia_tri_tinh_kh(Double gia_tri_tinh_kh) {
        this.setGia_tri_tinh_kh(gia_tri_tinh_kh);
        return this;
    }

    public void setGia_tri_tinh_kh(Double gia_tri_tinh_kh) {
        this.gia_tri_tinh_kh = gia_tri_tinh_kh;
    }

    public Double getHao_mon_trong_ky() {
        return this.hao_mon_trong_ky;
    }

    public Taisancodinh hao_mon_trong_ky(Double hao_mon_trong_ky) {
        this.setHao_mon_trong_ky(hao_mon_trong_ky);
        return this;
    }

    public void setHao_mon_trong_ky(Double hao_mon_trong_ky) {
        this.hao_mon_trong_ky = hao_mon_trong_ky;
    }

    public Double getHao_mon_luy_ke() {
        return this.hao_mon_luy_ke;
    }

    public Taisancodinh hao_mon_luy_ke(Double hao_mon_luy_ke) {
        this.setHao_mon_luy_ke(hao_mon_luy_ke);
        return this;
    }

    public void setHao_mon_luy_ke(Double hao_mon_luy_ke) {
        this.hao_mon_luy_ke = hao_mon_luy_ke;
    }

    public Double getGia_tri_con_lai() {
        return this.gia_tri_con_lai;
    }

    public Taisancodinh gia_tri_con_lai(Double gia_tri_con_lai) {
        this.setGia_tri_con_lai(gia_tri_con_lai);
        return this;
    }

    public void setGia_tri_con_lai(Double gia_tri_con_lai) {
        this.gia_tri_con_lai = gia_tri_con_lai;
    }

    public Double getGia_tri_KH_thang() {
        return this.gia_tri_KH_thang;
    }

    public Taisancodinh gia_tri_KH_thang(Double gia_tri_KH_thang) {
        this.setGia_tri_KH_thang(gia_tri_KH_thang);
        return this;
    }

    public void setGia_tri_KH_thang(Double gia_tri_KH_thang) {
        this.gia_tri_KH_thang = gia_tri_KH_thang;
    }

    public String getTk_nguyen_gia() {
        return this.tk_nguyen_gia;
    }

    public Taisancodinh tk_nguyen_gia(String tk_nguyen_gia) {
        this.setTk_nguyen_gia(tk_nguyen_gia);
        return this;
    }

    public void setTk_nguyen_gia(String tk_nguyen_gia) {
        this.tk_nguyen_gia = tk_nguyen_gia;
    }

    public String getTk_khau_hao() {
        return this.tk_khau_hao;
    }

    public Taisancodinh tk_khau_hao(String tk_khau_hao) {
        this.setTk_khau_hao(tk_khau_hao);
        return this;
    }

    public void setTk_khau_hao(String tk_khau_hao) {
        this.tk_khau_hao = tk_khau_hao;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Taisancodinh createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getKey_uuid() {
        return this.key_uuid;
    }

    public Taisancodinh key_uuid(String key_uuid) {
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
        if (!(o instanceof Taisancodinh)) {
            return false;
        }
        return id != null && id.equals(((Taisancodinh) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Taisancodinh{" +
            "id=" + getId() +
            ", ma_tscd='" + getMa_tscd() + "'" +
            ", ten_tscd='" + getTen_tscd() + "'" +
            ", loai_tscd='" + getLoai_tscd() + "'" +
            ", don_vi_su_dung='" + getDon_vi_su_dung() + "'" +
            ", ngay_ghi_tang='" + getNgay_ghi_tang() + "'" +
            ", so_ct_ghi_tang='" + getSo_ct_ghi_tang() + "'" +
            ", ngay_bat_dau_tinh_kh='" + getNgay_bat_dau_tinh_kh() + "'" +
            ", thoi_gian_su_dung=" + getThoi_gian_su_dung() +
            ", thoi_gian_su_dung_con_lai=" + getThoi_gian_su_dung_con_lai() +
            ", nguyen_gia=" + getNguyen_gia() +
            ", gia_tri_tinh_kh=" + getGia_tri_tinh_kh() +
            ", hao_mon_trong_ky=" + getHao_mon_trong_ky() +
            ", hao_mon_luy_ke=" + getHao_mon_luy_ke() +
            ", gia_tri_con_lai=" + getGia_tri_con_lai() +
            ", gia_tri_KH_thang=" + getGia_tri_KH_thang() +
            ", tk_nguyen_gia='" + getTk_nguyen_gia() + "'" +
            ", tk_khau_hao='" + getTk_khau_hao() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", key_uuid='" + getKey_uuid() + "'" +
            "}";
    }
}
