/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.controllers;

import com.dgmh.pojo.SanPham;
import com.dgmh.services.NguoiDungService;
import com.dgmh.services.SanPhamService;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tran Quoc Phong
 */
@RestController
@RequestMapping("/api/sanpham")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiSanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private NguoiDungService nguoiDungService;

    // API để đăng sản phẩm mới
    @PostMapping("/dangsanpham")
    public ResponseEntity<?> createProduct(@RequestParam("tenSanPham") String tenSanPham,
                                           @RequestParam("moTa") String moTa,
                                           @RequestParam("giaKhoiDiem") BigDecimal giaKhoiDiem,
                                           @RequestParam("buocNhay") BigDecimal buocNhay,
                                           @RequestParam("giaBua") BigDecimal giaBua,
                                           @RequestParam("loaiSanPham_id") int loaiSanPhamId,
                                           @RequestParam(value = "avatar", required = false) MultipartFile avatar,
                                           Principal principal) {
        
        // Lấy thông tin người dùng đang đăng nhập
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bạn cần đăng nhập để thực hiện thao tác này");
        }
        String username = principal.getName();

        // Kiểm tra nếu người dùng không có vai trò "ROLE_NGUOIBAN"
//        if (!nguoiDungService.vaiTro(username, "ROLE_NGUOIBAN")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                    .body("Chỉ người bán mới có quyền đăng sản phẩm.");
//        }

        try {
            // Gọi service để thêm sản phẩm
            SanPham sanPham = sanPhamService.addSanPham(
                tenSanPham, moTa, giaKhoiDiem, buocNhay, giaBua, loaiSanPhamId, username, avatar
            );
            return new ResponseEntity<>(sanPham, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi đăng sản phẩm: " + e.getMessage());
        }
    }
}
