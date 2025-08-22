/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.controllers;

import com.dgmh.pojo.NguoiDung;
import com.dgmh.pojo.ThongTinTaiKhoan;
import com.dgmh.services.NguoiDungService;
import com.dgmh.services.ThongTinTaiKhoanService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tran Quoc Phong
 */
@RestController
@RequestMapping("/api/taikhoannganhang")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiThongTinTaiKhoanController {
    @Autowired
    private ThongTinTaiKhoanService tkService;

    @Autowired
    private NguoiDungService userService;

    // Lấy tất cả TK của người bán đang đăng nhập
    @GetMapping("/cua-toi")
    public ResponseEntity<?> taiKhoanCuaToi(Principal principal) {
        if (principal == null)
            return ResponseEntity.status(401).build();

        NguoiDung u = userService.getByUsername(principal.getName());
        return ResponseEntity.ok(tkService.findByNguoiBan(u));
    }

    // Thêm tài khoản mới
    @PostMapping("/them")
    public ResponseEntity<?> themTaiKhoan(
    @RequestParam("tenNguoiNhan") String tenNguoiNhan,
    @RequestParam("nganHang") String nganHang,
    @RequestParam("soTaiKhoan") String soTaiKhoan,
    @RequestParam(value = "qrFile", required = false) MultipartFile qrFile,
    Principal principal) {


    if (principal == null)
    return ResponseEntity.status(401).build();


    NguoiDung u = userService.getByUsername(principal.getName());


    ThongTinTaiKhoan tk = tkService.addTaiKhoan(u, tenNguoiNhan, nganHang, soTaiKhoan, qrFile);


    return ResponseEntity.ok(tk);
    }
}
