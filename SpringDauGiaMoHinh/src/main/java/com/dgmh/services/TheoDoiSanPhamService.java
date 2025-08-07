/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services;

import com.dgmh.pojo.TheoDoiSanPham;
import java.util.List;

/**
 *
 * @author Tran Quoc Phong
 */
public interface TheoDoiSanPhamService {
    boolean daTheoDoi(int nguoiDungId, int phienDauGiaId);
    void theoDoi(int nguoiDungId, int phienDauGiaId);
    void boTheoDoi(int nguoiDungId, int phienDauGiaId);
    List<TheoDoiSanPham> layDanhSachTheoDoi(int nguoiDungId);
}
