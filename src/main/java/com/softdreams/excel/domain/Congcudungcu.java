package com.softdreams.excel.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Congcudungcu.
 */
@Entity
@Table(name = "congcudungcu")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Congcudungcu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_ccdc")
    private String ma_ccdc;

    @Column(name = "ten_ccdc")
    private String ten_ccdc;

    @Column(name = "loai_ccdc")
    private String loai_ccdc;

    @Column(name = "ly_do_ghi_tang")
    private String ly_do_ghi_tang;

    @Column(name = "ngay_ghi_tang")
    private LocalDate ngay_ghi_tang;

    @Column(name = "so_ct_ghi_tang")
    private String so_ct_ghi_tang;

    @Column(name = "so_ky_phan_bo")
    private Integer so_ky_phan_bo;

    @Column(name = "so_ky_pb_con_lai")
    private Integer so_ky_pb_con_lai;

    @Column(name = "dvt")
    private String dvt;

    @Column(name = "sl_ghi_tang")
    private Double sl_ghi_tang;

    @Column(name = "luy_ke_sl_da_giam")
    private Double luy_ke_sl_da_giam;

    @Column(name = "sl_con_lai")
    private Double sl_con_lai;

    @Column(name = "gia_tri_ccdc")
    private Double gia_tri_ccdc;

    @Column(name = "gia_tri_pb_hang_ky")
    private Double gia_tri_PB_hang_ky;

    @Column(name = "pb_trong_ky")
    private Double pb_trong_ky;

    @Column(name = "luy_ke_da_pb")
    private Double luy_ke_da_pb;

    @Column(name = "gia_tri_con_lai")
    private Double gia_tri_con_lai;

    @Column(name = "tk_cho_phan_bo")
    private String tk_cho_phan_bo;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "key_uuid")
    private String key_uuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Congcudungcu id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa_ccdc() {
        return this.ma_ccdc;
    }

    public Congcudungcu ma_ccdc(String ma_ccdc) {
        this.setMa_ccdc(ma_ccdc);
        return this;
    }

    public void setMa_ccdc(String ma_ccdc) {
        this.ma_ccdc = ma_ccdc;
    }

    public String getTen_ccdc() {
        return this.ten_ccdc;
    }

    public Congcudungcu ten_ccdc(String ten_ccdc) {
        this.setTen_ccdc(ten_ccdc);
        return this;
    }

    public void setTen_ccdc(String ten_ccdc) {
        this.ten_ccdc = ten_ccdc;
    }

    public String getLoai_ccdc() {
        return this.loai_ccdc;
    }

    public Congcudungcu loai_ccdc(String loai_ccdc) {
        this.setLoai_ccdc(loai_ccdc);
        return this;
    }

    public void setLoai_ccdc(String loai_ccdc) {
        this.loai_ccdc = loai_ccdc;
    }

    public String getLy_do_ghi_tang() {
        return this.ly_do_ghi_tang;
    }

    public Congcudungcu ly_do_ghi_tang(String ly_do_ghi_tang) {
        this.setLy_do_ghi_tang(ly_do_ghi_tang);
        return this;
    }

    public void setLy_do_ghi_tang(String ly_do_ghi_tang) {
        this.ly_do_ghi_tang = ly_do_ghi_tang;
    }

    public LocalDate getNgay_ghi_tang() {
        return this.ngay_ghi_tang;
    }

    public Congcudungcu ngay_ghi_tang(LocalDate ngay_ghi_tang) {
        this.setNgay_ghi_tang(ngay_ghi_tang);
        return this;
    }

    public void setNgay_ghi_tang(LocalDate ngay_ghi_tang) {
        this.ngay_ghi_tang = ngay_ghi_tang;
    }

    public String getSo_ct_ghi_tang() {
        return this.so_ct_ghi_tang;
    }

    public Congcudungcu so_ct_ghi_tang(String so_ct_ghi_tang) {
        this.setSo_ct_ghi_tang(so_ct_ghi_tang);
        return this;
    }

    public void setSo_ct_ghi_tang(String so_ct_ghi_tang) {
        this.so_ct_ghi_tang = so_ct_ghi_tang;
    }

    public Integer getSo_ky_phan_bo() {
        return this.so_ky_phan_bo;
    }

    public Congcudungcu so_ky_phan_bo(Integer so_ky_phan_bo) {
        this.setSo_ky_phan_bo(so_ky_phan_bo);
        return this;
    }

    public void setSo_ky_phan_bo(Integer so_ky_phan_bo) {
        this.so_ky_phan_bo = so_ky_phan_bo;
    }

    public Integer getSo_ky_pb_con_lai() {
        return this.so_ky_pb_con_lai;
    }

    public Congcudungcu so_ky_pb_con_lai(Integer so_ky_pb_con_lai) {
        this.setSo_ky_pb_con_lai(so_ky_pb_con_lai);
        return this;
    }

    public void setSo_ky_pb_con_lai(Integer so_ky_pb_con_lai) {
        this.so_ky_pb_con_lai = so_ky_pb_con_lai;
    }

    public String getDvt() {
        return this.dvt;
    }

    public Congcudungcu dvt(String dvt) {
        this.setDvt(dvt);
        return this;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public Double getSl_ghi_tang() {
        return this.sl_ghi_tang;
    }

    public Congcudungcu sl_ghi_tang(Double sl_ghi_tang) {
        this.setSl_ghi_tang(sl_ghi_tang);
        return this;
    }

    public void setSl_ghi_tang(Double sl_ghi_tang) {
        this.sl_ghi_tang = sl_ghi_tang;
    }

    public Double getLuy_ke_sl_da_giam() {
        return this.luy_ke_sl_da_giam;
    }

    public Congcudungcu luy_ke_sl_da_giam(Double luy_ke_sl_da_giam) {
        this.setLuy_ke_sl_da_giam(luy_ke_sl_da_giam);
        return this;
    }

    public void setLuy_ke_sl_da_giam(Double luy_ke_sl_da_giam) {
        this.luy_ke_sl_da_giam = luy_ke_sl_da_giam;
    }

    public Double getSl_con_lai() {
        return this.sl_con_lai;
    }

    public Congcudungcu sl_con_lai(Double sl_con_lai) {
        this.setSl_con_lai(sl_con_lai);
        return this;
    }

    public void setSl_con_lai(Double sl_con_lai) {
        this.sl_con_lai = sl_con_lai;
    }

    public Double getGia_tri_ccdc() {
        return this.gia_tri_ccdc;
    }

    public Congcudungcu gia_tri_ccdc(Double gia_tri_ccdc) {
        this.setGia_tri_ccdc(gia_tri_ccdc);
        return this;
    }

    public void setGia_tri_ccdc(Double gia_tri_ccdc) {
        this.gia_tri_ccdc = gia_tri_ccdc;
    }

    public Double getGia_tri_PB_hang_ky() {
        return this.gia_tri_PB_hang_ky;
    }

    public Congcudungcu gia_tri_PB_hang_ky(Double gia_tri_PB_hang_ky) {
        this.setGia_tri_PB_hang_ky(gia_tri_PB_hang_ky);
        return this;
    }

    public void setGia_tri_PB_hang_ky(Double gia_tri_PB_hang_ky) {
        this.gia_tri_PB_hang_ky = gia_tri_PB_hang_ky;
    }

    public Double getPb_trong_ky() {
        return this.pb_trong_ky;
    }

    public Congcudungcu pb_trong_ky(Double pb_trong_ky) {
        this.setPb_trong_ky(pb_trong_ky);
        return this;
    }

    public void setPb_trong_ky(Double pb_trong_ky) {
        this.pb_trong_ky = pb_trong_ky;
    }

    public Double getLuy_ke_da_pb() {
        return this.luy_ke_da_pb;
    }

    public Congcudungcu luy_ke_da_pb(Double luy_ke_da_pb) {
        this.setLuy_ke_da_pb(luy_ke_da_pb);
        return this;
    }

    public void setLuy_ke_da_pb(Double luy_ke_da_pb) {
        this.luy_ke_da_pb = luy_ke_da_pb;
    }

    public Double getGia_tri_con_lai() {
        return this.gia_tri_con_lai;
    }

    public Congcudungcu gia_tri_con_lai(Double gia_tri_con_lai) {
        this.setGia_tri_con_lai(gia_tri_con_lai);
        return this;
    }

    public void setGia_tri_con_lai(Double gia_tri_con_lai) {
        this.gia_tri_con_lai = gia_tri_con_lai;
    }

    public String getTk_cho_phan_bo() {
        return this.tk_cho_phan_bo;
    }

    public Congcudungcu tk_cho_phan_bo(String tk_cho_phan_bo) {
        this.setTk_cho_phan_bo(tk_cho_phan_bo);
        return this;
    }

    public void setTk_cho_phan_bo(String tk_cho_phan_bo) {
        this.tk_cho_phan_bo = tk_cho_phan_bo;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Congcudungcu createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getKey_uuid() {
        return this.key_uuid;
    }

    public Congcudungcu key_uuid(String key_uuid) {
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
        if (!(o instanceof Congcudungcu)) {
            return false;
        }
        return id != null && id.equals(((Congcudungcu) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Congcudungcu{" +
            "id=" + getId() +
            ", ma_ccdc='" + getMa_ccdc() + "'" +
            ", ten_ccdc='" + getTen_ccdc() + "'" +
            ", loai_ccdc='" + getLoai_ccdc() + "'" +
            ", ly_do_ghi_tang='" + getLy_do_ghi_tang() + "'" +
            ", ngay_ghi_tang='" + getNgay_ghi_tang() + "'" +
            ", so_ct_ghi_tang='" + getSo_ct_ghi_tang() + "'" +
            ", so_ky_phan_bo=" + getSo_ky_phan_bo() +
            ", so_ky_pb_con_lai=" + getSo_ky_pb_con_lai() +
            ", dvt='" + getDvt() + "'" +
            ", sl_ghi_tang=" + getSl_ghi_tang() +
            ", luy_ke_sl_da_giam=" + getLuy_ke_sl_da_giam() +
            ", sl_con_lai=" + getSl_con_lai() +
            ", gia_tri_ccdc=" + getGia_tri_ccdc() +
            ", gia_tri_PB_hang_ky=" + getGia_tri_PB_hang_ky() +
            ", pb_trong_ky=" + getPb_trong_ky() +
            ", luy_ke_da_pb=" + getLuy_ke_da_pb() +
            ", gia_tri_con_lai=" + getGia_tri_con_lai() +
            ", tk_cho_phan_bo='" + getTk_cho_phan_bo() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", key_uuid='" + getKey_uuid() + "'" +
            "}";
    }
}
