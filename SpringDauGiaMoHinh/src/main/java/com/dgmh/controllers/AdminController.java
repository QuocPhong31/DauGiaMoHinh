/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.controllers;

import com.dgmh.pojo.NguoiDung;
import com.dgmh.services.NguoiDungService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Tran Quoc Phong
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("")
    public String adminView(Model model) {
        List<NguoiDung> users = nguoiDungService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";  // admin.html
    }

    @PostMapping("/add-user")
    public String addUser(@RequestParam Map<String, String> params,
                          @RequestParam(value = "avatar", required = false) MultipartFile avatar,
                          RedirectAttributes redirectAttrs) {
        nguoiDungService.addUser(params, avatar);
        redirectAttrs.addFlashAttribute("message", "Thêm người dùng thành công!");
        return "redirect:/admin";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam("userId") int id, RedirectAttributes redirectAttrs) {
        boolean result = nguoiDungService.deleteUser(id);
        redirectAttrs.addFlashAttribute("message", result ? "Xóa người dùng thành công!" : "Xóa người dùng thất bại!");
        return "redirect:/admin";
    }
}
