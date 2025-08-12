/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services;

import com.dgmh.pojo.PhienDauGia;
import java.util.List;

/**
 *
 * @author Tran Quoc Phong
 */
public interface PhienDauGiaService {
    PhienDauGia themPhienDauGia(PhienDauGia p);
    List<PhienDauGia> getLayTatCaPhien();
    PhienDauGia getLayPhienTheoId(int id);
    boolean duyetPhien(int id); // admin duyệt
    boolean capNhatKetQuaPhien(int phienId);
    PhienDauGia capNhatPhien(PhienDauGia p);
}
