/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.services.impl;

import com.dgmh.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tran Quoc Phong
 */
@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender mailSender;

    // Đổi thành email "from" phù hợp (nên trùng với tài khoản app password)
    private static final String FROM = "asamikiri2004@gmail.com";

    @Override
    public void sendApprovalEmail(String toEmail, String hoTen) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM);
        message.setTo(toEmail);
        message.setSubject("Tài khoản của bạn đã được duyệt");
        message.setText(buildBody(hoTen));
        mailSender.send(message);
    }

    private String buildBody(String hoTen) {
        String ten = hoTen != null && !hoTen.isBlank() ? hoTen : "bạn";
        return "Xin chào " + ten + ",\n\n"
             + "Tài khoản đăng ký của bạn đã được duyệt thành công. "
             + "Bây giờ bạn có thể đăng nhập và sử dụng hệ thống đấu giá.\n\n"
             + "Nếu bạn có gặp vấn đề, vui lòng liên hệ bộ phận hỗ trợ.\n\n"
             + "Trân trọng\n"
             + "Đội ngũ hỗ trợ DGMH";
    }
}
