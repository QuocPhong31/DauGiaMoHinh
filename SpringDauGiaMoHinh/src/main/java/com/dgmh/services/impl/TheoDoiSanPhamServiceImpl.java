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

/**
 *
 * @author Tran Quoc Phong
 */
@Service
public class TheoDoiSanPhamServiceImpl implements TheoDoiSanPhamService{
    @Autowired
    private TheoDoiSanPhamRepository theoDoiSanPhamRepository;

    @Override
    public TheoDoiSanPham themTheoDoi(TheoDoiSanPham t) {
        return theoDoiSanPhamRepository.themTheoDoi(t);
    }

    @Override
    public boolean xoaTheoDoi(int nguoiDungId, int sanPhamId) {
        return theoDoiSanPhamRepository.xoaTheoDoi(nguoiDungId, sanPhamId);
    }

    @Override
    public List<TheoDoiSanPham> layTheoDoiTheoNguoiDung(int nguoiDungId) {
        return theoDoiSanPhamRepository.layTheoDoiTheoNguoiDung(nguoiDungId);
    }

    @Override
    public boolean kiemTraDangTheoDoi(int nguoiDungId, int sanPhamId) {
        return theoDoiSanPhamRepository.kiemTraDangTheoDoi(nguoiDungId, sanPhamId);
    }
}
