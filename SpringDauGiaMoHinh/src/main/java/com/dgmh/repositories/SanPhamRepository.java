/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories;

import com.dgmh.pojo.SanPham;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tran Quoc Phong
 */
public interface SanPhamRepository {
    SanPham addSanPham(Map<String, String> params, MultipartFile avatar);
    List<SanPham> getAll();
    boolean updateTrangThai(int id, String trangThai);
    SanPham getById(int id);
    boolean delete(int id);
}
