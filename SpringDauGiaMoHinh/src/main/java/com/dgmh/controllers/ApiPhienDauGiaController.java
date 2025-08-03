/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.controllers;

import com.dgmh.pojo.PhienDauGia;
import com.dgmh.services.PhienDauGiaService;
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
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiPhienDauGiaController {

    @Autowired
    private PhienDauGiaService phienDauGiaService;

    @GetMapping("/phiendaugia")
    public List<PhienDauGia> layTatCaPhienDangDienRa() {
        List<PhienDauGia> all = phienDauGiaService.getLayTatCaPhien();
        return all.stream()
                .filter(p -> "dang_dien_ra".equals(p.getTrangThai()))
                .collect(Collectors.toList());
    }

    @GetMapping("/phiendaugia/{id}")
    public ResponseEntity<?> layChiTietPhien(@PathVariable(name = "id") int id) {
        PhienDauGia phien = phienDauGiaService.getLayPhienTheoId(id);
        if (phien != null) {
            return ResponseEntity.ok(phien);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy phiên đấu giá");
    }
}
