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
    TheoDoiSanPham theoDoi(int nguoiDungId, int phienId);
    boolean boTheoDoi(int nguoiDungId, int phienId);
    List<TheoDoiSanPham> getTheoDoiByNguoiDung(int nguoiDungId);
    boolean isDangTheoDoi(int nguoiDungId, int phienId);
}
