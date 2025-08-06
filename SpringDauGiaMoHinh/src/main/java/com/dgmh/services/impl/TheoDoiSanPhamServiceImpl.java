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
    private TheoDoiSanPhamRepository theoDoiSanPhamRepository;

    @Override
    public TheoDoiSanPham theoDoi(int nguoiDungId, int phienId) {
        return theoDoiSanPhamRepository.theoDoi(nguoiDungId, phienId);
    }

    @Override
    public boolean boTheoDoi(int nguoiDungId, int phienId) {
        return theoDoiSanPhamRepository.boTheoDoi(nguoiDungId, phienId);
    }

    @Override
    public List<TheoDoiSanPham> getTheoDoiByNguoiDung(int nguoiDungId) {
        return theoDoiSanPhamRepository.getTheoDoiByNguoiDung(nguoiDungId);
    }

    @Override
    public boolean isDangTheoDoi(int nguoiDungId, int phienId) {
        return theoDoiSanPhamRepository.isDangTheoDoi(nguoiDungId, phienId);
    }
}
