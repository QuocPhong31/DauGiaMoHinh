/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories;

import com.dgmh.pojo.LoaiSanPham;
import java.util.List;

/**
 *
 * @author Tran Quoc Phong
 */
public interface LoaiSanPhamRepository {
    LoaiSanPham add(LoaiSanPham loaiSanPham);
    List<LoaiSanPham> getAll();
}
