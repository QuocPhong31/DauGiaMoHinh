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
    TheoDoiSanPham themTheoDoi(TheoDoiSanPham t);
    boolean xoaTheoDoi(int nguoiDungId, int sanPhamId);
    List<TheoDoiSanPham> layTheoDoiTheoNguoiDung(int nguoiDungId);
    boolean kiemTraDangTheoDoi(int nguoiDungId, int sanPhamId);
}
