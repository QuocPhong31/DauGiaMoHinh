package com.dgmh.controllers;

import com.dgmh.pojo.NguoiDung;
import com.dgmh.pojo.PhienDauGia;
import com.dgmh.pojo.PhienDauGiaNguoiDung;
import com.dgmh.services.NguoiDungService;
import com.dgmh.services.PhienDauGiaNguoiDungService;
import com.dgmh.services.PhienDauGiaService;
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
import org.springframework.web.bind.annotation.RestController;

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
            // Kiểm tra đăng nhập
            if (principal == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bạn cần đăng nhập để đấu giá");
            }
            String username = principal.getName();
            NguoiDung nguoiDung = this.nguoiDungService.getByUsername(username);
            if (nguoiDung == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Không tìm thấy thông tin người dùng");
            }

            // Lấy thông tin phiên và giá
            int phienId = Integer.parseInt(params.get("phienDauGiaId"));
            BigDecimal giaMoi = new BigDecimal(params.get("gia"));

            PhienDauGia phien = this.phienDauGiaService.getLayPhienTheoId(phienId);
            if (phien == null) {
                return ResponseEntity.badRequest().body("Không tìm thấy phiên đấu giá");
            }

            // Không cho người bán đấu giá sản phẩm của mình
            if (phien.getSanPham() != null
                    && phien.getSanPham().getNguoiDung() != null
                    && phien.getSanPham().getNguoiDung().getId().equals(nguoiDung.getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Bạn không thể đấu giá sản phẩm của chính mình");
            }

            // Lấy giá cao nhất hiện tại
            PhienDauGiaNguoiDung giaCaoNhatRecord = this.phienDauGiaNguoiDungService.getGiaCaoNhat(phienId);
            BigDecimal giaKhoiDiem = phien.getSanPham().getGiaKhoiDiem();
            BigDecimal buocNhay = phien.getSanPham().getBuocNhay();

            boolean laBidDauTien = (giaCaoNhatRecord == null);
            BigDecimal giaToiThieu = laBidDauTien
                    ? giaKhoiDiem
                    : giaCaoNhatRecord.getGiaDau().add(buocNhay);

            if (giaMoi.compareTo(giaToiThieu) < 0) {
                return ResponseEntity.badRequest()
                        .body("Giá bạn nhập phải lớn hơn hoặc bằng " + giaToiThieu.toPlainString());
            }

            // Kiểm tra bước nhảy
            BigDecimal mocSoSanh = laBidDauTien ? giaKhoiDiem : giaCaoNhatRecord.getGiaDau();
            BigDecimal du = giaMoi.subtract(mocSoSanh).remainder(buocNhay);
            if (du.compareTo(BigDecimal.ZERO) != 0) {
                return ResponseEntity.badRequest()
                        .body("Giá phải theo bước nhảy " + buocNhay.toPlainString());
            }

            // Lưu giá đấu
            PhienDauGiaNguoiDung pdgNd = new PhienDauGiaNguoiDung();
            pdgNd.setPhienDauGia(phien);
            pdgNd.setNguoiDung(nguoiDung);
            pdgNd.setGiaDau(giaMoi);
            pdgNd.setThoiGianDauGia(LocalDateTime.now());

            this.phienDauGiaNguoiDungService.datGia(pdgNd);

            // === Cập nhật giaHienTai của phiên ===
            phien.setGiaHienTai(giaMoi); // kể cả khi = giá khởi điểm
            this.phienDauGiaService.capNhatPhien(phien);
            // =====================================

            return ResponseEntity.ok("Đặt giá thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Lỗi xử lý đặt giá: " + e.getMessage());
        }
    }
}
