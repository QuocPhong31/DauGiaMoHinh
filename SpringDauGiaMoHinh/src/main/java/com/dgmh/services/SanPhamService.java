/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services;

import com.dgmh.pojo.SanPham;
import java.util.List;

/**
 *
 * @author Tran Quoc Phong
 */
public interface SanPhamService {
    SanPham addSanPham(SanPham sanPham);
    List<SanPham> getAllSanPham();
    boolean updateTrangThai(int id, String trangThai);
    SanPham getSanPhamById(int id);
    boolean deleteSanPham(int id);
}
