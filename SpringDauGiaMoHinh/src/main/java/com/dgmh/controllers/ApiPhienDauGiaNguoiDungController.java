/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.controllers;

import com.dgmh.pojo.NguoiDung;
import com.dgmh.pojo.PhienDauGia;
import com.dgmh.pojo.PhienDauGiaNguoiDung;
import com.dgmh.services.NguoiDungService;
import com.dgmh.services.PhienDauGiaNguoiDungService;
import com.dgmh.services.PhienDauGiaService;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tran Quoc Phong
 */
@RestController
@RequestMapping("/api/phiendaugianguoidung")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiPhienDauGiaNguoiDungController {
    @Autowired
    private PhienDauGiaNguoiDungService phienDauGiaNguoiDungService;

    @Autowired
    private PhienDauGiaService phienDauGiaService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @PostMapping("/dat-gia")
    public ResponseEntity<?> datGia(@RequestBody Map<String, String> params, Principal principal) {
        try {
            // Lấy thông tin phiên
            int phienId = Integer.parseInt(params.get("phienDauGiaId"));
            BigDecimal gia = new BigDecimal(params.get("gia"));
            PhienDauGia phien = this.phienDauGiaService.getLayPhienTheoId(phienId);
            if (phien == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy phiên đấu giá");

            // Lấy username từ JWT token
            if (principal == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bạn cần đăng nhập để đấu giá");

            String username = principal.getName();
            NguoiDung nd = this.nguoiDungService.getByUsername(username); // Bạn cần implement

            if (nd == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Không tìm thấy thông tin người dùng");

            // Tạo lượt đặt giá
            PhienDauGiaNguoiDung pdgNd = new PhienDauGiaNguoiDung();
            pdgNd.setPhienDauGia(phien);
            pdgNd.setNguoiDung(nd);
            pdgNd.setGiaDau(gia);
            pdgNd.setThoiGianDauGia(LocalDateTime.now());

            this.phienDauGiaNguoiDungService.datGia(pdgNd);
            return ResponseEntity.ok("Đặt giá thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi xử lý đặt giá: " + e.getMessage());
        }
    }
}
