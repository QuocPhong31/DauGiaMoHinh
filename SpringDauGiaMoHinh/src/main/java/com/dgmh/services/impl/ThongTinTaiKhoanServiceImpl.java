/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services.impl;

import com.dgmh.pojo.NguoiDung;
import com.dgmh.pojo.ThongTinTaiKhoan;
import com.dgmh.repositories.ThongTinTaiKhoanRepository;
import com.dgmh.services.ThongTinTaiKhoanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tran Quoc Phong
 */
@Service
public class ThongTinTaiKhoanServiceImpl implements ThongTinTaiKhoanService {
    @Autowired
    private ThongTinTaiKhoanRepository repo;

    @Override
    public List<ThongTinTaiKhoan> findByNguoiBan(NguoiDung nd) {
        return repo.findByNguoiBan(nd);
    }

    @Override
    public ThongTinTaiKhoan addTaiKhoan(NguoiDung u, String tenNguoiNhan, String nganHang, String soTaiKhoan, MultipartFile qrFile) {
        return repo.addTaiKhoan(u, tenNguoiNhan, nganHang, soTaiKhoan, qrFile);
    }
    
    @Override
    public boolean taiKhoanNguoiBan(NguoiDung nd) {
        return !repo.findByNguoiBan(nd).isEmpty();
    }
}
