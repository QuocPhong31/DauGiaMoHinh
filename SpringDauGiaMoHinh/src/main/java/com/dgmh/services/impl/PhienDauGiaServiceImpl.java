/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services.impl;

import com.dgmh.pojo.PhienDauGia;
import com.dgmh.repositories.PhienDauGiaRepository;
import com.dgmh.services.PhienDauGiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tran Quoc Phong
 */
@Service
public class PhienDauGiaServiceImpl implements PhienDauGiaService {

    @Autowired
    private PhienDauGiaRepository phienDauGiaRepository;

    @Override
    public PhienDauGia themPhienDauGia(PhienDauGia p) {
        // Mặc định trạng thái là "CHO_DUYET"
        //p.setTrangThai("CHO_DUYET");
        return phienDauGiaRepository.themPhienDauGia(p);
    }

    @Override
    public List<PhienDauGia> getLayTatCaPhien() {
        return phienDauGiaRepository.getLayTatCaPhien();
    }

    @Override
    public PhienDauGia getLayPhienTheoId(int id) {
        return phienDauGiaRepository.getLayPhienTheoId(id);
    }

    @Override
    public boolean duyetPhien(int id) {
        return phienDauGiaRepository.capNhatTrangThai(id, "DUOC_DUYET");
    }
    
    @Override
    public boolean capNhatKetQuaPhien(int phienId) {
        return phienDauGiaRepository.capNhatKetQuaPhien(phienId);
    }
}
