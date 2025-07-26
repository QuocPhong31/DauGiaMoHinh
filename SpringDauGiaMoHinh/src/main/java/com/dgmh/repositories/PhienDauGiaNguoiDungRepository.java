/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories;

import com.dgmh.pojo.PhienDauGiaNguoiDung;
import java.util.List;

/**
 *
 * @author Tran Quoc Phong
 */
public interface PhienDauGiaNguoiDungRepository {
    PhienDauGiaNguoiDung datGia(PhienDauGiaNguoiDung p);
    List<PhienDauGiaNguoiDung> getLichSuByPhien(int phienId);
    List<PhienDauGiaNguoiDung> getLichSuByNguoiDung(int nguoiDungId);
    PhienDauGiaNguoiDung getGiaCaoNhat(int phienId);
}
