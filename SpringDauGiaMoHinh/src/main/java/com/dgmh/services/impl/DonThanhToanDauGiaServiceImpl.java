/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services.impl;

import com.dgmh.pojo.DonThanhToanDauGia;
import com.dgmh.pojo.NguoiDung;
import com.dgmh.pojo.PhienDauGia;
import com.dgmh.repositories.DonThanhToanDauGiaRepository;
import com.dgmh.services.DonThanhToanDauGiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tran Quoc Phong
 */
@Service
@Transactional
public class DonThanhToanDauGiaServiceImpl implements DonThanhToanDauGiaService {

    @Autowired
    private DonThanhToanDauGiaRepository repo;

    @Override
    public DonThanhToanDauGia findByPhien(PhienDauGia p) {
        return repo.findByPhien(p);
    }

    @Override
    public List<DonThanhToanDauGia> findByNguoiMua(NguoiDung u) {
        return repo.findByNguoiMua(u);
    }

    @Override
    public DonThanhToanDauGia add(DonThanhToanDauGia d) {
        return repo.add(d);
    }

    @Override
    public DonThanhToanDauGia update(DonThanhToanDauGia d) {
        return repo.update(d);
    }

    @Override
    public DonThanhToanDauGia getById(Integer id) {
        return repo.getById(id);
    }

    @Override
    public DonThanhToanDauGia taoDon(PhienDauGia p) {
        return repo.taoDon(p);
    }

    @Override
    public List<DonThanhToanDauGia> DonQuaHanChuaThanhToan() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.add(java.util.Calendar.DATE, -3); // Trừ 3 ngày
        java.util.Date deadline = calendar.getTime();

        return repo.DonQuaHanChuaThanhToan();
    }

    @Override
    public void huyDon(int donId, String lyDo) {
        repo.huyDon(donId, lyDo);
    }
}
