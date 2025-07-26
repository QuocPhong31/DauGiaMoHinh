/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services.impl;

import com.dgmh.pojo.SanPham;
import com.dgmh.repositories.SanPhamRepository;
import com.dgmh.services.SanPhamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tran Quoc Phong
 */
@Service
public class SanPhamServiceImpl implements SanPhamService{
    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public SanPham addSanPham(SanPham sanPham) {
        return sanPhamRepository.add(sanPham);
    }

    @Override
    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.getAll();
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
