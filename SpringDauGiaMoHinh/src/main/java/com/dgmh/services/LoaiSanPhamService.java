/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services;

import com.dgmh.pojo.LoaiSanPham;
import java.util.List;

/**
 *
 * @author Tran Quoc Phong
 */
public interface LoaiSanPhamService {
    LoaiSanPham addLoaiSanPham(LoaiSanPham loaiSanPham);
    List<LoaiSanPham> getLoaiSanPham();
    List<LoaiSanPham> getLoaiSanPhamHoatDong();
    boolean deleteLoaiSanPham(int id);
    boolean khoaLoaiSanPham(int id);
    boolean moKhoaLoaiSanPham(int id);
}
