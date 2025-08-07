/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories;

import com.dgmh.pojo.TheoDoiSanPham;
import java.util.List;

/**
 *
 * @author Tran Quoc Phong
 */
public interface TheoDoiSanPhamRepository {
    boolean kiemTraTheoDoi(int nguoiDungId, int phienDauGiaId);
    void themTheoDoi(int nguoiDungId, int phienDauGiaId);
    void xoaTheoDoi(int nguoiDungId, int phienDauGiaId);
    List<TheoDoiSanPham> layTheoDoiTheoNguoiDung(int nguoiDungId);
}
