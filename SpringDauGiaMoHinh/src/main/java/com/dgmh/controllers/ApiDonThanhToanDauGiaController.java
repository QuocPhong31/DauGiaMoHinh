///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.dgmh.controllers;
//
//import com.dgmh.pojo.DonThanhToanDauGia;
//import com.dgmh.pojo.NguoiDung;
//import com.dgmh.services.DonThanhToanDauGiaService;
//import com.dgmh.services.NguoiDungService;
//import jakarta.validation.constraints.NotNull;
//import java.util.Date;
//import java.util.Map;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// *
// * @author Tran Quoc Phong
// */
//@RestController
//@RequestMapping("/api/thanhtoan")
//@CrossOrigin(origins="http://localhost:3000")
//public class ApiDonThanhToanDauGiaController {
//    @Autowired 
//    private DonThanhToanDauGiaService donSvc;
//    @Autowired 
//    private NguoiDungService userSvc;
//
//    // Danh sách đơn PENDING/PAID của chính user đăng nhập
//    @GetMapping("/danhsach")
//    public ResponseEntity<?> ds(@NotNull java.security.Principal principal) {
//        NguoiDung u = userSvc.getByUsername(principal.getName());
//        return ResponseEntity.ok(donSvc.danhSachCuaNguoiMua(u));
//    }
//
//    // Xem chi tiết
//    @GetMapping("/{id}")
//    public ResponseEntity<?> chiTiet(@PathVariable int id, java.security.Principal principal) {
//        var d = donSvc.getById(id);
//        if (d == null) return ResponseEntity.notFound().build();
//        if (!d.getNguoiMua().getUsername().equals(principal.getName()))
//            return ResponseEntity.status(403).body("Không có quyền");
//        return ResponseEntity.ok(d);
//    }
//
//    // Xác nhận thanh toán
//    @PostMapping("/{id}/xac-nhan")
//    public ResponseEntity<?> xacNhan(@PathVariable int id, @RequestBody Map<String,String> body,
//                                     java.security.Principal principal) {
//        var d = donSvc.getById(id);
//        if (d == null) return ResponseEntity.notFound().build();
//        if (!d.getNguoiMua().getUsername().equals(principal.getName()))
//            return ResponseEntity.status(403).body("Không có quyền");
//
//        d.setHoTenNhan(body.getOrDefault("hoTenNhan", d.getNguoiMua().getHoTen()));
//        d.setSoDienThoai(body.getOrDefault("soDienThoai", d.getNguoiMua().getSoDienThoai()));
//        d.setDiaChiNhan(body.getOrDefault("diaChiNhan",""));
//        d.setGhiChu(body.getOrDefault("ghiChu",""));
//        d.setPhuongThuc("BANK".equalsIgnoreCase(body.get("phuongThuc")) 
//            ? DonThanhToanDauGia.PhuongThuc.BANK : DonThanhToanDauGia.PhuongThuc.COD);
//        d.setTrangThai(DonThanhToanDauGia.TrangThai.PAID);
//        d.setNgayThanhToan(new Date());
//
//        return ResponseEntity.ok(donSvc.capNhat(d));
//    }
//}
