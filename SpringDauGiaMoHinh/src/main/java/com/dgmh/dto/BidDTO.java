/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Tran Quoc Phong
 */
public class BidDTO {
    private String hoTen;
    private String username;
    private String avatar;
    private BigDecimal giaDau;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime thoiGian;

    private boolean caoNhat;

    public BidDTO() {
        
    }
    
    public BidDTO(String hoTen, String username, String avatar, BigDecimal giaDau,
                  LocalDateTime thoiGian, boolean caoNhat) {
        this.hoTen = hoTen;
        this.username = username;
        this.avatar = avatar;
        this.giaDau = giaDau;
        this.thoiGian = thoiGian;
        this.caoNhat = caoNhat;
    }
    
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public BigDecimal getGiaDau() {
        return giaDau;
    }

    public void setGiaDau(BigDecimal giaDau) {
        this.giaDau = giaDau;
    }

    public LocalDateTime getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(LocalDateTime thoiGian) {
        this.thoiGian = thoiGian;
    }

    public boolean isCaoNhat() {
        return caoNhat;
    }

    public void setCaoNhat(boolean caoNhat) {
        this.caoNhat = caoNhat;
    }
}
