/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dgmh.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Tran Quoc Phong
 */
public class QuanLyBaiDauDTO {
    private Integer phienId;
    private Integer sanPhamId;
    private String tenSanPham;
    private String hinhAnh;
    private BigDecimal giaKhoiDiem;
    private BigDecimal giaHienTai;

    // Khớp với entity PhienDauGia (java.util.Date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date thoiGianBatDau;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date thoiGianKetThuc;

    private String trangThaiSanPham;   // CHO_DUYET/DUYET/KHONG_DUYET
    private String trangThaiPhien;     // dang_dien_ra/da_ket_thuc
    private BigDecimal giaChot;
    private String nguoiThangUsername;
    private String trangThaiThanhToan; // PENDING/PAID/CANCELLED hoặc null

    public QuanLyBaiDauDTO() {}

    public QuanLyBaiDauDTO(Integer phienId, Integer sanPhamId, String tenSanPham, String hinhAnh,
                           BigDecimal giaKhoiDiem, BigDecimal giaHienTai,
                           Date thoiGianBatDau, Date thoiGianKetThuc,
                           String trangThaiSanPham, String trangThaiPhien,
                           BigDecimal giaChot, String nguoiThangUsername, String trangThaiThanhToan) {
        this.phienId = phienId;
        this.sanPhamId = sanPhamId;
        this.tenSanPham = tenSanPham;
        this.hinhAnh = hinhAnh;
        this.giaKhoiDiem = giaKhoiDiem;
        this.giaHienTai = giaHienTai;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.trangThaiSanPham = trangThaiSanPham;
        this.trangThaiPhien = trangThaiPhien;
        this.giaChot = giaChot;
        this.nguoiThangUsername = nguoiThangUsername;
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public Integer getPhienId() { 
        return phienId; 
    }
    public void setPhienId(Integer phienId) { 
        this.phienId = phienId; 
    }

    public Integer getSanPhamId() { 
        return sanPhamId; 
    }
    public void setSanPhamId(Integer sanPhamId) { 
        this.sanPhamId = sanPhamId; 
    }

    public String getTenSanPham() { 
        return tenSanPham; 
    }
    public void setTenSanPham(String tenSanPham) { 
        this.tenSanPham = tenSanPham; 
    }

    public String getHinhAnh() { 
        return hinhAnh; 
    }
    public void setHinhAnh(String hinhAnh) { 
        this.hinhAnh = hinhAnh; 
    }

    public BigDecimal getGiaKhoiDiem() { 
        return giaKhoiDiem; 
    }
    public void setGiaKhoiDiem(BigDecimal giaKhoiDiem) { 
        this.giaKhoiDiem = giaKhoiDiem; 
    }

    public BigDecimal getGiaHienTai() { 
        return giaHienTai; 
    }
    public void setGiaHienTai(BigDecimal giaHienTai) { 
        this.giaHienTai = giaHienTai; 
    }

    public Date getThoiGianBatDau() { 
        return thoiGianBatDau; 
    }
    public void setThoiGianBatDau(Date thoiGianBatDau) { 
        this.thoiGianBatDau = thoiGianBatDau; 
    }

    public Date getThoiGianKetThuc() { 
        return thoiGianKetThuc; 
    }
    public void setThoiGianKetThuc(Date thoiGianKetThuc) { 
        this.thoiGianKetThuc = thoiGianKetThuc; 
    }

    public String getTrangThaiSanPham() { 
        return trangThaiSanPham; 
    }
    public void setTrangThaiSanPham(String trangThaiSanPham) { 
        this.trangThaiSanPham = trangThaiSanPham; 
    }

    public String getTrangThaiPhien() { 
        return trangThaiPhien; 
    }
    public void setTrangThaiPhien(String trangThaiPhien) { 
        this.trangThaiPhien = trangThaiPhien; 
    }

    public BigDecimal getGiaChot() { 
        return giaChot; 
    }
    public void setGiaChot(BigDecimal giaChot) { 
        this.giaChot = giaChot; 
    }

    public String getNguoiThangUsername() { 
        return nguoiThangUsername; 
    }
    public void setNguoiThangUsername(String nguoiThangUsername) { 
        this.nguoiThangUsername = nguoiThangUsername; 
    }

    public String getTrangThaiThanhToan() { 
        return trangThaiThanhToan; 
    }
    public void setTrangThaiThanhToan(String trangThaiThanhToan) { 
        this.trangThaiThanhToan = trangThaiThanhToan; 
    }
}