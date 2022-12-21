package com.softdreams.excel.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Congnophaitra.
 */
@Entity
@Table(name = "cong_no_phai_tra")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Congnophaitra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_ncc")
    private String ma_ncc;

    @Column(name = "ten_ncc")
    private String ten_ncc;

    @Column(name = "tk_cong_no")
    private String tk_congno;

    @Column(name = "so_du_no_dau_ky")
    private Double so_du_no_dau_ky;

    @Column(name = "so_du_co_dau_ky")
    private Double so_du_co_dau_ky;

    @Column(name = "phat_sinh_no")
    private Double phat_sinh_no;

    @Column(name = "phat_sinh_co")
    private Double phat_sinh_co;

    @Column(name = "so_du_no_cuoi_ky")
    private Double so_du_no_cuoi_ky;

    @Column(name = "so_du_co_cuoi_ky")
    private Double so_du_co_cuoi_ky;

    @Column(name = "created_date")
    private LocalDate created_Date;

    @Column(name = "key_uuid")
    private String key_uuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Congnophaitra id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa_ncc() {
        return this.ma_ncc;
    }

    public Congnophaitra ma_ncc(String ma_ncc) {
        this.setMa_ncc(ma_ncc);
        return this;
    }

    public void setMa_ncc(String ma_ncc) {
        this.ma_ncc = ma_ncc;
    }

    public String getTen_ncc() {
        return this.ten_ncc;
    }

    public Congnophaitra ten_ncc(String ten_ncc) {
        this.setTen_ncc(ten_ncc);
        return this;
    }

    public void setTen_ncc(String ten_ncc) {
        this.ten_ncc = ten_ncc;
    }

    public String getTk_congno() {
        return this.tk_congno;
    }

    public Congnophaitra tk_congno(String tk_congno) {
        this.setTk_congno(tk_congno);
        return this;
    }

    public void setTk_congno(String tk_congno) {
        this.tk_congno = tk_congno;
    }

    public Double getSo_du_no_dau_ky() {
        return this.so_du_no_dau_ky;
    }

    public Congnophaitra so_du_no_dau_ky(Double so_du_no_dau_ky) {
        this.setSo_du_no_dau_ky(so_du_no_dau_ky);
        return this;
    }

    public void setSo_du_no_dau_ky(Double so_du_no_dau_ky) {
        this.so_du_no_dau_ky = so_du_no_dau_ky;
    }

    public Double getSo_du_co_dau_ky() {
        return this.so_du_co_dau_ky;
    }

    public Congnophaitra so_du_co_dau_ky(Double so_du_co_dau_ky) {
        this.setSo_du_co_dau_ky(so_du_co_dau_ky);
        return this;
    }

    public void setSo_du_co_dau_ky(Double so_du_co_dau_ky) {
        this.so_du_co_dau_ky = so_du_co_dau_ky;
    }

    public Double getPhat_sinh_no() {
        return this.phat_sinh_no;
    }

    public Congnophaitra phat_sinh_no(Double phat_sinh_no) {
        this.setPhat_sinh_no(phat_sinh_no);
        return this;
    }

    public void setPhat_sinh_no(Double phat_sinh_no) {
        this.phat_sinh_no = phat_sinh_no;
    }

    public Double getPhat_sinh_co() {
        return this.phat_sinh_co;
    }

    public Congnophaitra phat_sinh_co(Double phat_sinh_co) {
        this.setPhat_sinh_co(phat_sinh_co);
        return this;
    }

    public void setPhat_sinh_co(Double phat_sinh_co) {
        this.phat_sinh_co = phat_sinh_co;
    }

    public Double getSo_du_no_cuoi_ky() {
        return this.so_du_no_cuoi_ky;
    }

    public Congnophaitra so_du_no_cuoi_ky(Double so_du_no_cuoi_ky) {
        this.setSo_du_no_cuoi_ky(so_du_no_cuoi_ky);
        return this;
    }

    public void setSo_du_no_cuoi_ky(Double so_du_no_cuoi_ky) {
        this.so_du_no_cuoi_ky = so_du_no_cuoi_ky;
    }

    public Double getSo_du_co_cuoi_ky() {
        return this.so_du_co_cuoi_ky;
    }

    public Congnophaitra so_du_co_cuoi_ky(Double so_du_co_cuoi_ky) {
        this.setSo_du_co_cuoi_ky(so_du_co_cuoi_ky);
        return this;
    }

    public void setSo_du_co_cuoi_ky(Double so_du_co_cuoi_ky) {
        this.so_du_co_cuoi_ky = so_du_co_cuoi_ky;
    }

    public LocalDate getCreated_Date() {
        return this.created_Date;
    }

    public Congnophaitra created_Date(LocalDate created_Date) {
        this.setCreated_Date(created_Date);
        return this;
    }

    public void setCreated_Date(LocalDate created_Date) {
        this.created_Date = created_Date;
    }

    public String getKey_uuid() {
        return this.key_uuid;
    }

    public Congnophaitra key_uuid(String key_uuid) {
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
        if (!(o instanceof Congnophaitra)) {
            return false;
        }
        return id != null && id.equals(((Congnophaitra) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Congnophaitra{" +
            "id=" + getId() +
            ", ma_ncc='" + getMa_ncc() + "'" +
            ", ten_ncc='" + getTen_ncc() + "'" +
            ", tk_congno='" + getTk_congno() + "'" +
            ", so_du_no_dau_ky=" + getSo_du_no_dau_ky() +
            ", so_du_co_dau_ky=" + getSo_du_co_dau_ky() +
            ", phat_sinh_no=" + getPhat_sinh_no() +
            ", phat_sinh_co=" + getPhat_sinh_co() +
            ", so_du_no_cuoi_ky=" + getSo_du_no_cuoi_ky() +
            ", so_du_co_cuoi_ky=" + getSo_du_co_cuoi_ky() +
            ", created_Date='" + getCreated_Date() + "'" +
            ", key_uuid='" + getKey_uuid() + "'" +
            "}";
    }
}
