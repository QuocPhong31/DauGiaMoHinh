/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.schedulers;

import com.dgmh.pojo.PhienDauGia;
import com.dgmh.services.DonThanhToanDauGiaService;
import com.dgmh.services.PhienDauGiaService;
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
                    if (phienCapNhat.getNguoiThangDauGia() != null && phienCapNhat.getGiaChot() != null) 
                    {
                        System.out.println("✅ Điều kiện đúng, tạo đơn cho phiên #" + phien.getId());
                        donThanhToanDauGiaService.taoDon(phienCapNhat);
                        System.out.println("✅ Đã tạo đơn thanh toán cho phiên #" + phien.getId());
                    }
                }
            }
        }
    }
}
