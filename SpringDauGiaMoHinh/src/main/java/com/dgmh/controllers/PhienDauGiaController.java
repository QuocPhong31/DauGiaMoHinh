/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.controllers;

import com.dgmh.pojo.PhienDauGia;
import com.dgmh.pojo.SanPham;
import com.dgmh.services.PhienDauGiaService;
import com.dgmh.services.SanPhamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Tran Quoc Phong
 */
@Controller
@RequestMapping("/admin")
public class PhienDauGiaController {
    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private PhienDauGiaService phienDauGiaService;

    @GetMapping("/duyetSanPham")
    public String duyetSanPhamView(Model model) {
        List<SanPham> dsChoDuyet = sanPhamService.getSanPhamsTheoTrangThai("CHO_DUYET");
        model.addAttribute("sanPhamsChoDuyet", dsChoDuyet);
        return "duyetBaiDauGia";
    }

    @PostMapping("/duyet-san-pham")
    public String duyetSanPham(@RequestParam("id") int id, @RequestParam("action") String action) {
        SanPham sp = sanPhamService.getSanPhamById(id);
        if (sp != null) {
            if ("duyet".equals(action)) {
                // cập nhật trạng thái
                sanPhamService.updateTrangThai(id, "DUYET");

                // tạo phiên đấu giá
                PhienDauGia phien = new PhienDauGia();
                phien.setNguoiDang(sp.getNguoiDung());
                phien.setSanPham(sp);
                phien.setTrangThai("dang_dien_ra");
                phien.setThoiGianBatDau(sp.getNgayDang());
                phien.setThoiGianKetThuc(sp.getThoiGianKetThuc());
                phien.setDaThongBaoKQ(Boolean.FALSE);
                phienDauGiaService.themPhienDauGia(phien);

            } else if ("khong_duyet".equals(action)) {
                sanPhamService.updateTrangThai(id, "KHONG_DUYET");
            }
        }
        return "redirect:/admin/duyetSanPham";
    }
}
