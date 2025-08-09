/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services.impl;

import com.dgmh.pojo.PhienDauGia;
import com.dgmh.repositories.PhienDauGiaRepository;
import com.dgmh.services.MailService;
import com.dgmh.services.PhienDauGiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tran Quoc Phong
 */
@Service
public class PhienDauGiaServiceImpl implements PhienDauGiaService {

    @Autowired
    private PhienDauGiaRepository phienDauGiaRepository;
    
    @Autowired
    private MailService mailService;

    @Override
    public PhienDauGia themPhienDauGia(PhienDauGia p) {
        // Mặc định trạng thái là "CHO_DUYET"
        //p.setTrangThai("CHO_DUYET");
        return phienDauGiaRepository.themPhienDauGia(p);
    }

    @Override
    public List<PhienDauGia> getLayTatCaPhien() {
        return phienDauGiaRepository.getLayTatCaPhien();
    }

    @Override
    public PhienDauGia getLayPhienTheoId(int id) {
        return phienDauGiaRepository.getLayPhienTheoId(id);
    }

    @Override
    public boolean duyetPhien(int id) {
        return phienDauGiaRepository.capNhatTrangThai(id, "DUOC_DUYET");
    }
    
    @Override
    @Transactional
    public boolean capNhatKetQuaPhien(int phienId) {
        phienDauGiaRepository.capNhatKetQuaPhien(phienId);

        PhienDauGia phien = phienDauGiaRepository.getLayPhienTheoId(phienId);
        if (phien == null) return false;

        boolean ketThuc = "da_ket_thuc".equalsIgnoreCase(phien.getTrangThai());
        boolean chuaThongBao = (phien.getDaThongBaoKQ() == null) || !phien.getDaThongBaoKQ();
        boolean coWinner = phien.getNguoiThangDauGia() != null && phien.getGiaChot() != null;

        if (ketThuc && coWinner && chuaThongBao) {
            String email = phien.getNguoiThangDauGia().getEmail();
            if (email != null && !email.isBlank()) {
                try {
                    mailService.sendWinnerEmail(
                        email,
                        phien.getNguoiThangDauGia().getHoTen(),
                        phien.getSanPham().getTenSanPham(),
                        phien.getGiaChot()
                    );
                    // đánh dấu đã thông báo để không gửi lặp
                    phien.setDaThongBaoKQ(true);
                    // vì đang @Transactional và entity managed, flush sẽ tự update
                    // nếu bạn muốn rõ ràng, thêm repo.update(phien);
                } catch (Exception ex) {
                    ex.printStackTrace(); // log, không rollback giao dịch
                }
            }
        }
        return ketThuc;
    }
}
