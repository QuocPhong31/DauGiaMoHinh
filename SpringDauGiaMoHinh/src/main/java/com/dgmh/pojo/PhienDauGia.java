/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Tran Quoc Phong
 */
@Entity
@Table(name = "phiendaugia")
public class PhienDauGia implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sanPham_id")
    private SanPham sanPham;

    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianBatDau;

    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianKetThuc;

    private String trangThai;
    private BigDecimal giaChot;

    @ManyToOne
    @JoinColumn(name = "nguoiThangDauGia_id")
    private NguoiDung nguoiThangDauGia;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public Date getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Date thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public BigDecimal getGiaChot() {
        return giaChot;
    }

    public void setGiaChot(BigDecimal giaChot) {
        this.giaChot = giaChot;
    }

    public NguoiDung getNguoiThangDauGia() {
        return nguoiThangDauGia;
    }

    public void setNguoiThangDauGia(NguoiDung nguoiThangDauGia) {
        this.nguoiThangDauGia = nguoiThangDauGia;
    }
}
