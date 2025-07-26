/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services.impl;

import com.dgmh.pojo.LoaiSanPham;
import com.dgmh.repositories.LoaiSanPhamRepository;
import com.dgmh.services.LoaiSanPhamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tran Quoc Phong
 */
@Service
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService{
    @Autowired
    private LoaiSanPhamRepository loaiSanPhamRepository;

    @Override
    public LoaiSanPham addLoaiSanPham(LoaiSanPham loaiSanPham) {
        return loaiSanPhamRepository.add(loaiSanPham);
    }

    @Override
    public List<LoaiSanPham> getAllLoaiSanPham() {
        return loaiSanPhamRepository.getAll();
    }
}
