/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.controllers;

import com.dgmh.dto.QuanLyBaiDauDTO;
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
            if (max != null) {
                phien.setGiaHienTai(max.getGiaDau());
            } else {
                //phien.setGiaHienTai(phien.getSanPham().getGiaKhoiDiem());
                phien.setGiaHienTai(null);
            }
            
            // Nếu có WINNER + giá chốt thì tạo đơn
            if (phien.getGiaChot() != null && phien.getNguoiThangDauGia() != null) {
                donThanhToanDauGiaService.taoDon(phien);
            }

            return ResponseEntity.ok(phien);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy phiên đấu giá");
    }
    
    @GetMapping("/bai-dau-gia")
    public ResponseEntity<List<QuanLyBaiDauDTO>> getBaiDauGiaCuaNguoiBan(Principal principal) {
        try {
            var me = nguoiDungService.getByUsername(principal.getName());
            return ResponseEntity.ok(phienDauGiaService.getBaiDauCuaNguoiBan(me.getId()));
        } catch (Exception ex) {
            ex.printStackTrace(); // xem lỗi chính xác trong console
        }
        return null;
    }

}
