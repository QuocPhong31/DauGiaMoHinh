/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services;

import com.dgmh.pojo.NguoiDung;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tran Quoc Phong
 */
public interface NguoiDungService extends UserDetailsService {
    NguoiDung getByUsername(String username); //apiGiaovu
    NguoiDung getById(int id); //apiAdmin, apiGiaovu
    NguoiDung addUser(NguoiDung user);
    NguoiDung mergeUser(NguoiDung user);
    NguoiDung addUser(Map<String, String> params, MultipartFile avatar); 
    boolean authenticate(String username, String rawPassword);
    boolean deleteUser(int id);
    List<NguoiDung> getAllUsers();  //apiAdmin
}
