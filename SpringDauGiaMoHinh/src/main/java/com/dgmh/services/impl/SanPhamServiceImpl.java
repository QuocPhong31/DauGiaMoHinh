/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services.impl;

import com.dgmh.pojo.SanPham;
import com.dgmh.repositories.SanPhamRepository;
import com.dgmh.services.SanPhamService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tran Quoc Phong
 */
@Service
@Transactional
public class SanPhamServiceImpl implements SanPhamService{
    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public SanPham addSanPham(String tenSanPham, String moTa, BigDecimal giaKhoiDiem, BigDecimal buocNhay, BigDecimal giaBua, int loaiSanPhamId, String username, MultipartFile avatar) {
        return sanPhamRepository.addSanPham(tenSanPham, moTa, giaKhoiDiem, buocNhay, giaBua, loaiSanPhamId, username, avatar);
    }

    @Override
    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.getAll();
    }
    
    @Override
    public boolean updateTrangThai(int id, String trangThai) {
        return sanPhamRepository.updateTrangThai(id, trangThai);
    }

    @Override
    public SanPham getSanPhamById(int id) {
        return sanPhamRepository.getById(id);
    }

    @Override
    public boolean deleteSanPham(int id) {
        return sanPhamRepository.delete(id);
    }
}
