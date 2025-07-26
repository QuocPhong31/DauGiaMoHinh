/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories;

import com.dgmh.pojo.SanPham;
import java.util.List;

/**
 *
 * @author Tran Quoc Phong
 */
public interface SanPhamRepository {
    SanPham add(SanPham sanPham);
    List<SanPham> getAll();
    SanPham getById(int id);
    boolean delete(int id);
}
