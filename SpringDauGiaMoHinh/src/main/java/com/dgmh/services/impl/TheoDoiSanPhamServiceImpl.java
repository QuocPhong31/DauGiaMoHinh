/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services.impl;

import com.dgmh.pojo.TheoDoiSanPham;
import com.dgmh.repositories.TheoDoiSanPhamRepository;
import com.dgmh.services.TheoDoiSanPhamService;
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
public class TheoDoiSanPhamServiceImpl implements TheoDoiSanPhamService{
    @Autowired
    private TheoDoiSanPhamRepository repo;

    @Override
    public boolean daTheoDoi(int nguoiDungId, int phienDauGiaId) {
        return repo.kiemTraTheoDoi(nguoiDungId, phienDauGiaId);
    }

    @Override
    public void theoDoi(int nguoiDungId, int phienDauGiaId) {
        repo.themTheoDoi(nguoiDungId, phienDauGiaId);
    }

    @Override
    public void boTheoDoi(int nguoiDungId, int phienDauGiaId) {
        repo.xoaTheoDoi(nguoiDungId, phienDauGiaId);
    }

    @Override
    public List<TheoDoiSanPham> layDanhSachTheoDoi(int nguoiDungId) {
        return repo.layTheoDoiTheoNguoiDung(nguoiDungId);
    }
}
