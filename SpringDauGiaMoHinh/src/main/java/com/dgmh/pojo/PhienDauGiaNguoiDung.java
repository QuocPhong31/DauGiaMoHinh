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
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Tran Quoc Phong
 */
@Entity
@Table(name = "phiendaugia_nguoidung")
public class PhienDauGiaNguoiDung implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "phienDauGia_id")
    private PhienDauGia phienDauGia;

    @ManyToOne
    @JoinColumn(name = "nguoiDung_id")
    private NguoiDung nguoiDung;

    private BigDecimal giaDau;

    private LocalDateTime thoiGianDauGia;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PhienDauGia getPhienDauGia() {
        return phienDauGia;
    }

    public void setPhienDauGia(PhienDauGia phienDauGia) {
        this.phienDauGia = phienDauGia;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public BigDecimal getGiaDau() {
        return giaDau;
    }

    public void setGiaDau(BigDecimal giaDau) {
        this.giaDau = giaDau;
    }

    public LocalDateTime getThoiGianDauGia() {
        return thoiGianDauGia;
    }

    public void setThoiGianDauGia(LocalDateTime thoiGianDauGia) {
        this.thoiGianDauGia = thoiGianDauGia;
    }
}
