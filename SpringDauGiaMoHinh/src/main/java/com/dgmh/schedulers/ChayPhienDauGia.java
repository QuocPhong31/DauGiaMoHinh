/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.schedulers;

import com.dgmh.pojo.DonThanhToanDauGia;
import com.dgmh.pojo.PhienDauGia;
import com.dgmh.services.DonThanhToanDauGiaService;
import com.dgmh.services.PhienDauGiaService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Tran Quoc Phong
 */
@Component
public class ChayPhienDauGia {

    @Autowired
    private PhienDauGiaService phienDauGiaService;

    @Autowired
    private DonThanhToanDauGiaService donThanhToanDauGiaService;

    // Chạy mỗi phút
    @Scheduled(fixedRate = 60000)
    public void kiemTraPhienHetHan() {

        List<PhienDauGia> tatCaPhien = phienDauGiaService.getLayTatCaPhien();
        for (PhienDauGia phien : tatCaPhien) {
            if ("dang_dien_ra".equals(phien.getTrangThai())) {
                boolean ketThuc = phienDauGiaService.capNhatKetQuaPhien(phien.getId());

                if (ketThuc) {
                    // Gọi lại phiên để lấy thông tin mới cập nhật
                    PhienDauGia phienCapNhat = phienDauGiaService.getLayPhienTheoId(phien.getId());
                    System.out.println("➡️ Đang kiểm tra phiên #" + phien.getId());
                    // Chỉ tạo đơn nếu có winner và giá chốt
                    if (phienCapNhat.getNguoiThangDauGia() != null && phienCapNhat.getGiaChot() != null) {
                        System.out.println("✅ Điều kiện đúng, tạo đơn cho phiên #" + phien.getId());
                        donThanhToanDauGiaService.taoDon(phienCapNhat);
                        System.out.println("✅ Đã tạo đơn thanh toán cho phiên #" + phien.getId());
                    }
                }
            }
        }
    }

    @Scheduled(fixedRate = 70000)
    public void kiemTraDonQuaHan() {

        // Lấy tất cả các đơn chưa thanh toán và chưa hủy
        List<DonThanhToanDauGia> donChuaThanhToan = donThanhToanDauGiaService.DonQuaHanChuaThanhToan();

        // Lặp qua các đơn hàng để kiểm tra
        for (DonThanhToanDauGia don : donChuaThanhToan) {
            Date today = new Date();
            // Kiểm tra nếu đơn hàng đã quá 3 ngày chưa thanh toán
            if (don.getNgayTao().before(new Date(today.getTime() - 3L * 24 * 60 * 60 * 1000))) {
                // Cập nhật trạng thái đơn hàng thành CANCELLED
                don.setTrangThai(DonThanhToanDauGia.TrangThai.CANCELLED);
                don.setGhiChu("Đơn bị hủy vì quá hạn thanh toán 3 ngày.");
                donThanhToanDauGiaService.update(don);
                System.out.println("Đã hủy đơn #" + don.getId() + " vì quá hạn thanh toán.");
            }
        }
    }
}
