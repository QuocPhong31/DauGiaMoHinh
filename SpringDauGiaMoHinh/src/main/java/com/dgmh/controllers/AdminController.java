/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.controllers;

import com.dgmh.pojo.NguoiDung;
import com.dgmh.services.NguoiDungService;
import java.util.Arrays;
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

    @GetMapping("/duyetNguoiDung")
    public String duyetNguoiDung(Model model) {
        List<NguoiDung> users = nguoiDungService.getAllUsers();  // Lấy danh sách người dùng cần duyệt
        model.addAttribute("users", users);
        return "duyetNguoiDung"; // Trả về view "duyetNguoiDung.html"
    }

    @PostMapping("/duyetNguoiDung")
    public String approveUser(@RequestParam("userId") int userId, RedirectAttributes redirectAttrs) {
        boolean result = nguoiDungService.duyetNguoiDung(userId);
        redirectAttrs.addFlashAttribute("message", result ? "Duyệt người dùng thành công!" : "Duyệt người dùng thất bại!");
        return "redirect:/admin/duyetNguoiDung";
    }

    @GetMapping("/themNguoiDung")
    public String showAddUserForm(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "addUser"; // tên file addUser.html
    }

    @PostMapping("/themNguoiDung")
    public String addUser(@RequestParam Map<String, String> params,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            RedirectAttributes redirectAttrs) {

        // Kiểm tra giá trị vaiTro và gán mặc định nếu không hợp lệ
        String vaiTro = params.get("vaiTro");

        // Kiểm tra xem vaiTro có hợp lệ không
        if (!Arrays.asList("ROLE_ADMIN", "ROLE_NGUOIBAN", "ROLE_NGUOIMUA").contains(vaiTro)) {
            vaiTro = "ROLE_NGUOIMUA";  // Gán mặc định nếu không hợp lệ
        }

        // Đảm bảo giá trị vaiTro đã được gán chính xác
        params.put("vaiTro", vaiTro);

        // Nếu là admin thì gán trạng thái được duyệt luôn
        if ("ROLE_ADMIN".equals(vaiTro)) {
            params.put("trangThai", "DUOC_DUYET");
        } else {
            params.put("trangThai", "CHO_DUYET");
        }

        // Thực hiện thêm người dùng
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
