/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services;

import com.dgmh.pojo.DonThanhToanDauGia;
import com.dgmh.pojo.NguoiDung;
import com.dgmh.pojo.PhienDauGia;
import java.util.List;

/**
 *
 * @author Tran Quoc Phong
 */
public interface DonThanhToanDauGiaService {
    DonThanhToanDauGia findByPhien(PhienDauGia p);
    List<DonThanhToanDauGia> findByNguoiMua(NguoiDung u);
    DonThanhToanDauGia add(DonThanhToanDauGia d);
    DonThanhToanDauGia update(DonThanhToanDauGia d);
    DonThanhToanDauGia getById(Integer id);
    DonThanhToanDauGia taoDon(PhienDauGia p);
    List<DonThanhToanDauGia> DonQuaHanChuaThanhToan();
    void huyDon(int donId, String lyDo);
}
