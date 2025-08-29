/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.controllers;

import com.dgmh.pojo.DonThanhToanDauGia;
import com.dgmh.pojo.NguoiDung;
import com.dgmh.pojo.PhienDauGia;
import com.dgmh.pojo.PhienDauGiaNguoiDung;
import com.dgmh.services.DonThanhToanDauGiaService;
import com.dgmh.services.NguoiDungService;
import com.dgmh.services.PhienDauGiaNguoiDungService;
import com.dgmh.services.PhienDauGiaService;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tran Quoc Phong
 */
@RestController
@RequestMapping("/api/phiendaugia")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiPhienDauGiaController {

    @Autowired
    private PhienDauGiaService phienDauGiaService;

    @Autowired
    private PhienDauGiaNguoiDungService phienDauGiaNguoiDungService;

    @Autowired
    private DonThanhToanDauGiaService donThanhToanDauGiaService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/phiendaugia")
    public List<PhienDauGia> layTatCaPhienDangDienRa() {
        List<PhienDauGia> all = phienDauGiaService.getLayTatCaPhien();
        return all;
//        return all.stream()
//                .filter(p -> "dang_dien_ra".equals(p.getTrangThai()))
//                .collect(Collectors.toList());
    }

    @GetMapping("/phiendaugia/{id}")
    public ResponseEntity<?> layChiTietPhien(@PathVariable(name = "id") int id) {
        // Gọi service để cập nhật trạng thái, người thắng nếu phiên đã kết thúc
        phienDauGiaService.capNhatKetQuaPhien(id);

        // Lấy lại phiên đã cập nhật
        PhienDauGia phien = phienDauGiaService.getLayPhienTheoId(id);
        if (phien != null) {
            // Cập nhật giá hiện tại
            PhienDauGiaNguoiDung max = phienDauGiaNguoiDungService.getGiaCaoNhat(id);
            DonThanhToanDauGia donThanhToan = donThanhToanDauGiaService.findByPhien(phien);

            if (max != null) {
                phien.setGiaHienTai(max.getGiaDau());
            } else {
                phien.setGiaHienTai(null); // Không có giá đấu hiện tại
            }

            // Đảm bảo rằng luôn có thông tin thanh toán nếu đã có đơn
            if (donThanhToan != null) {
                phien.setDonThanhToan(donThanhToan);
            } else {
                phien.setDonThanhToan(null); // Không có đơn thanh toán
            }

            return ResponseEntity.ok(phien);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy phiên đấu giá");
    }

    @GetMapping("/baidau")
    public ResponseEntity<?> layDanhSachBaiDauCuaNguoiBan(Principal principal) {
        String username = principal.getName();

        // Lấy người dùng từ service hoặc cơ sở dữ liệu
        NguoiDung nguoiDung = nguoiDungService.getByUsername(username);

        if (nguoiDung == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy người dùng.");
        }

        // Lấy các phiên đấu giá theo nguoiDang_id của người dùng hiện tại
        List<PhienDauGia> phienDauGias = phienDauGiaService.getPhienDauByNguoiDangId(nguoiDung.getId());

        if (phienDauGias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không có bài đấu giá nào.");
        }

        for (PhienDauGia phien : phienDauGias) {
            // Lấy giá cao nhất từ các người tham gia đấu giá
            PhienDauGiaNguoiDung maxBid = phienDauGiaNguoiDungService.getGiaCaoNhat(phien.getId());
            if (maxBid != null) {
                phien.setGiaHienTai(maxBid.getGiaDau());  // Cập nhật giá hiện tại
            } else {
                phien.setGiaHienTai(null);  // Nếu chưa có ai đấu giá
            }
            DonThanhToanDauGia don = donThanhToanDauGiaService.findByPhien(phien);
            phien.setDonThanhToan(don);
        }

        return ResponseEntity.ok(phienDauGias);
    }

}
