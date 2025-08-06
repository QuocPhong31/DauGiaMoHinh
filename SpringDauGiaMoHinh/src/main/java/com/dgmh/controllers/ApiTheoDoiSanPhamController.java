/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.controllers;

import com.dgmh.services.TheoDoiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tran Quoc Phong
 */
@RestController
@RequestMapping("/api/theodoisanpham")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiTheoDoiSanPhamController {
    @Autowired
    private TheoDoiSanPhamService service;

    @PostMapping("/them")
    public ResponseEntity<?> theoDoi(@RequestParam int nguoiDungId, @RequestParam int phienId) {
        return ResponseEntity.ok(service.theoDoi(nguoiDungId, phienId));
    }

    @DeleteMapping("/xoa")
    public ResponseEntity<?> boTheoDoi(@RequestParam int nguoiDungId, @RequestParam int phienId) {
        return ResponseEntity.ok(service.boTheoDoi(nguoiDungId, phienId));
    }

    @GetMapping("/danhsach")
    public ResponseEntity<?> layTheoDoi(@RequestParam int nguoiDungId) {
        return ResponseEntity.ok(service.getTheoDoiByNguoiDung(nguoiDungId));
    }

    @GetMapping("/kiemtra")
    public ResponseEntity<?> isTheoDoi(@RequestParam int nguoiDungId, @RequestParam int phienId) {
        return ResponseEntity.ok(service.isDangTheoDoi(nguoiDungId, phienId));
    }
}
