/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.controllers;

import com.dgmh.pojo.LoaiSanPham;
import com.dgmh.services.LoaiSanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Tran Quoc Phong
 */
@Controller
public class LoaiSanPhamController {
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;

    // Hiển thị form
    @GetMapping("/admin/loaiSanPham")
    public String loaiSanPhamForm(Model model) {
        model.addAttribute("loaiSanPham", new LoaiSanPham());
        model.addAttribute("dsLoai", loaiSanPhamService.getAllLoaiSanPham());
        return "loaiSanPham";
    }

    // Xử lý form
    @PostMapping("/admin/loaiSanPham")
    public String themLoaiSanPham(@ModelAttribute @Valid LoaiSanPham loaiSanPham,
                                   BindingResult result,
                                   Model model) {
        if (!result.hasErrors()) {
            loaiSanPhamService.addLoaiSanPham(loaiSanPham);
            model.addAttribute("successMessage", "Thêm loại sản phẩm thành công!");
        }
        model.addAttribute("dsLoai", loaiSanPhamService.getAllLoaiSanPham());
        return "loaiSanPham";
    }
}
