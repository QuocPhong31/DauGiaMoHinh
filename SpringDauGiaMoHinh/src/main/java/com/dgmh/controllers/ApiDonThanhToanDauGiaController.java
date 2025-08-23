/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.controllers;

import com.dgmh.pojo.DonThanhToanDauGia;
import com.dgmh.pojo.NguoiDung;
import com.dgmh.pojo.PhienDauGia;
import com.dgmh.services.DonThanhToanDauGiaService;
import com.dgmh.services.NguoiDungService;
import com.dgmh.services.PhienDauGiaService;
import jakarta.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tran Quoc Phong
 */
@RestController
@RequestMapping("/api/thanhtoan")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiDonThanhToanDauGiaController {

    @Autowired
    private DonThanhToanDauGiaService donService;
    @Autowired
    private PhienDauGiaService phienService;
    @Autowired
    private NguoiDungService userService;

    // Tạo đơn cho phiên đã kết thúc (gọi sau khi xác định winner)
//    @PostMapping("/tao-don/{phienId}")
//    public ResponseEntity<?> taoDon(@PathVariable int phienId) {
//        PhienDauGia p = phienService.getLayPhienTheoId(phienId);
//        if (p == null || p.getGiaChot() == null || p.getNguoiThangDauGia() == null)
//            return ResponseEntity.badRequest().body("Phiên chưa có kết quả");
//
//        if (donService.findByPhien(p) != null)
//            return ResponseEntity.ok("Đơn đã tồn tại");
//
//        DonThanhToanDauGia d = new DonThanhToanDauGia();
//        d.setPhienDauGia(p);
//        d.setNguoiMua(p.getNguoiThangDauGia());
//        d.setSoTien(p.getGiaChot());
//        d.setTrangThai(DonThanhToanDauGia.TrangThai.PENDING);
//        return ResponseEntity.ok(donService.add(d));
//    }
    // Danh sách đơn của tôi (người thắng xem trên Header)
    @GetMapping("/cua-toi")
    public ResponseEntity<?> donCuaToi(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).build();
        }
        NguoiDung u = userService.getByUsername(principal.getName());
        return ResponseEntity.ok(donService.findByNguoiMua(u));
    }

    // Cập nhật thanh toán (COD/BANK + địa chỉ…)
    @PostMapping("/{donId}/thanh-toan")
    public ResponseEntity<?> thanhToan(@PathVariable("donId") int donId,
            @RequestBody Map<String, String> body,
            Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).build();
        }

        DonThanhToanDauGia don = donService.getById(donId);
        if (don == null) {
            return ResponseEntity.notFound().build();
        }

        // Chỉ chủ đơn (người thắng) mới được thao tác
        NguoiDung nguoiDangNhap = userService.getByUsername(principal.getName());
        if (nguoiDangNhap == null || !nguoiDangNhap.getId().equals(don.getNguoiMua().getId())) {
            return ResponseEntity.status(403).body("Bạn không có quyền thao tác đơn này");
        }

        if (don.getTrangThai() == DonThanhToanDauGia.TrangThai.SELLER_REVIEW) {
            return ResponseEntity.badRequest().body("Đơn đã thanh toán");
        }

        try {
            // Lấy dữ liệu
            String phuongThuc = body.getOrDefault("phuongThuc", "COD");
            String hoTenNhan = body.get("hoTenNhan");
            String soDienThoai = body.get("soDienThoai");
            String diaChiNhan = body.get("diaChiNhan");
            String ghiChu = body.get("ghiChu");

            // Validate đơn giản
            if (hoTenNhan == null || hoTenNhan.isBlank()
                    || soDienThoai == null || soDienThoai.isBlank()
                    || diaChiNhan == null || diaChiNhan.isBlank()) {
                return ResponseEntity.badRequest().body("Vui lòng nhập đủ họ tên, số điện thoại và địa chỉ nhận");
            }

            // Set thông tin thanh toán
            DonThanhToanDauGia.PhuongThuc pm
                    = "BANK".equalsIgnoreCase(phuongThuc) ? DonThanhToanDauGia.PhuongThuc.BANK
                    : DonThanhToanDauGia.PhuongThuc.COD;
            don.setPhuongThuc(pm);
            don.setHoTenNhan(hoTenNhan);
            don.setSoDienThoai(soDienThoai);
            don.setDiaChiNhan(diaChiNhan);
            don.setGhiChu(ghiChu);
            don.setTrangThai(DonThanhToanDauGia.TrangThai.SELLER_REVIEW);
            don.setNgayThanhToan(new java.util.Date());

            return ResponseEntity.ok(donService.update(don));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Lỗi xử lý thanh toán");
        }
    }

    @PutMapping("/{donId}/xac-nhan")
    public ResponseEntity<?> xacNhanDon(@PathVariable("donId") int donId, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).build();
        }

        DonThanhToanDauGia don = donService.getById(donId);
        if (don == null) {
            return ResponseEntity.notFound().build();
        }

        // Lấy người dùng hiện tại
        NguoiDung nguoiDangNhap = userService.getByUsername(principal.getName());

        // Chỉ cho phép người đăng phiên đấu xác nhận đơn
        PhienDauGia phien = don.getPhienDauGia();
        if (phien == null || !phien.getNguoiDang().getId().equals(nguoiDangNhap.getId())) {
            return ResponseEntity.status(403).body("Bạn không có quyền xác nhận đơn này");
        }

        // Chỉ xác nhận nếu trạng thái hiện tại là SELLER_REVIEW
        if (don.getTrangThai() != DonThanhToanDauGia.TrangThai.SELLER_REVIEW) {
            return ResponseEntity.badRequest().body("Chỉ được xác nhận đơn ở trạng thái SELLER_REVIEW");
        }

        // Cập nhật trạng thái sang PAID
        don.setTrangThai(DonThanhToanDauGia.TrangThai.PAID);
        don.setNgaySellerDuyet(new Date());

        return ResponseEntity.ok(donService.update(don));
    }

    @PutMapping("/huy/{id}")
    public ResponseEntity<?> huyDon(@PathVariable("id") int id, @RequestBody Map<String, String> body) {
        String lyDo = body.get("lyDo");

        if (lyDo == null || lyDo.isBlank()) {
            return ResponseEntity.badRequest().body("Lý do không được để trống.");
        }

        donService.huyDon(id, lyDo);
        return ResponseEntity.ok("Đơn đã được hủy.");
    }
}
