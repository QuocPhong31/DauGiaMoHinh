/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.repositories;

import com.dgmh.pojo.NguoiDung;
import java.util.List;

/**
 *
 * @author Tran Quoc Phong
 */
public interface NguoiDungRepository {
    NguoiDung getByUsername(String username);
    NguoiDung getById(int id);
    NguoiDung addUser(NguoiDung u);
    NguoiDung merge(NguoiDung u);
    boolean vaiTro(String username, String vaiTro);
    boolean deleteUser(int id);
    boolean authenticate(String username, String rawPassword);
    List<NguoiDung> getAllUsers();
    boolean duyetNguoiDung(int userId);
}
